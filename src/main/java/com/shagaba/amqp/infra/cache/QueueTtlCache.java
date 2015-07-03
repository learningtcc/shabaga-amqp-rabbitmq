package com.shagaba.amqp.infra.cache;

import java.util.Map;
import java.util.UUID;

/**
 * 
 * @author Shai
 *
 */
public class QueueTtlCache {

    protected Map<String, TtlCache<?>> cacheMap;

    /**
     * @param queue
     * @param ttlCache
     */
    public <T> void putCache(String queue, TtlCache<T> ttlCache) {
        this.cacheMap.put(queue, ttlCache);
    }

    /**
     *
     * @param queue
     * @param element
     * @param <T>
     * @return
     */
    public <T> QueueTtlKey put(String queue, T element) {
        TtlCache<T> ttlCache = getTtlCache(queue);
        UUID uuid = ttlCache.put(element);
        return new QueueTtlKey(queue, uuid);
    }

    /**
     *
     * @param queueTtlKey
     * @param <T>
     * @return
     */
    public <T> T getIfPresent(QueueTtlKey queueTtlKey) {
        TtlCache<T> ttlCache = getTtlCache(queueTtlKey.getQeueue());
        if (ttlCache == null) {
            return null;
        }
        return ttlCache.getIfPresent(queueTtlKey.getTtlKey());
    }

    /**
     *
     * @param queue
     * @param uuid
     * @param <T>
     * @return
     */
    public <T> T getIfPresent(String queue, UUID uuid) {
        TtlCache<T> ttlCache = getTtlCache(queue);
        return ttlCache.getIfPresent(uuid);
    }

    /**
     * @param queue
     * @return
     */
    public <T> TtlCache<T> getTtlCache(String queue) {
        return (TtlCache<T>) this.cacheMap.get(queue);
    }
}