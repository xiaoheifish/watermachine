package com.terabits.controller;

import com.terabits.config.Constants;
import com.terabits.meta.po.AuxcalPO;
import com.terabits.meta.po.InvitationPO;
import com.terabits.meta.po.PresentPO;
import com.terabits.meta.po.TotalPO;
import com.terabits.meta.po.UserPO;
import com.terabits.service.InvitationService;
import com.terabits.service.PresentService;
import com.terabits.service.SmsService;
import com.terabits.service.StatisticService;
import com.terabits.service.UserService;
import com.terabits.service.impl.SmsServiceImpl;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/19.
 */
@Controller
public class RegisterController {
    @Autowired
    private SmsService smsService;
    @Autowired
    private UserService userService;
    @Autowired
    private InvitationService invitationService;
    @Autowired
    private StatisticService statisticService;
    @Autowired
    private PresentService presentService;

    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @RequestMapping(value="/sendmessage",method= RequestMethod.GET)
    public void sendmessage(HttpServletRequest request, HttpServletResponse response)throws Exception{
        HttpSession session = request.getSession();
        String tel = request.getParameter("tel");
        try {
            UserPO userPO = userService.userExist(tel);
            if(userPO != null){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("auth", "existence");
                response.getWriter().print(jsonObject);
                return;
            }
        }catch (Exception e){
            logger.error("userService.userExist error in RegisterController");
        }
        String code = smsService.sendMessage(tel, "zh_CN");
        String auth = UUID.randomUUID().toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth",auth);
        session.setAttribute("auth", auth);
        session.setAttribute("tel", tel);
        session.setAttribute("code",code);
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value="/testcode",method=RequestMethod.POST)
    public void testcode(HttpServletRequest request, HttpServletResponse response)throws Exception{
        HttpSession session = request.getSession();
        String tempAuth = (String)session.getAttribute("auth");
        //tel是本次注册用户的手机号，phone是邀请别人的用户的手机号
        String tempId = (String)session.getAttribute("tel");
        String tempCode = (String)session.getAttribute("code");
        JSONObject jsonObject = new JSONObject();
        String auth = request.getParameter("auth");
        String id = request.getParameter("tel");
        String code = request.getParameter("code");

        if(!(tempAuth.equals(auth))){
            jsonObject.put("testpass","no");
            response.getWriter().print(jsonObject);
            return;
        }
        if(!(tempId.equals(id))){
            jsonObject.put("testpass","no");
            response.getWriter().print(jsonObject);
            return;
        }

        if(tempCode.equals(code)){
        	if(request.getParameter("openid")!=null){
        	    String openId = request.getParameter("openid");
        	    System.out.println("openid:::::"+openId);
        	    UserPO userPO = userService.selectUser(openId);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String currentDay = sdf.format(new Date());
                PresentPO presentPO = new PresentPO();
                //若userPO不为null，表明用户之前曾自己点进来过，只需要更新phone和present，但仍要区分此次注册是否由别人邀请而来
        	    if(userPO != null){
                    try{
                        userService.updatePhone(id, openId);
                        userService.updateRemain(0.0,5.0, openId);
                        //在成功给新注册用户加钱后，将这笔赠送金额记录到present表中，目前为5元
                        try{
                            presentPO.setPhone(request.getParameter("tel"));
                            presentPO.setMoney(5.0);
                            presentPO.setType(Constants.REGISTER_PRESENT);
                            presentService.insertPresent(presentPO);
                        }
                        catch (Exception e){
                            logger.error("用户注册插入赠送金额及类型失败，用户 = " + userPO.getPhone());
                        }
                        session.setAttribute("openid", openId);
                        //用户是被邀请而来的
                        if(request.getParameter("phone") != null){
                            invitationService.insertInvitation(request.getParameter("phone"), request.getParameter("tel"));
                            UserPO inviterPO = userService.userExist(request.getParameter("phone"));
                            userService.updateRemain(inviterPO.getRecharge(), inviterPO.getPresent() + 5.0, inviterPO.getOpenId());
                            //在成功给邀请用户加钱后，将这笔赠送金额记录到present表中，目前为5元
                            try{
                                presentPO.setPhone(inviterPO.getPhone());
                                presentPO.setMoney(5.0);
                                presentPO.setType(Constants.INVITE_PRESENT);
                                presentService.insertPresent(presentPO);
                            }
                            catch (Exception e){
                                logger.error("用户邀请插入赠送金额及类型失败，用户 = " + inviterPO.getPhone());
                            }
                            try{
                                //当日统计数据，更新总赠送
                                AuxcalPO auxcalPO = new AuxcalPO();
                                auxcalPO.setGmtCreate(currentDay);
                                auxcalPO.setPresent(10.0);
                                statisticService.updateTodayAuxcal(auxcalPO);
                                //历史统计数据，更新总赠送和总余额
                                TotalPO totalPO = new TotalPO();
                                totalPO.setPresent(10.0);
                                totalPO.setRemain(10.0);
                                statisticService.updateTotalRecharge(totalPO);
                                jsonObject.put("testpass","yes");
                                response.getWriter().print(jsonObject);
                                return;
                            }catch (Exception e){
                                logger.error("邀请注册页更新统计数据失败，"+request.getParameter("phone")+request.getParameter("tel"));
                                jsonObject.put("testpass","no");
                                response.getWriter().print(jsonObject);
                                return;
                            }
                        }
                        //用户不是被邀请而来的
                        if(request.getParameter("phone") == null) {
                            try {
                                //当日统计数据，更新总赠送
                                AuxcalPO auxcalPO = new AuxcalPO();
                                auxcalPO.setGmtCreate(currentDay);
                                auxcalPO.setPresent(5.0);
                                statisticService.updateTodayAuxcal(auxcalPO);
                                //历史统计数据，更新总赠送和总余额
                                TotalPO totalPO = new TotalPO();
                                totalPO.setPresent(5.0);
                                totalPO.setRemain(5.0);
                                statisticService.updateTotalRecharge(totalPO);
                                jsonObject.put("testpass","yes");
                                response.getWriter().print(jsonObject);
                                return;
                            } catch (Exception e) {
                                logger.error("邀请注册页更新统计数据失败，" + request.getParameter("tel"));
                                jsonObject.put("testpass", "no");
                                response.getWriter().print(jsonObject);
                                return;
                            }
                        }

                    }catch (Exception e){
                        logger.error("userService.update phone and remain error!");
                        jsonObject.put("testpass","no");
                        response.getWriter().print(jsonObject);
                        return;
                    }
                //若userPO为null，表明之前用户从未点进来过，则一定是被别人邀请来注册
                }else{
                    UserPO inviteeUserPO = new UserPO();
                    inviteeUserPO.setOpenId(request.getParameter("openid"));
                    inviteeUserPO.setPhone(request.getParameter("tel"));
                    inviteeUserPO.setRecharge(0.0);
                    inviteeUserPO.setPresent(5.0);
                    try{
                        userService.insertUser(inviteeUserPO);
                        //在成功给新注册用户加钱后，将这笔赠送金额记录到present表中，目前为5元
                        try{
                            presentPO.setPhone(inviteeUserPO.getPhone());
                            presentPO.setMoney(5.0);
                            presentPO.setType(Constants.REGISTER_PRESENT);
                            presentService.insertPresent(presentPO);
                        }
                        catch (Exception e){
                            logger.error("用户注册插入赠送金额及类型失败，用户 = " + userPO.getPhone());
                        }
                        invitationService.insertInvitation(request.getParameter("phone"), request.getParameter("tel"));
                        UserPO inviterPO = userService.userExist(request.getParameter("phone"));
                        userService.updateRemain(inviterPO.getRecharge(), inviterPO.getPresent() + 5.0, inviterPO.getOpenId());
                        //在成功给邀请用户加钱后，将这笔赠送金额记录到present表中，目前为5元
                        try{
                            presentPO.setPhone(inviterPO.getPhone());
                            presentPO.setMoney(5.0);
                            presentPO.setType(Constants.INVITE_PRESENT);
                            presentService.insertPresent(presentPO);
                        }
                        catch (Exception e){
                            logger.error("用户邀请插入赠送金额及类型失败，用户 = " + inviterPO.getPhone());
                        }
                        try{
                            //当日统计数据，更新总赠送
                            AuxcalPO auxcalPO = new AuxcalPO();
                            auxcalPO.setGmtCreate(currentDay);
                            auxcalPO.setPresent(10.0);
                            statisticService.updateTodayAuxcal(auxcalPO);
                            //历史统计数据，更新总赠送和总余额
                            TotalPO totalPO = new TotalPO();
                            totalPO.setPresent(10.0);
                            totalPO.setRemain(10.0);
                            statisticService.updateTotalRecharge(totalPO);
                            session.setAttribute("openid", openId);
                            jsonObject.put("testpass","yes");
                            response.getWriter().print(jsonObject);
                            return;
                        }catch (Exception e){
                            logger.error("邀请注册页更新统计数据失败，"+request.getParameter("phone")+request.getParameter("tel"));
                            jsonObject.put("testpass","no");
                            response.getWriter().print(jsonObject);
                            return;
                        }
                    }catch(Exception e){
                        jsonObject.put("testpass","no");
                        response.getWriter().print(jsonObject);
                        return;
                    }
                }

        	}else{
                jsonObject.put("testpass","wrongcode");
                response.getWriter().print(jsonObject);
                return;
            }

        }
    }

}
