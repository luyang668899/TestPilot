package com.luckyframe.project.cloud.service;

import com.luckyframe.project.cloud.domain.CloudTestExecution;

import java.util.List;

/**
 * 云测试执行服务接口
 * 
 * @author ruoyi
 * @date 2023-09-15
 */
public interface ICloudTestExecutionService
{
    /**
     * 查询云测试执行
     * 
     * @param executionId 云测试执行ID
     * @return 云测试执行
     */
    public CloudTestExecution selectCloudTestExecutionByExecutionId(Integer executionId);

    /**
     * 查询云测试执行列表
     * 
     * @param cloudTestExecution 云测试执行
     * @return 云测试执行集合
     */
    public List<CloudTestExecution> selectCloudTestExecutionList(CloudTestExecution cloudTestExecution);

    /**
     * 新增云测试执行
     * 
     * @param cloudTestExecution 云测试执行
     * @return 结果
     */
    public int insertCloudTestExecution(CloudTestExecution cloudTestExecution);

    /**
     * 修改云测试执行
     * 
     * @param cloudTestExecution 云测试执行
     * @return 结果
     */
    public int updateCloudTestExecution(CloudTestExecution cloudTestExecution);

    /**
     * 删除云测试执行
     * 
     * @param executionId 云测试执行ID
     * @return 结果
     */
    public int deleteCloudTestExecutionByExecutionId(Integer executionId);

    /**
     * 批量删除云测试执行
     * 
     * @param executionIds 需要删除的云测试执行ID
     * @return 结果
     */
    public int deleteCloudTestExecutionByExecutionIds(Integer[] executionIds);

    /**
     * 根据服务ID查询云测试执行列表
     * 
     * @param serviceId 服务ID
     * @return 云测试执行集合
     */
    public List<CloudTestExecution> selectCloudTestExecutionByServiceId(Integer serviceId);

    /**
     * 根据资源ID查询云测试执行列表
     * 
     * @param resourceId 资源ID
     * @return 云测试执行集合
     */
    public List<CloudTestExecution> selectCloudTestExecutionByResourceId(Integer resourceId);

    /**
     * 执行云测试
     * 
     * @param serviceId 服务ID
     * @param resourceId 资源ID
     * @param testTaskId 测试任务ID
     * @return 执行ID
     */
    public Integer executeCloudTest(Integer serviceId, Integer resourceId, Integer testTaskId);

    /**
     * 获取测试执行状态
     * 
     * @param executionId 执行ID
     * @return 状态
     */
    public String getTestExecutionStatus(Integer executionId);

    /**
     * 获取测试执行结果
     * 
     * @param executionId 执行ID
     * @return 结果
     */
    public String getTestExecutionResult(Integer executionId);

    /**
     * 停止测试执行
     * 
     * @param executionId 执行ID
     * @return 结果
     */
    public boolean stopTestExecution(Integer executionId);
}
