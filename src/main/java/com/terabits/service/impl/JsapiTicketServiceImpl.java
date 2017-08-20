package com.terabits.service.impl;

import com.terabits.mapper.JsapiTicketMapper;
import com.terabits.meta.po.AccessTokenPO;
import com.terabits.meta.po.JsapiTicketPO;
import com.terabits.service.AccessTokenService;
import com.terabits.service.JsapiTicketService;
import com.terabits.utils.WeixinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/29.
 */
@Service("jsapiTicketService")
public class JsapiTicketServiceImpl implements JsapiTicketService {

    @Autowired(required = false)
    private JsapiTicketMapper jsapiTicketMapper;
    @Autowired
    private AccessTokenService accessTokenService;

    public int insertJsapi(JsapiTicketPO jsapiTicketPO)throws Exception{
        return jsapiTicketMapper.insertJsapi(jsapiTicketPO);
    }

    public int updateJsapi(JsapiTicketPO jsapiTicketPO)throws Exception{
        return jsapiTicketMapper.updateJsapi(jsapiTicketPO);
    }
    public JsapiTicketPO getLatestJsapi() throws Exception{
        JsapiTicketPO jsapiTicketPO = null;
        try {
            jsapiTicketPO = jsapiTicketMapper.selectJsapi();
        }catch (Exception e){
            e.printStackTrace();
        }
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long between = 0;
        try {
            Date begin =dfs.parse(jsapiTicketPO.getGmtCreate());
            Date end = new Date();
            between = (end.getTime() - begin.getTime())/1000;// 得到两者的秒数
            System.out.println(between);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(between < 10){
            return jsapiTicketPO;
        }
        else {
            AccessTokenPO accessTokenPO1 = accessTokenService.getLatestToken();
            String jsapiTicket = WeixinUtil.getJsapiTicket(accessTokenPO1.getAccessToken());
            JsapiTicketPO jsapiTicketPO1 = new JsapiTicketPO();
            jsapiTicketPO1.setJsapiTicket(jsapiTicket);
            jsapiTicketMapper.updateJsapi(jsapiTicketPO1);
            return jsapiTicketPO1;
        }
    }
}
