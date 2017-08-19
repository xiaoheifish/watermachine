package com.terabits.service;

import com.aliyuncs.exceptions.ClientException;

/**
 * Created by Administrator on 2017/8/19.
 */
public interface SmsService {

    //给定用户手机号和使用语言，发送验证码
    public String sendMessage(String number, String language) throws ClientException, InterruptedException;
}
