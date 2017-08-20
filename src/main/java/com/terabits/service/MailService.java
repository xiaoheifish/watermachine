package com.terabits.service;

/**
 * Created by Administrator on 2017/8/20.
 */
public interface MailService {
    /**
     * 发送邮件函数，feedback为反馈内容
     * @param feedback
     */
    public void userFeedback(String feedback);
}
