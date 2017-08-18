package com.terabits.mapper;

import com.terabits.meta.po.JsapiTicketPO;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */
public interface JsapiTicketMapper {
    //插入新的jsapiTicket
    public int insertJsapi(JsapiTicketPO jsapiTicketPO) throws Exception;

    //更新jsapiTicket
    public int updateJsapi(JsapiTicketPO jsapiTicketPO) throws Exception;

    //取回jsapiTicket
    public JsapiTicketPO selectJsapi() throws Exception;


}
