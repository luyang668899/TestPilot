package com.luckyframe.project.cloud.service;

import com.luckyframe.project.cloud.domain.CloudService;
import java.util.List;

/**
 * 云服务管理Service接口
 * 
 * @author luckyframe
 * @date 2024-09-05
 */
public interface ICloudServiceService 
{
    /**
     * 查询云服务管理
     * 
     * @param cloudId 云服务管理ID
     * @return 云服务管理
     */
    public CloudService selectCloudServiceById(Integer cloudId);

    /**
     * 查询云服务管理列表
     * 
     * @param cloudService 云服务管理
     * @return 云服务管理集合
     */
    public List<CloudService> selectCloudServiceList(CloudService cloudService);

    /**
     * 新增云服务管理
     * 
     * @param cloudService 云服务管理
     * @return 结果
     */
    public int insertCloudService(CloudService cloudService);

    /**
     * 修改云服务管理
     * 
     * @param cloudService 云服务管理
     * @return 结果
     */
    public int updateCloudService(CloudService cloudService);

    /**
     * 删除云服务管理
     * 
     * @param cloudId 云服务管理ID
     * @return 结果
     */
    public int deleteCloudServiceById(Integer cloudId);

    /**
     * 批量删除云服务管理
     * 
     * @param cloudIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCloudServiceByIds(String[] cloudIds);

    /**
     * 测试云服务连接
     * 
     * @param cloudService 云服务管理
     * @return 结果
     */
    public String testCloudConnection(CloudService cloudService);

    /**
     * 列出云服务资源
     * 
     * @param cloudId 云服务管理ID
     * @return 结果
     */
    public String listCloudResources(Integer cloudId);

    /**
     * 创建云服务资源
     * 
     * @param cloudId 云服务管理ID
     * @param resourceType 资源类型
     * @param resourceName 资源名称
     * @param resourceConfig 资源配置
     * @return 结果
     */
    public String createCloudResource(Integer cloudId, String resourceType, String resourceName, String resourceConfig);

    /**
     * 删除云服务资源
     * 
     * @param cloudId 云服务管理ID
     * @param resourceType 资源类型
     * @param resourceName 资源名称
     * @return 结果
     */
    public String deleteCloudResource(Integer cloudId, String resourceType, String resourceName);

    /**
     * 在云环境执行测试
     * 
     * @param cloudId 云服务管理ID
     * @param testCaseId 测试用例ID
     * @param resourceId 资源ID
     * @return 结果
     */
    public String executeTestInCloud(Integer cloudId, Integer testCaseId, String resourceId);
}
