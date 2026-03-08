package com.luckyframe.common.utils.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.luckyframe.project.system.client.domain.Client;

/**
 * 客户端负载监控服务
 * @author luckyframe
 * @date 2026-03-08
 */
public class ClientLoadMonitor {
    private static final Logger log = LoggerFactory.getLogger(ClientLoadMonitor.class);
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
    private static final Map<String, ClientLoad> clientLoadMap = new ConcurrentHashMap<>();
    
    static {
        // 启动定期更新客户端负载的任务
        executorService.scheduleAtFixedRate(() -> {
            try {
                updateClientLoad();
            } catch (Exception e) {
                log.error("更新客户端负载失败", e);
            }
        }, 0, 30, TimeUnit.SECONDS);
    }
    
    /**
     * 更新客户端负载信息
     */
    private static void updateClientLoad() {
        // 这里可以通过HTTP请求获取客户端的负载信息
        // 暂时使用模拟数据
        for (String clientIp : clientLoadMap.keySet()) {
            ClientLoad load = clientLoadMap.get(clientIp);
            // 模拟负载数据更新
            load.setCpuUsage(Math.random() * 100);
            load.setMemoryUsage(Math.random() * 100);
            load.setCurrentTasks((int) (Math.random() * 10));
            load.setLastUpdateTime(System.currentTimeMillis());
        }
    }
    
    /**
     * 注册客户端
     * @param client 客户端对象
     */
    public static void registerClient(Client client) {
        if (client != null && client.getClientIp() != null) {
            clientLoadMap.put(client.getClientIp(), new ClientLoad(client.getClientIp()));
            log.info("客户端注册成功: {}", client.getClientIp());
        }
    }
    
    /**
     * 注销客户端
     * @param clientIp 客户端IP
     */
    public static void unregisterClient(String clientIp) {
        if (clientIp != null) {
            clientLoadMap.remove(clientIp);
            log.info("客户端注销成功: {}", clientIp);
        }
    }
    
    /**
     * 获取客户端负载信息
     * @param clientIp 客户端IP
     * @return 客户端负载信息
     */
    public static ClientLoad getClientLoad(String clientIp) {
        return clientLoadMap.get(clientIp);
    }
    
    /**
     * 获取所有客户端的负载信息
     * @return 客户端负载信息映射
     */
    public static Map<String, ClientLoad> getAllClientLoad() {
        return clientLoadMap;
    }
    
    /**
     * 客户端负载信息类
     */
    public static class ClientLoad {
        private String clientIp;
        private double cpuUsage;
        private double memoryUsage;
        private int currentTasks;
        private long lastUpdateTime;
        
        public ClientLoad(String clientIp) {
            this.clientIp = clientIp;
            this.cpuUsage = 0;
            this.memoryUsage = 0;
            this.currentTasks = 0;
            this.lastUpdateTime = System.currentTimeMillis();
        }
        
        // getter and setter methods
        public String getClientIp() {
            return clientIp;
        }
        
        public void setClientIp(String clientIp) {
            this.clientIp = clientIp;
        }
        
        public double getCpuUsage() {
            return cpuUsage;
        }
        
        public void setCpuUsage(double cpuUsage) {
            this.cpuUsage = cpuUsage;
        }
        
        public double getMemoryUsage() {
            return memoryUsage;
        }
        
        public void setMemoryUsage(double memoryUsage) {
            this.memoryUsage = memoryUsage;
        }
        
        public int getCurrentTasks() {
            return currentTasks;
        }
        
        public void setCurrentTasks(int currentTasks) {
            this.currentTasks = currentTasks;
        }
        
        public long getLastUpdateTime() {
            return lastUpdateTime;
        }
        
        public void setLastUpdateTime(long lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }
        
        /**
         * 计算客户端负载分数，分数越低表示负载越小
         * @return 负载分数
         */
        public double getLoadScore() {
            // 综合考虑CPU使用率、内存使用率和当前任务数
            return cpuUsage * 0.4 + memoryUsage * 0.4 + currentTasks * 2.0;
        }
    }
}
