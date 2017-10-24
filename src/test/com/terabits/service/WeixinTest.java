package com.terabits.service;

import com.github.wxpay.sdk.WXPay;
import com.terabits.config.MyConfig;
import com.terabits.config.MyWXPay;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by Administrator on 2017/9/12.
 */
public class WeixinTest {
    public static void main(String[] args) throws Exception {

        MyConfig config = new MyConfig();
        MyWXPay wxpay = new MyWXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("partner_trade_no", "2017102325000013");
        data.put("openid", "o1S07wuDO9ivY_55p3OT4bEMNUL0");
        data.put("check_name", "NO_CHECK");
        data.put("amount", "200");
        data.put("desc", "退款");
        data.put("spbill_create_ip", "119.23.210.52");
        try {
            Map<String, String> resp = wxpay.personalPay(data);
            if (resp.get("payment_no") != null) {
                //退款成功，更新退款订单状态，更新用户余额
                System.out.println("paymentNo="+resp.get("payment_no"));
            }
            if (resp.get("err_code") != null) {
                System.out.println("errorCode"+resp.get("err_code"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      /*  MyConfig config = new MyConfig();
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
        }*/
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
