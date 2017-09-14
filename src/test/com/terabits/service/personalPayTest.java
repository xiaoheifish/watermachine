package com.terabits.service;

import com.terabits.config.MyConfig;
import com.terabits.config.MyWXPay;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/12.
 */
public class personalPayTest {
    public static void main(String[] args) throws Exception {

        MyConfig config = new MyConfig();
        MyWXPay wxpay = new MyWXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("partner_trade_no", "40023420012017091217");
        data.put("openid","o1S07wotESY-e5nUxo1GZQL9LZKo");
        data.put("check_name","NO_CHECK");
        data.put("amount", "200");
        data.put("desc","退款");
        data.put("spbill_create_ip","119.23.210.52");
        try {
            Map<String, String> resp = wxpay.personalPay(data);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
