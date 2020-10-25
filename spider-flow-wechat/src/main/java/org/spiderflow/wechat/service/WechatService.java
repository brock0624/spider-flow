package org.spiderflow.wechat.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.spiderflow.wechat.mapper.WechatMapper;
import org.spiderflow.wechat.model.Wechat;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Brock-Tiyi
 * Date: 2020/10/25 17:52
 * Description:
 * Version: 1.0
 * Knowledge has no limit
 */
@Service
public class WechatService extends ServiceImpl<WechatMapper, Wechat> {
    public Map<String, Wechat> cachedWechat = new HashMap<>();

    public Wechat get(String id) {
        synchronized (cachedWechat) {
            if (!cachedWechat.containsKey(id)) {
                cachedWechat.put(id, getBaseMapper().selectById(id));
            }
            return cachedWechat.get(id);
        }
    }

    @Override
    public boolean removeById(Serializable id) {
        synchronized (cachedWechat) {
            cachedWechat.remove(id);
            return super.removeById(id);
        }
    }

    @Override
    public boolean saveOrUpdate(Wechat entity) {
        if (entity.getId() != null) {
            synchronized (cachedWechat) {
                cachedWechat.remove("" + entity.getId());
                return super.saveOrUpdate(entity);
            }
        }
        return super.saveOrUpdate(entity);
    }
}

