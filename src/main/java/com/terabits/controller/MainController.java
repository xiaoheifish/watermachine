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
public class MainController {

    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    /**
     * 用户从微信对话框点击图文消息或者下方按钮进入此页，首先调用微信oauth2.0授权接口获取用户信息，得到openid
     * 去数据库中查询此用户的phone字段是否为空，不为空则表明已注册过，更新其他信息，返回openId,language,nickname和headimgurl给前端，并跳转到首页(login.jsp)
     * 若为空，则表明是未注册用户，则判断language是否为空，若为空，表明之前从未进入过，将该用户信息插入user表，若不为空，则
     * 表明之前进入过，只是没有注册就退出了，则将user信息更新，返回openId和language给前端，跳转到注册页(register.jsp)
     */
    @RequestMapping(value = "/mainpage", method = RequestMethod.GET)
    public String mainpage(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
        //获取code和openid
        String code = request.getParameter("code");
        if (code == null) {
            String sessionOpenId = (String)session.getAttribute("openid");
            UserPO userPO = userService.selectUser(sessionOpenId);
            model.addAttribute("openId", userPO.getOpenId());
            model.addAttribute("language", userPO.getLanguage());
            model.addAttribute("nickname", userPO.getNickname());
            model.addAttribute("headimgurl", userPO.getHeadImgUrl());
            return "main/login.jsp";
        }
        JSONObject jsonObject = WeixinUtil.getOpenid(code, WeixinGlobal.APP_ID, WeixinGlobal.APP_SECRET);
        if(jsonObject.has("openid")==false){
            String sessionOpenId = (String)session.getAttribute("openid");
            UserPO userPO = userService.selectUser(sessionOpenId);
            model.addAttribute("openId", userPO.getOpenId());
            model.addAttribute("language", userPO.getLanguage());
            model.addAttribute("nickname", userPO.getNickname());
            model.addAttribute("headimgurl", userPO.getHeadImgUrl());
            return "main/login.jsp";
        }
        String openId = jsonObject.getString("openid");
        //存入session中，以备后续使用
        session.setAttribute("openid", openId);
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
        System.out.println("userPO" + userPO);
       /* try {
            //取回phone和language
            weixinUserBO = userService.userRegistered(openId);
        }catch (Exception e){
            logger.error("userService.userRegistered error in mainpage!");
        }*/
        WeixinUserBO weixinUserBO = userService.userRegistered(openId);
        //phone不为null，表明已注册，则更新一下信息，返回首页
        if (!(weixinUserBO == null)) {
            if (weixinUserBO.getPhone() == null) {
                //phone为null，表明之前点进来过，则更新一下信息，进入注册页
                try {
                    userService.updateInfo(userPO);
                } catch (Exception e) {
                    logger.error("userService.updateInfo error for unregistered user in mainpage!");
                }
                model.addAttribute("openId", openId);
                model.addAttribute("language", jsonObject1.getString("language"));
                return "main/signup.jsp";
            }
            try {
                userService.updateInfo(userPO);
            } catch (Exception e) {
                logger.error("userService.updateInfo error for registered user in mainpage!");
            }
            model.addAttribute("openId", openId);
            model.addAttribute("language", jsonObject1.getString("language"));
            model.addAttribute("nickname", jsonObject1.getString("nickname"));
            model.addAttribute("headimgurl", jsonObject1.getString("headimgurl"));
            return "main/login.jsp";
        } else {
            //若language为null，则表明之前从未进来过，则插入该用户信息，进入注册页

            try {
                userService.insertUser(userPO);
            } catch (Exception e) {
                logger.error("userService.updateInfo error for never enter user in mainpage!");
            }
            model.addAttribute("openId", openId);
            model.addAttribute("language", jsonObject1.getString("language"));
            return "main/signup.jsp";

        }
    }


}
