package com.luckyframe.common.utils.thread;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程池管理器
 * @author luckyframe
 * @date 2026-03-08
 */
public class ThreadPoolManager {
    private static final Logger log = LoggerFactory.getLogger(ThreadPoolManager.class);
    private static final Map<String, ThreadPoolExecutor> threadPoolMap = new ConcurrentHashMap<>();
    private static final Map<String, ThreadPoolStats> threadPoolStatsMap = new ConcurrentHashMap<>();
    
    /**
     * 获取默认线程池
     * @return 默认线程池
     */
    public static ThreadPoolExecutor getDefaultThreadPool() {
        return getThreadPool("default");
    }
    
    /**
     * 获取指定名称的线程池
     * @param poolName 线程池名称
     * @return 线程池
     */
    public static ThreadPoolExecutor getThreadPool(String poolName) {
        return threadPoolMap.computeIfAbsent(poolName, name -> createThreadPool(name));
    }
    
    /**
     * 创建线程池
     * @param poolName 线程池名称
     * @return 线程池
     */
    private static ThreadPoolExecutor createThreadPool(String poolName) {
        int corePoolSize = 10;
        int maxPoolSize = 50;
        long keepAliveTime = 60L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(1000);
        ThreadFactory threadFactory = new NamedThreadFactory(poolName);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            corePoolSize,
            maxPoolSize,
            keepAliveTime,
            unit,
            workQueue,
            threadFactory,
            handler
        ) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
                ThreadPoolStats stats = threadPoolStatsMap.computeIfAbsent(poolName, n -> new ThreadPoolStats());
                stats.incrementTaskCount();
            }
            
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                ThreadPoolStats stats = threadPoolStatsMap.get(poolName);
                if (stats != null) {
                    stats.incrementCompletedTaskCount();
                    if (t != null) {
                        stats.incrementFailedTaskCount();
                    }
                }
            }
        };
        
        log.info("创建线程池: {}，核心线程数: {}，最大线程数: {}", poolName, corePoolSize, maxPoolSize);
        return executor;
    }
    
    /**
     * 获取带优先级的线程池
     * @param poolName 线程池名称
     * @return 带优先级的线程池
     */
    public static ThreadPoolExecutor getPriorityThreadPool(String poolName) {
        return threadPoolMap.computeIfAbsent(poolName + "-priority", name -> createPriorityThreadPool(name));
    }
    
    /**
     * 创建带优先级的线程池
     * @param poolName 线程池名称
     * @return 带优先级的线程池
     */
    private static ThreadPoolExecutor createPriorityThreadPool(String poolName) {
        int corePoolSize = 10;
        int maxPoolSize = 50;
        long keepAliveTime = 60L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new PriorityBlockingQueue<>(1000);
        ThreadFactory threadFactory = new NamedThreadFactory(poolName);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            corePoolSize,
            maxPoolSize,
            keepAliveTime,
            unit,
            workQueue,
            threadFactory,
            handler
        );
        
        log.info("创建带优先级的线程池: {}，核心线程数: {}，最大线程数: {}", poolName, corePoolSize, maxPoolSize);
        return executor;
    }
    
    /**
     * 动态调整线程池大小
     * @param poolName 线程池名称
     * @param corePoolSize 核心线程数
     * @param maxPoolSize 最大线程数
     */
    public static void resizeThreadPool(String poolName, int corePoolSize, int maxPoolSize) {
        ThreadPoolExecutor executor = threadPoolMap.get(poolName);
        if (executor != null) {
            executor.setCorePoolSize(corePoolSize);
            executor.setMaximumPoolSize(maxPoolSize);
            log.info("调整线程池 {} 大小: 核心线程数={}, 最大线程数={}", poolName, corePoolSize, maxPoolSize);
        }
    }
    
    /**
     * 关闭线程池
     * @param poolName 线程池名称
     */
    public static void shutdownThreadPool(String poolName) {
        ThreadPoolExecutor executor = threadPoolMap.remove(poolName);
        if (executor != null) {
            executor.shutdown();
            threadPoolStatsMap.remove(poolName);
            log.info("关闭线程池: {}", poolName);
        }
    }
    
    /**
     * 关闭所有线程池
     */
    public static void shutdownAll() {
        for (String poolName : threadPoolMap.keySet()) {
            shutdownThreadPool(poolName);
        }
    }
    
    /**
     * 获取线程池状态
     * @param poolName 线程池名称
     * @return 线程池状态
     */
    public static ThreadPoolStats getThreadPoolStats(String poolName) {
        return threadPoolStatsMap.get(poolName);
    }
    
    /**
     * 线程工厂类
     */
    private static class NamedThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;
        
        NamedThreadFactory(String poolName) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = poolName + "-pool-" + poolNumber.getAndIncrement() + "-thread-";
        }
        
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
    
    /**
     * 线程池统计信息类
     */
    public static class ThreadPoolStats {
        private final AtomicInteger taskCount = new AtomicInteger(0);
        private final AtomicInteger completedTaskCount = new AtomicInteger(0);
        private final AtomicInteger failedTaskCount = new AtomicInteger(0);
        private long lastUpdateTime = System.currentTimeMillis();
        
        public void incrementTaskCount() {
            taskCount.incrementAndGet();
            lastUpdateTime = System.currentTimeMillis();
        }
        
        public void incrementCompletedTaskCount() {
            completedTaskCount.incrementAndGet();
            lastUpdateTime = System.currentTimeMillis();
        }
        
        public void incrementFailedTaskCount() {
            failedTaskCount.incrementAndGet();
            lastUpdateTime = System.currentTimeMillis();
        }
        
        public int getTaskCount() {
            return taskCount.get();
        }
        
        public int getCompletedTaskCount() {
            return completedTaskCount.get();
        }
        
        public int getFailedTaskCount() {
            return failedTaskCount.get();
        }
        
        public long getLastUpdateTime() {
            return lastUpdateTime;
        }
        
        public double getSuccessRate() {
            int total = taskCount.get();
            if (total == 0) {
                return 0;
            }
            return (double) (total - failedTaskCount.get()) / total * 100;
        }
    }
}
