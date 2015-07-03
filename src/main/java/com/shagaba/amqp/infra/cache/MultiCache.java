package com.shagaba.amqp.infra.cache;

import java.util.Map;
import java.util.UUID;

public class MultiCache<K> {

	protected Map<K, TtlCache<?>> cacheMap;
	
	/**
	 * @param key
	 * @param ttlCache
	 */
	public <T> void putCache(K key, TtlCache<T> ttlCache) {
		this.cacheMap.put(key, ttlCache);
	}
	
	/**
	 * @param element
	 * @return
	 */
	public <T> UUID put(K key, T element) {
		TtlCache<T> ttlCache = getTtlCache(key);
		return ttlCache.put(element);
	}
	
	/**
	 * @param uuid
	 * @return
	 */
	public <T> T getIfPresent(K key, UUID uuid) {
		TtlCache<T> ttlCache = getTtlCache(key);
		return ttlCache.getIfPresent(uuid);
	}

	/**
	 * @param key
	 * @return
	 */
	public <T> TtlCache<T> getTtlCache(K key) {
		return (TtlCache<T>) this.cacheMap.get(key);
	}
}
