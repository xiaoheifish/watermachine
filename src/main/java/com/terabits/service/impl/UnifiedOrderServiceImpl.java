package com.terabits.service.impl;

import com.terabits.config.WeixinGlobal;
import com.terabits.meta.bo.JsapiConfigBO;
import com.terabits.service.UnifiedOrderService;
import com.terabits.utils.MapUtils;
import com.terabits.utils.PayCommonUtil;
import com.terabits.utils.Sha1Util;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Administrator on 2017/6/12.
 */
@Service("unifiedOrderService")
public class UnifiedOrderServiceImpl implements UnifiedOrderService{

    public SortedMap<String, Object> prepareOrder(String orderId, String openId, int price, String ip){
        Map<String, Object> oparams = new HashMap<String, Object>();
        oparams.put("appid", WeixinGlobal.APP_ID);//应用号
        oparams.put("body", "智能插座");// 商品描述
        oparams.put("mch_id", WeixinGlobal.MERCHANT_ID);// 商户号
        oparams.put("nonce_str", PayCommonUtil.CreateNoncestr());// 16随机字符串(大小写字母加数字)
        oparams.put("out_trade_no", orderId);// 商户订单号
        oparams.put("openid", openId);
        oparams.put("total_fee", price);// 银行币种支付的钱钱啦
        oparams.put("spbill_create_ip", ip);// IP地址
        oparams.put("notify_url", WeixinGlobal.NOTIFY_URL); // 微信回调地址
        oparams.put("trade_type", "JSAPI");// 支付类型 APP

        return MapUtils.sortMap(oparams);
    }

    public JsapiConfigBO createPayConfig(String prepayId){
        JsapiConfigBO config = new JsapiConfigBO();
        String nonce = Sha1Util.getNonceStr();
        String timestamp = Sha1Util.getTimeStamp();
        String packageName = "prepay_id="+prepayId;
        StringBuffer sign = new StringBuffer();
        sign.append("appId=").append(WeixinGlobal.APP_ID);
        sign.append("&nonceStr=").append(nonce);
        sign.append("&package=").append(packageName);
        sign.append("&signType=").append(config.getSignType());
        sign.append("&timeStamp=").append(timestamp);
        sign.append("&key=").append(WeixinGlobal.API_KEY);
        String signature = DigestUtils.md5Hex(sign.toString()).toUpperCase();
        config.setAppId(WeixinGlobal.APP_ID);
        config.setNonce(nonce);
        config.setTimestamp(timestamp);
        config.setPackageName(packageName);
        config.setSignature(signature);
        return config;
    }
}
