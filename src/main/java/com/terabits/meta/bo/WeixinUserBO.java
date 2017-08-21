package com.terabits.meta.bo;

/**
 * 用户从微信对话框唯一入口进入时，需要查询phone来判断该用户是否已经注册，需要查询language来决定前端显示的语言，
 * 因此将这两个字段组合成一个Business Object
 * Created by Administrator on 2017/8/19.
 */
public class WeixinUserBO {
    //用户手机号
    private String phone = null;
    //用户所用语言
    private String language;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "WeixinUserBO[" +
                "phone='" + phone + '\'' +
                ", language='" + language + '\'' +
                ']';
    }
}
