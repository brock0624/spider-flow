package org.spiderflow.wechat.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Author: Brock-Tiyi
 * Date: 2020/10/25 16:57
 * Description:
 * Version: 1.0
 * Knowledge has no limit
 */
@TableName("sp_wechat")
public class Wechat {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String url;

    private Integer timeout;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
