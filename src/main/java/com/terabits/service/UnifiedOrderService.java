package com.terabits.service;

import com.terabits.meta.bo.JsapiConfigBO;

import java.util.SortedMap;

/**
 * Created by Administrator on 2017/6/12.
 * 微信统一下单接口
 */
public interface UnifiedOrderService {
    public SortedMap<String, Object> prepareOrder(String orderId, String openId, int price, String ip);
    public JsapiConfigBO createPayConfig(String prepayId);
}
