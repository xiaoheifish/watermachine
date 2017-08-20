package com.terabits.service.impl;

import com.terabits.config.MailConstant;

import java.util.Properties;

import com.terabits.service.MailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/20.
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

    private JavaMailSenderImpl email;

    private SimpleMailMessage message;

    public MailServiceImpl() {
        // TODO Auto-generated constructor stub
        email = new JavaMailSenderImpl();
        email.setHost(MailConstant.MAIL_HOST);
        email.setUsername(MailConstant.FROM_MAIL);//QQ邮箱账号
        email.setPassword(MailConstant.MAIL_PASSWORD);//QQ邮箱授权码,在网站上设置申请
        email.setPort(465);
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.ssl.enable", true);
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.timeout", 5000);
        email.setJavaMailProperties(properties);
    }

    //发送邮件
    public void send(String content){
        message = new SimpleMailMessage();
        message.setFrom(MailConstant.FROM_MAIL);
        message.setTo(MailConstant.TO_MAIL);
        message.setSubject("智能饮水用户反馈");
        message.setText(content);
        email.send(message);
    }

    //供controller层调用接口
    public void userFeedback(String feedback) {
        MailServiceImpl sendMail = new MailServiceImpl();
        sendMail.send(feedback);
        System.out.println("邮件已发送");
    }

}
