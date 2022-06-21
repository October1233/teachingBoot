package com.demoPack.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class FlashLock {

    // 获取锁（这回填好骨肉了）
    public void lock() {
        sync.acquire(1);
    }
    // 释放锁
    public void unlock() {
        sync.release(1);
    }

    private final Sync sync = new Sync();

    // 这个内部类就是继承并实现了 AQS 但我这里只先实现两个方法
    private static class Sync extends AbstractQueuedSynchronizer {

        @Override
        public boolean tryAcquire(int acquires) {
            // CAS 方式尝试获取锁，成功返回true，失败返回false
            if (compareAndSetState(0, 1)) {
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int releases) {
            // 释放锁，这里为什么不像上面那样也是 CAS 操作呢？请读者思考
            setState(0);
            return true;
        }
    }
}
