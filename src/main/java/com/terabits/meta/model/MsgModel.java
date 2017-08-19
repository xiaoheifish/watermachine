package com.terabits.meta.model;

/**
 * Created by Administrator on 2017/8/19.
 * 短信模板模型
 */
public class MsgModel {
    //待下发用户手机号
    private String number;
    //待下发验证码
    private String code;
    //短信签名
    private String signature;
    //短信模板
    private String template;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public String toString() {
        return "MsgModel[" +
                "number='" + number + '\'' +
                ", code='" + code + '\'' +
                ", signature='" + signature + '\'' +
                ", template='" + template + '\'' +
                ']';
    }
}
