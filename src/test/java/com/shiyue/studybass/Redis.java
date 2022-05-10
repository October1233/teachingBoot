package com.shiyue.studybass;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;


@Slf4j
public class Redis {

    public static void main(String[] args) {

        String ssss = "sss";

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!lockUnwait(ssss)){
                    System.out.println("redis锁在使用1");
                    return;
                }
                try {
                    System.out.println("休眠----1----");
                    Thread.sleep(100);
                }catch (Exception e){
                    System.out.println("休眠异常1");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!lockUnwait(ssss)){
                    System.out.println("redis锁在使用");
                    return;
                }
                try {
                    System.out.println("休眠----2----");
                    Thread.sleep(100);
                }catch (Exception e){
                    System.out.println("休眠异常2");
                }
            }
        }).start();

    }


    public static boolean lockUnwait(String subKey) {
        String key = "REDIS-LOCK_UNWAIT" + subKey;
        ShardedJedis jedis = null;
        boolean isLock = false;
        try {
            jedis = getShardedJedis();
            if (jedis == null) {
                return isLock;
            }
            log.debug("lock key: " + key);
            Long i = jedis.setnx(key, key);
            if (i == 1) {
                jedis.expire(key, 2);
                log.debug("get lock, key: " + key + " , expire in " + 2 + " seconds.");
                isLock = true;
            } else {
                if (log.isDebugEnabled()) {
                    String desc = jedis.get(key);
                    log.debug("key: " + key + " locked by another business：" + desc);
                }
                isLock = false;
            }
            return isLock;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        } finally {
            if (jedis != null)
                jedis.disconnect();
        }

    }
    private static final ThreadLocal<ShardedJedis> jdeisContextHolder = new ThreadLocal<ShardedJedis>();

    public static void setShardedJedis(ShardedJedis key) {
        jdeisContextHolder.set(key);
    }

    public static ShardedJedis getShardedJedis() {
        return jdeisContextHolder.get();
    }

}
