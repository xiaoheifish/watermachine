package com.terabits.config;

import com.github.wxpay.sdk.WXPay;

import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants.SignType;
import com.github.wxpay.sdk.WXPayUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/**
 * Created by Administrator on 2017/9/12.
 */
public class MyWXPay
{
    private WXPayConfig config;
    private SignType signType;
    private boolean useSandbox;

    public MyWXPay(WXPayConfig config) {
        this(config, SignType.MD5, false);
    }

    public MyWXPay(WXPayConfig config, SignType signType) {
        this(config, signType, false);
    }

    public MyWXPay(WXPayConfig config, SignType signType, boolean useSandbox) {
        this.config = config;
        this.signType = signType;
        this.useSandbox = useSandbox;
    }

    public boolean isResponseSignatureValid(Map<String, String> reqData) throws Exception {
        return WXPayUtil.isSignatureValid(reqData, this.config.getKey(), this.signType);
    }

    public Map<String, String> fillRequestData(Map<String, String> reqData) throws Exception {
        reqData.put("mch_appid", this.config.getAppID());
        reqData.put("mchid", this.config.getMchID());
        String nonce_str =  WXPayUtil.generateNonceStr();
        //String nonce_str = "a11843e9820b4702950c1ad11100d7fc";
        reqData.put("nonce_str", nonce_str);
        System.out.println("nonce_str:" + nonce_str);
        if(SignType.MD5.equals(this.signType)) {
            //reqData.put("sign_type", "MD5");
        } else if(SignType.HMACSHA256.equals(this.signType)) {
            reqData.put("sign_type", "HMAC-SHA256");
        }
        String sign = WXPayUtil.generateSignature(reqData, this.config.getKey(), this.signType);
        reqData.put("sign", sign);
        System.out.println("sign:" + sign);
        return reqData;
    }

    public Map<String, String> processResponseXml(String xmlStr) throws Exception {
        String RETURN_CODE = "return_code";
        Map<String, String> respData = WXPayUtil.xmlToMap(xmlStr);
        if(respData.containsKey(RETURN_CODE)) {
            String return_code = (String)respData.get(RETURN_CODE);
            if(return_code.equals("FAIL")) {
                return respData;
            } else if(return_code.equals("SUCCESS")) {
                if(this.isResponseSignatureValid(respData)) {
                    return respData;
                } else {
                    //throw new Exception(String.format("Invalid sign value in XML: %s", new Object[]{xmlStr}));
                    return respData;
                }
            } else {
                throw new Exception(String.format("return_code value %s is invalid in XML: %s", new Object[]{return_code, xmlStr}));
            }
        } else {
            throw new Exception(String.format("No `return_code` in XML: %s", new Object[]{xmlStr}));
        }
    }

    public String requestWithCert(String strUrl, Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String UTF8 = "UTF-8";
        String reqBody = WXPayUtil.mapToXml(reqData);
        System.out.println("reqBody:" + reqBody);
        URL httpUrl = new URL(strUrl);
        char[] password = this.config.getMchID().toCharArray();
        InputStream certStream = this.config.getCertStream();
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(certStream, password);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, password);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), (TrustManager[])null, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HttpURLConnection httpURLConnection = (HttpURLConnection)httpUrl.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(connectTimeoutMs);
        httpURLConnection.setReadTimeout(readTimeoutMs);
        httpURLConnection.connect();
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(reqBody.getBytes(UTF8));
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, UTF8));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;

        while((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }

        String resp = stringBuffer.toString();
        if(stringBuffer != null) {
            try {
                bufferedReader.close();
            } catch (IOException var24) {
                ;
            }
        }

        if(inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException var23) {
                ;
            }
        }

        if(outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException var22) {
                ;
            }
        }

        if(certStream != null) {
            try {
                certStream.close();
            } catch (IOException var21) {
                ;
            }
        }

        return resp;
    }

    public Map<String, String> personalPay(Map<String, String> reqData) throws Exception{
        return this.personalPay(reqData, this.config.getHttpConnectTimeoutMs(), this.config.getHttpReadTimeoutMs());
    }

    public Map<String, String> personalPay(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception{
        String url;
        if(this.useSandbox){
            url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
        }else{
            url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
        }
        String respXml = this.requestWithCert(url, this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
        return this.processResponseXml(respXml);
    }

}
