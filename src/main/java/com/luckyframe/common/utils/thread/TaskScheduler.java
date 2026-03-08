package com.luckyframe.common.utils.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务调度器
 * @author luckyframe
 * @date 2026-03-08
 */
public class TaskScheduler {
    private static final Logger log = LoggerFactory.getLogger(TaskScheduler.class);
    private static final Map<String, Future<?>> taskFutureMap = new ConcurrentHashMap<>();
    
    /**
     * 提交任务到默认线程池
     * @param task 任务
     * @return 任务执行结果
     */
    public static Future<?> submitTask(Runnable task) {
        return submitTask("default", task);
    }
    
    /**
     * 提交任务到指定线程池
     * @param poolName 线程池名称
     * @param task 任务
     * @return 任务执行结果
     */
    public static Future<?> submitTask(String poolName, Runnable task) {
        Future<?> future = ThreadPoolManager.getThreadPool(poolName).submit(task);
        if (task instanceof PrioritizedTask) {
            log.info("提交带优先级的任务到线程池 {}，优先级: {}", poolName, ((PrioritizedTask) task).getPriority());
        } else {
            log.info("提交任务到线程池: {}", poolName);
        }
        return future;
    }
    
    /**
     * 提交带优先级的任务
     * @param task 带优先级的任务
     * @return 任务执行结果
     */
    public static Future<?> submitPrioritizedTask(PrioritizedTask task) {
        return submitPrioritizedTask("default", task);
    }
    
    /**
     * 提交带优先级的任务到指定线程池
     * @param poolName 线程池名称
     * @param task 带优先级的任务
     * @return 任务执行结果
     */
    public static Future<?> submitPrioritizedTask(String poolName, PrioritizedTask task) {
        Future<?> future = ThreadPoolManager.getPriorityThreadPool(poolName).submit(task);
        log.info("提交带优先级的任务到线程池 {}，优先级: {}", poolName, task.getPriority());
        return future;
    }
    
    /**
     * 提交带任务ID的任务
     * @param taskId 任务ID
     * @param task 任务
     * @return 任务执行结果
     */
    public static Future<?> submitTaskWithId(String taskId, Runnable task) {
        Future<?> future = submitTask(task);
        taskFutureMap.put(taskId, future);
        return future;
    }
    
    /**
     * 取消任务
     * @param taskId 任务ID
     * @return 是否取消成功
     */
    public static boolean cancelTask(String taskId) {
        Future<?> future = taskFutureMap.remove(taskId);
        if (future != null && !future.isDone()) {
            boolean cancelled = future.cancel(true);
            log.info("取消任务 {}: {}", taskId, cancelled ? "成功" : "失败");
            return cancelled;
        }
        return false;
    }
    
    /**
     * 检查任务是否完成
     * @param taskId 任务ID
     * @return 是否完成
     */
    public static boolean isTaskDone(String taskId) {
        Future<?> future = taskFutureMap.get(taskId);
        return future != null && future.isDone();
    }
    
    /**
     * 获取线程池统计信息
     * @param poolName 线程池名称
     * @return 线程池统计信息
     */
    public static ThreadPoolManager.ThreadPoolStats getThreadPoolStats(String poolName) {
        return ThreadPoolManager.getThreadPoolStats(poolName);
    }
    
    /**
     * 调整线程池大小
     * @param poolName 线程池名称
     * @param corePoolSize 核心线程数
     * @param maxPoolSize 最大线程数
     */
    public static void resizeThreadPool(String poolName, int corePoolSize, int maxPoolSize) {
        ThreadPoolManager.resizeThreadPool(poolName, corePoolSize, maxPoolSize);
    }
    
    /**
     * 关闭线程池
     * @param poolName 线程池名称
     */
    public static void shutdownThreadPool(String poolName) {
        ThreadPoolManager.shutdownThreadPool(poolName);
    }
    
    /**
     * 关闭所有线程池
     */
    public static void shutdownAll() {
        ThreadPoolManager.shutdownAll();
    }
}
