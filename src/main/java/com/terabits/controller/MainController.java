package com.terabits.controller;

import com.terabits.config.WeixinGlobal;
import com.terabits.meta.bo.WeixinUserBO;
import com.terabits.meta.po.UserPO;
import com.terabits.service.CredentialService;

import com.terabits.meta.po.TerminalPO;
import com.terabits.service.TerminalService;
import com.terabits.service.UserService;
import com.terabits.utils.WeixinUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/21.
 */
@Controller
public class MainController
{
    @Autowired
    private CredentialService CredentialService;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private UserService userService;
    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value="/info/{displayId}",method=RequestMethod.GET)
    public String getProductInfo(@PathVariable("displayId") String displayId, HttpServletRequest request, ModelMap model) throws Exception {
        System.out.println("displayId"+displayId);
        Long display = Long.parseLong(displayId);
        String time = CredentialService.getTerminalTime(displayId);
        TerminalPO terminalPO = terminalService.selectOneTerminal(displayId);
        if(time == null){
            model.addAttribute("status","空闲");
            model.addAttribute("id",displayId);
            model.addAttribute("location",terminalPO.getLocation());
            model.addAttribute("usingtime",null);
            model.addAttribute("lefttime",null);
            return "main/information.jsp";
        }
        else{
            SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long between = 0;
            try {
                Date begin =dfs.parse(time);
                Date end = new Date();
                between = (end.getTime() - begin.getTime())/1000;// 得到两者的秒数
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            String leftTime = CredentialService.getLeftTime(displayId);
            System.out.println("lefttime:::::::::" + leftTime);
            long money = (between + Long.parseLong(leftTime)) / 40;
            System.out.println("money::::::" + String.valueOf(money));
            model.addAttribute("status","使用中");
            model.addAttribute("id",displayId);
            model.addAttribute("location",terminalPO.getLocation());
            model.addAttribute("usingtime", String.valueOf(between));
            model.addAttribute("lefttime", leftTime);
            model.addAttribute("money",String.valueOf(money));
            model.addAttribute("water",String.valueOf(money));
            return "main/using.jsp";
        }
    }

    /**用户从微信对话框点击图文消息或者下方按钮进入此页，首先调用微信oauth2.0授权接口获取用户信息，得到openid
     * 去数据库中查询此用户的phone字段是否为空，不为空则表明已注册过，更新其他信息，返回openId,language,nickname和headimgurl给前端，并跳转到首页(login.jsp)
     * 若为空，则表明是未注册用户，则判断language是否为空，若为空，表明之前从未进入过，将该用户信息插入user表，若不为空，则
     * 表明之前进入过，只是没有注册就退出了，则将user信息更新，返回openId和language给前端，跳转到注册页(register.jsp)
     */
    @RequestMapping(value="/mainpage",method=RequestMethod.GET)
    public String mainpage(HttpServletRequest request, ModelMap model, HttpSession session){
        //获取code和openid
        String code = request.getParameter("code");
        JSONObject jsonObject = WeixinUtil.getOpenid(code, WeixinGlobal.APP_ID, WeixinGlobal.APP_SECRET);
        String openId = jsonObject.getString("openid");
        String accesstoken = jsonObject.getString("access_token");
        //获取用户信息
        JSONObject jsonObject1 = WeixinUtil.getUserInfo(accesstoken, openId);
        //将用户信息存入userPO，备用
        UserPO userPO = new UserPO();
        userPO.setOpenId(openId);
        userPO.setNickname(jsonObject1.getString("nickname"));
        userPO.setSex(Integer.parseInt(jsonObject1.getString("sex")));
        userPO.setLanguage(jsonObject1.getString("language"));
        userPO.setCity(jsonObject1.getString("city"));
        userPO.setProvince(jsonObject1.getString("province"));
        userPO.setCountry(jsonObject1.getString("country"));
        userPO.setHeadImgUrl(jsonObject1.getString("headimgurl"));

        WeixinUserBO weixinUserBO = new WeixinUserBO();
        try {
            //取回phone和language
            weixinUserBO = userService.userRegistered(openId);
        }catch (Exception e){
            logger.error("userService.userRegistered error in mainpage!");
        }
        //phone不为null，表明已注册，则更新一下信息，返回首页
        if(weixinUserBO.getPhone() != null){
            try{
                userService.updateInfo(userPO);
            }catch (Exception e){
                logger.error("userService.updateInfo error for registered user in mainpage!");
            }
            model.addAttribute("openId", openId);
            model.addAttribute("language", jsonObject1.getString("language"));
            model.addAttribute("nickname", jsonObject1.getString("nickname"));
            model.addAttribute("headimgurl",jsonObject1.getString("headimgurl"));
            return "main/login.jsp";
        }
        else{
            //language不为null，表明之前点进来过，则更新一下信息，进入注册页
            if(weixinUserBO.getLanguage() != null) {
                try {
                    userService.updateInfo(userPO);
                } catch (Exception e) {
                    logger.error("userService.updateInfo error for unregistered user in mainpage!");
                }
                model.addAttribute("openId", openId);
                model.addAttribute("language", jsonObject1.getString("language"));
                return "main/register.jsp";
            //若language为null，则表明之前从未进来过，则插入该用户信息，进入注册页
            }else{
                try {
                    userService.insertUser(userPO);
                } catch (Exception e) {
                    logger.error("userService.updateInfo error for never enter user in mainpage!");
                }
                model.addAttribute("openId", openId);
                model.addAttribute("language", jsonObject1.getString("language"));
                return "main/register.jsp";
            }
        }
    }



}
