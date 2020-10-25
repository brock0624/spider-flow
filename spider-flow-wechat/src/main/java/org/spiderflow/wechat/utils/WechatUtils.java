package org.spiderflow.wechat.utils;


import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.spiderflow.wechat.model.Wechat;

import java.io.IOException;

/**
 * Author: Brock-Tiyi
 * Date: 2020/10/25 17:07
 * Description:
 * Version: 1.0
 * Knowledge has no limit
 */
public class WechatUtils {
    public static JSONObject createWechatSender(Wechat wechat, String wechatsckey, String wechatsubject, String wechatcontext) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        String url = wechat.getUrl() + wechatsckey + ".send";
        String content = "text=" + wechatsubject + "&desp=" + wechatcontext;
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();
        return JSONObject.parseObject(response.body().string());
    }
}
