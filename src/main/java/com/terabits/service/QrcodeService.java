package com.terabits.service;

import net.sf.json.JSONObject;

/**
 * Created by Administrator on 2017/9/5.
 */
public interface QrcodeService {
    //根据displayId，生成scene_id = displayId的二维码链接，调用微信生成带参数二维码接口
    public JSONObject getWebId(String displayId);
}
