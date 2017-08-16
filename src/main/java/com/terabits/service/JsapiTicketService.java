package com.terabits.service;

import com.terabits.meta.po.JsapiTicketPO;

/**
 * Created by Administrator on 2017/6/29.
 */
public interface JsapiTicketService {

    //插入新获取到的jsapi_ticket
    public int insertJsapi(JsapiTicketPO jsapiTicketPO);

    //获取最新可用的jsapi_ticket
    public JsapiTicketPO getLatestJsapi();
}
