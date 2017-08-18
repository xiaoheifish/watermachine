package com.terabits.service;

import com.terabits.meta.po.JsapiTicketPO;

/**
 * Created by Administrator on 2017/6/29.
 */
public interface JsapiTicketService {

    //插入jsapiTicket
    public int insertJsapi(JsapiTicketPO jsapiTicketPO)throws Exception;

    //更新jsapiTicket
    public int updateJsapi(JsapiTicketPO jsapiTicketPO)throws Exception;

    //获取最新有效的jsapiTicket
    public JsapiTicketPO getLatestJsapi() throws Exception;
}
