package com.tmall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TokenCache {

    public static final String TOKEN_PREFIX = "token_";

    /**
     * LRU
     * 1. 新数据插入到链表头部；
     * 2. 每当缓存命中（即缓存数据被访问），则将数据移到链表头部；
     * 3. 当链表满的时候，将链表尾部的数据丢弃。
     */
    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS).build(new CacheLoader<String, String>() {
        @Override
        public String load(String key) throws Exception {
            return "null";
        }
    });

    public static void setKey(String key, String value) {
        localCache.put(key, value);
    }

    public static String getKey(String key) {
        String value = null;
        try {
            value = localCache.get(key);
            if ("null".equals(value)) {
                return null;
            }
            return value;
        } catch (Exception e) {
            log.error("localCache get ERROR", e);
        }
        return null;
    }
}
