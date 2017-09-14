package com.terabits.service;

import com.github.wxpay.sdk.WXPay;
import com.terabits.config.MyConfig;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by Administrator on 2017/9/12.
 */
public class WeixinTest {
    public static void main(String[] args) throws Exception {

        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);


        Map<String, String> data = new HashMap<String, String>();
        data.put("transaction_id", "4002342001201709121713650661");
        data.put("out_refund_no","400688208821");
        data.put("total_fee","5");
        data.put("refund_fee","5");
        try {
            Map<String, String> resp = wxpay.refund(data);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* 微信退款返回值
    {transaction_id=4002342001201709121686130348,
    nonce_str=aa5MHoaHyOBb0Kns,
    out_refund_no=400688208820,
    sign=D559DB637A945B2C6CB42CA7B246576E,
    return_msg=OK,
    mch_id=1451123102,
    refund_id=50000404282017091201740385819,
    cash_fee=5,
    out_trade_no=2017091263000056,
    coupon_refund_fee=0,
    refund_channel=,
    appid=wx34690a5342af3858,
    refund_fee=5,
    total_fee=5,
    result_code=SUCCESS,
    coupon_refund_count=0,
    cash_refund_fee=5,
    return_code=SUCCESS}
    */
}
