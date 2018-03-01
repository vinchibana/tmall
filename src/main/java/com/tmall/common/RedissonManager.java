package com.tmall.common;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author qiuxin
 */
@Slf4j
@Component
public class RedissonManager {

    private Config config = new Config();
    private Redisson redisson = null;

    @PostConstruct
    private void init() {
        try {
            config.useSingleServer().setAddress("127.0.0.1:6379");
            redisson = (Redisson) Redisson.create(config);
            log.info("初始化 Redisson 结束");
        } catch (Exception e) {
            log.error("初始化 Redisson 错误", e);
        }

    }

    public Redisson getRedisson() {
        return redisson;
    }
}
