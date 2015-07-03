package com.shagaba.amqp.infra.cache;

import java.util.UUID;

/**
 * 
 * @author Shai
 *
 */
public class QueueTtlKey {

    protected String qeueue;

    protected UUID ttlKey;

    /**
     *
     */
    public QueueTtlKey() {
    }

    /**
     *
     * @param qeueue
     * @param ttlKey
     */
    public QueueTtlKey(String qeueue, UUID ttlKey) {
        this.qeueue = qeueue;
        this.ttlKey = ttlKey;
    }

    public String getQeueue() {
        return qeueue;
    }

    public void setQeueue(String qeueue) {
        this.qeueue = qeueue;
    }

    public UUID getTtlKey() {
        return ttlKey;
    }

    public void setTtlKey(UUID ttlKey) {
        this.ttlKey = ttlKey;
    }

}
