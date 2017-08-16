package com.terabits.config;

/**
 * Created by Administrator on 2017/6/5.
 */
public class WeixinGlobal {
    //微信平台的appid
    public static final String APP_ID = "wx34690a5342af3858";
    //微信平台的appsecret
    public static final String APP_SECRET = "7bed20d3cf6ff73cb361b88e96c350d9";
    //微信支付的商户id
    public static final String MERCHANT_ID = "1451123102";
    //微信支付的api key, 32位, 自己在商户平台设置的
    public static final String API_KEY = "KPcN14hIaXOrfMfPwfWmKfttLFZK196B";
    //统一下单URL
    public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //微信支付回调URL, 需告知微信操作成功
    public static final String NOTIFY_URL= "http://www.terabits-wx.cn/watermachine/notify";
    //获取JS_API TICKET 的URL
    public static final String JSAPI_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    public int orderNum = 0;

    public static String SUCCESS = "SUCCESS"; //成功return_code

    public static String FAIL = "FAIL";   //失败return_code

    public static String PRODUCT_BODY = "xx时长充值卡"; // 商品描述

    public static String FEBDA_PAY_BODY = "xx分答支付"; // 商品描述

    public static String TRADE_TYPE = "JSAPI";//支付类型 JSAPI NATIVE(扫码支付) APP等等

    public static String SIGN_TYPE = "MD5";//签名类型

    public static String EPAY_DESC = "xxxx";//企业付款描述

    //项目标号
    public static final String PRO_NUMBER= "6663";

}
