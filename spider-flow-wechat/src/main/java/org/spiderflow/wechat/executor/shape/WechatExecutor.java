package org.spiderflow.wechat.executor.shape;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spiderflow.ExpressionEngine;
import org.spiderflow.context.SpiderContext;
import org.spiderflow.executor.ShapeExecutor;
import org.spiderflow.model.Shape;
import org.spiderflow.model.SpiderNode;
import org.spiderflow.wechat.service.WechatService;
import org.spiderflow.wechat.utils.WechatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * Author: Brock-Tiyi
 * Date: 2020/10/25 18:03
 * Description:
 * Version: 1.0
 * Knowledge has no limit
 */
@Component
public class WechatExecutor implements ShapeExecutor {
    private static Logger logger = LoggerFactory.getLogger(WechatExecutor.class);

    /**
     * 数据源类型
     */
    public static final String DATASOURCE_ID = "datasourceId";
    /**
     * 收信人
     */
    private static final String WECHAT_SCKEY = "wechatsckey";

    /**
     * 消息标题
     */
    private static final String WECHAT_SUBJECT = "wechatsubject";

    /**
     * 消息内容
     */
    private static final String WECHAT_CONTEXT = "wechatcontext";

    private String[] wechatSckeys = {};

    @Autowired
    private ExpressionEngine engine;

    @Autowired
    private WechatService wechatService;


    @Override
    public String supportShape() {
        return "wechatsendhtml";
    }

    @Override
    public Shape shape() {
        Shape shape = new Shape();
        // web界面上显示的图标
        shape.setImage("data:image/png;base64,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");
        // 拖放至容器里时默认的节点名称
        shape.setLabel("微信发送");
        // 模板文件名
        shape.setName("wechatsendhtml");
        // 鼠标移动至图标上显示的名称
        shape.setTitle("微信发送");
        return shape;
    }

    @Override
    public void execute(SpiderNode node, SpiderContext context, Map<String, Object> variables) {
        int datasourceId = NumberUtils.toInt(node.getStringJsonValue(DATASOURCE_ID), 0);
        String wechatSckey = node.getStringJsonValue(WECHAT_SCKEY);
        String wechatSubject = node.getStringJsonValue(WECHAT_SUBJECT);
        String wechatContext = node.getStringJsonValue(WECHAT_CONTEXT);
        if (datasourceId == 0) {
            logger.error("请选择发送服务地址！");
        } else if (!StringUtils.isNotBlank(wechatSckey)) {
            logger.error("收信人为空！");
        } else if (!StringUtils.isNotBlank(wechatSubject)) {
            logger.error("消息标题为空！");
        } else if (!StringUtils.isNotBlank(wechatContext)) {
            logger.error("消息内容为空！");
        } else {
            try {
                // 处理收信人变量值
                wechatSckey = engine.execute(wechatSckey, variables).toString();
                wechatSckeys = wechatSckey.split(",");
                logger.debug("设置收信人信息成功！");
            } catch (NullPointerException e) {
                logger.error("收信人为空！");
                return;
            } catch (Exception e1) {
                logger.error("收信人不正确", e1);
                return;
            }
            try {
                // 处理消息标题变量值
                wechatSubject = engine.execute(wechatSubject, variables).toString();
                logger.debug("设置消息标题成功！");
            } catch (NullPointerException e) {
                logger.error("消息标题为空！");
                return;
            } catch (Exception e) {
                logger.error("消息标题格式不正确", e);
                return;
            }
            try {
                // 处理消息内容变量值
                wechatContext = engine.execute(wechatContext, variables).toString();
                logger.debug("设置消息内容成功！");
            } catch (Exception e) {
                logger.error("消息内容格式不正确", e);
                return;
            }
            try {
                logger.info("微信发送中.......请稍等！");
                Collection wechatSckeysList = java.util.Arrays.asList(wechatSckeys);
                for (Object sckey : wechatSckeysList) {
                    JSONObject response = WechatUtils.createWechatSender(wechatService.get(datasourceId + ""), sckey.toString(), wechatSubject, wechatContext);

                    if (response.getInteger("errno") == 0) {
                        logger.debug(sckey + "发送成功！");
                    } else {
                        logger.error(sckey + "发送失败！失败原因:" + response.toString());
                    }
                }

                logger.debug("微信全部发送结束！");
            } catch (Exception e) {
                logger.error("微信发送失败:{}", e);
            }
        }
    }
}
