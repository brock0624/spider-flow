package org.spiderflow.wechat.controller;

import org.spiderflow.common.CURDController;
import org.spiderflow.executor.PluginConfig;
import org.spiderflow.model.JsonBean;
import org.spiderflow.model.Plugin;
import org.spiderflow.wechat.mapper.WechatMapper;
import org.spiderflow.wechat.model.Wechat;
import org.spiderflow.wechat.service.WechatService;
import org.spiderflow.wechat.utils.WechatUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Brock-Tiyi
 * Date: 2020/10/25 18:07
 * Description:
 * Version: 1.0
 * Knowledge has no limit
 */
@RestController
@RequestMapping("wechat")
public class WechatController extends CURDController<WechatService, WechatMapper, Wechat> implements PluginConfig {

    @RequestMapping("/test")
    public JsonBean<String> test(Wechat wechat, String sckey){
        try {
            String res=WechatUtils.createWechatSender(wechat);

            return new JsonBean<String>(1, "测试成功");
        } catch (Exception e) {
            return new JsonBean<String>(-1, e.getMessage());
        }
    }

    @Override
    public Plugin plugin() {
        return new Plugin("微信配置", "resources/wechatList.html");
    }
}
