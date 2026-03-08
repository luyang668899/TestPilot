package com.luckyframe.common.utils.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.luckyframe.project.system.client.domain.Client;
import com.luckyframe.project.testexecution.taskScheduling.domain.TaskScheduling;

/**
 * 任务分配服务
 * @author luckyframe
 * @date 2026-03-08
 */
public class TaskAssignmentService {
    private static final Logger log = LoggerFactory.getLogger(TaskAssignmentService.class);
    
    /**
     * 根据任务调度信息选择最合适的客户端
     * @param taskScheduling 任务调度信息
     * @param availableClients 可用客户端列表
     * @return 选中的客户端
     */
    public static Client selectBestClient(TaskScheduling taskScheduling, List<Client> availableClients) {
        if (availableClients == null || availableClients.isEmpty()) {
            log.warn("没有可用的客户端");
            return null;
        }
        
        // 过滤出状态正常的客户端
        List<Client> healthyClients = new ArrayList<>();
        for (Client client : availableClients) {
            if (client.getStatus() == 0) { // 0表示状态正常
                healthyClients.add(client);
            }
        }
        
        if (healthyClients.isEmpty()) {
            log.warn("没有状态正常的客户端");
            return null;
        }
        
        // 为每个客户端计算负载分数
        List<ClientLoadMonitor.ClientLoad> clientLoads = new ArrayList<>();
        for (Client client : healthyClients) {
            ClientLoadMonitor.ClientLoad load = ClientLoadMonitor.getClientLoad(client.getClientIp());
            if (load == null) {
                // 如果客户端还没有负载信息，创建一个新的
                ClientLoadMonitor.registerClient(client);
                load = ClientLoadMonitor.getClientLoad(client.getClientIp());
            }
            clientLoads.add(load);
        }
        
        // 根据负载分数排序，选择负载最小的客户端
        clientLoads.sort(Comparator.comparingDouble(ClientLoadMonitor.ClientLoad::getLoadScore));
        
        String bestClientIp = clientLoads.get(0).getClientIp();
        for (Client client : healthyClients) {
            if (client.getClientIp().equals(bestClientIp)) {
                log.info("为任务 {} 选择客户端: {}", taskScheduling.getSchedulingName(), client.getClientName());
                return client;
            }
        }
        
        return healthyClients.get(0);
    }
    
    /**
     * 批量分配任务到多个客户端
     * @param taskSchedulings 任务调度列表
     * @param availableClients 可用客户端列表
     * @return 任务分配结果，key为客户端IP，value为分配的任务列表
     */
    public static Map<String, List<TaskScheduling>> assignTasks(List<TaskScheduling> taskSchedulings, List<Client> availableClients) {
        Map<String, List<TaskScheduling>> assignmentResult = new java.util.HashMap<>();
        
        for (TaskScheduling task : taskSchedulings) {
            Client bestClient = selectBestClient(task, availableClients);
            if (bestClient != null) {
                String clientIp = bestClient.getClientIp();
                if (!assignmentResult.containsKey(clientIp)) {
                    assignmentResult.put(clientIp, new ArrayList<>());
                }
                assignmentResult.get(clientIp).add(task);
                
                // 更新客户端的当前任务数
                ClientLoadMonitor.ClientLoad load = ClientLoadMonitor.getClientLoad(clientIp);
                if (load != null) {
                    load.setCurrentTasks(load.getCurrentTasks() + 1);
                }
            }
        }
        
        return assignmentResult;
    }
    
    /**
     * 故障转移：当客户端不可用时，将任务重新分配到其他客户端
     * @param failedClientIp 失败的客户端IP
     * @param task 任务调度信息
     * @param availableClients 可用客户端列表
     * @return 新的客户端
     */
    public static Client failoverTask(String failedClientIp, TaskScheduling task, List<Client> availableClients) {
        log.warn("客户端 {} 执行任务失败，开始故障转移", failedClientIp);
        
        // 从可用客户端中排除失败的客户端
        List<Client> alternativeClients = new ArrayList<>();
        for (Client client : availableClients) {
            if (!client.getClientIp().equals(failedClientIp)) {
                alternativeClients.add(client);
            }
        }
        
        // 选择新的客户端
        Client newClient = selectBestClient(task, alternativeClients);
        if (newClient != null) {
            log.info("故障转移成功，任务 {} 重新分配到客户端: {}", task.getSchedulingName(), newClient.getClientName());
        } else {
            log.error("故障转移失败，没有可用的客户端");
        }
        
        return newClient;
    }
}
