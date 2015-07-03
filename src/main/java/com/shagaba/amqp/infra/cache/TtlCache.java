package com.shagaba.amqp.infra.cache;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;

/**
 * @author Shai
 *
 * @param <T>
 */
public class TtlCache<T> {
	
	protected long maximumSize;
	
	protected long timeToLive;
	
	protected Cache<UUID,T> cache;
	
	/**
	 * @param maximumSize
	 * @param timeToLive
	 */
	public TtlCache(long maximumSize, long timeToLive) {
		this.maximumSize = maximumSize;
		this.timeToLive = timeToLive;
		this.cache = CacheBuilder.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterWrite(timeToLive, TimeUnit.SECONDS)
                .build();
	}
	
	/**
	 * @param maximumSize
	 * @param timeToLive
	 * @param listener
	 */
	public TtlCache(long maximumSize, long timeToLive, RemovalListener<UUID, T> listener) {
		this.maximumSize = maximumSize;
		this.timeToLive = timeToLive;
		this.cache = CacheBuilder.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterWrite(timeToLive, TimeUnit.SECONDS)
                .removalListener(listener)
                .build();
	}
	
	/**
	 * @param element
	 * @return
	 */
	public UUID put(T element) {
		UUID uuid = UUID.randomUUID();
		cache.put(uuid, element);
		return uuid;
	}
	
	/**
	 * @param uuid
	 * @return
	 */
	public T getIfPresent(UUID uuid) {
		return cache.getIfPresent(uuid);
	}

	/**
	 * 
	 */
	public void cleanUp() {
		cache.cleanUp();
	}

	/**
	 * @param uuid
	 */
	public void invalidate(UUID uuid) {
		cache.invalidate(uuid);
	}

	/**
	 * 
	 */
	public void invalidateAll() {
		cache.invalidateAll();
	}

	/**
	 * 
	 */
	public long size() {
		return cache.size();
	}

	/**
	 * @return the maximumSize
	 */
	public long getMaximumSize() {
		return maximumSize;
	}

	/**
	 * @return the timeToLive
	 */
	public long getTimeToLive() {
		return timeToLive;
	}

}
