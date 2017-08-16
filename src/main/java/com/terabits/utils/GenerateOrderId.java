package com.terabits.utils;

import com.terabits.config.WeixinGlobal;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/27.
 */
public class GenerateOrderId {
    public static String generateOrderId(int k){
            Date date = new Date();
            String orderId = String.format("%tY%<tm%<td%06d", date, k);
            String pre = orderId.substring(0,8);
            System.out.println(pre);
            String post = orderId.substring(8);
            return pre + WeixinGlobal.PRO_NUMBER + post;
    }
}
