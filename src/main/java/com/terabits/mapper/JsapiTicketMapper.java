package com.terabits.mapper;

import com.terabits.meta.po.JsapiTicketPO;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */
public interface JsapiTicketMapper {
    //插入新的token
    public int insertJsapi(JsapiTicketPO jsapiTicketPO) throws Exception;
    //取回最后一个token
    public JsapiTicketPO selectLastJsapi() throws Exception;
    //取回全部token
    public List<JsapiTicketPO> selectAllJsapi() throws Exception;
}
