package com.luckyframe.common.utils.thread;

/**
 * 带优先级的任务接口
 * @author luckyframe
 * @date 2026-03-08
 */
public interface PrioritizedTask extends Runnable, Comparable<PrioritizedTask> {
    /**
     * 获取任务优先级，值越小优先级越高
     * @return 优先级
     */
    int getPriority();
    
    @Override
    default int compareTo(PrioritizedTask other) {
        return Integer.compare(this.getPriority(), other.getPriority());
    }
}
