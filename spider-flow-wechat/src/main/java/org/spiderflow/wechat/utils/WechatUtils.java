package org.spiderflow.wechat.utils;

import okhttp3.*;
import org.spiderflow.wechat.model.Wechat;

/**
 * Author: Brock-Tiyi
 * Date: 2020/10/25 17:07
 * Description:
 * Version: 1.0
 * Knowledge has no limit
 */
public class WechatUtils {
    public static String createWechatSender(Wechat wechat) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "text=消息标题，最长为256&desp=消息内容，最长64Kb，可空，支持MarkDown");
        Request request = new Request.Builder()
                .url("https://sc.ftqq.com/SCU74937T6ba586802a797ab3068e1af8f15b13a55e0c81b84ac34.send")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Cookie", "PHPSESSID=4a62f137d2cd4b6be12c81f991c323b0")
                .build();
//        Response response = client.newCall(request).execute();

        return "";
    }
}
