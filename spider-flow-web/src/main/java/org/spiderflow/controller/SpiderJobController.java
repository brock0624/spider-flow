package org.spiderflow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spiderflow.core.job.SpiderJobManager;
import org.spiderflow.model.JsonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Brock-Tiyi
 * Date: 2021/3/7 11:48
 * Description:
 * Version: 1.0
 * Knowledge has no limit
 */
@RestController
@RequestMapping("/job")
public class SpiderJobController {
    private static Logger logger = LoggerFactory.getLogger(SpiderJobController.class);

    @Autowired
    private SpiderJobManager spiderJobManager;

    /**
     * 判断定时器是否为待机模式
     */
    @RequestMapping(value = "/isInStandbyMode")
    @ResponseBody
    public JsonBean<Void> isInStandbyMode() {
        boolean result = spiderJobManager.isInStandbyMode();
        if(result){
            return new JsonBean<>(1,"true");
        }
        return new JsonBean<>(0,"false");
    }


    /**
     * 启动定时器
     *
     * @return
     */
    @RequestMapping(value = "startScheduler")
    @ResponseBody
    public JsonBean<Void> startScheduler() {
        boolean result = spiderJobManager.startScheduler();
        if(result){
            return new JsonBean<>(1,"true");
        }
        return new JsonBean<>(0,"false");
    }

    /**
     * 关闭调度器
     *
     * @return
     */
    @RequestMapping(value = "standbyScheduler")
    @ResponseBody
    public JsonBean<Void> standbyScheduler() {
        boolean result = spiderJobManager.standbyScheduler();
        if(result){
            return new JsonBean<>(1,"true");
        }
        return new JsonBean<>(0,"false");
    }

    /**
     * 判断调度器是否为开启状态
     *
     * @return
     */
    @RequestMapping(value = "isStarted")
    @ResponseBody
    public JsonBean<Void> isStarted() {
        boolean result = spiderJobManager.isStarted();
        if(result){
            return new JsonBean<>(1,"true");
        }
        return new JsonBean<>(0,"false");
    }

    /**
     * 判断调度器是否为开启状态
     *
     * @return
     */
    @RequestMapping(value = "isShutdown")
    @ResponseBody
    public JsonBean<Void> isShutdown() {
        boolean result = spiderJobManager.isShutdown();
        if(result){
            return new JsonBean<>(1,"true");
        }
        return new JsonBean<>(0,"false");
    }


}
