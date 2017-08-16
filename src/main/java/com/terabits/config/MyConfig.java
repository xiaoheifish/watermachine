package com.terabits.config;

/**
 * Created by Administrator on 2017/6/30.
 */
import com.github.wxpay.sdk.WXPayConfig;
import java.io.*;
public class MyConfig implements WXPayConfig {
    private byte[] certData;

    public MyConfig() throws Exception {
        String certPath = "E://wxcert//apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public String getAppID() {
        return "wx34690a5342af3858";
    }

    public String getMchID() {
        return "1451123102";
    }

    public String getKey() {
        return "KPcN14hIaXOrfMfPwfWmKfttLFZK196B";
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

}
