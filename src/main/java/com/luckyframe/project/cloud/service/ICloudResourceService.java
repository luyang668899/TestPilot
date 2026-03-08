package com.luckyframe.project.cloud.service;

import com.luckyframe.project.cloud.domain.CloudResource;

import java.util.List;

/**
 * 云资源服务接口
 * 
 * @author ruoyi
 * @date 2023-09-15
 */
public interface ICloudResourceService
{
    /**
     * 查询云资源
     * 
     * @param resourceId 云资源ID
     * @return 云资源
     */
    public CloudResource selectCloudResourceByResourceId(Integer resourceId);

    /**
     * 查询云资源列表
     * 
     * @param cloudResource 云资源
     * @return 云资源集合
     */
    public List<CloudResource> selectCloudResourceList(CloudResource cloudResource);

    /**
     * 新增云资源
     * 
     * @param cloudResource 云资源
     * @return 结果
     */
    public int insertCloudResource(CloudResource cloudResource);

    /**
     * 修改云资源
     * 
     * @param cloudResource 云资源
     * @return 结果
     */
    public int updateCloudResource(CloudResource cloudResource);

    /**
     * 删除云资源
     * 
     * @param resourceId 云资源ID
     * @return 结果
     */
    public int deleteCloudResourceByResourceId(Integer resourceId);

    /**
     * 批量删除云资源
     * 
     * @param resourceIds 需要删除的云资源ID
     * @return 结果
     */
    public int deleteCloudResourceByResourceIds(Integer[] resourceIds);

    /**
     * 根据服务ID查询云资源列表
     * 
     * @param serviceId 服务ID
     * @return 云资源集合
     */
    public List<CloudResource> selectCloudResourceByServiceId(Integer serviceId);

    /**
     * 创建云资源
     * 
     * @param serviceId 服务ID
     * @param resourceName 资源名称
     * @param resourceType 资源类型
     * @param params 资源参数
     * @return 云资源
     */
    public CloudResource createCloudResource(Integer serviceId, String resourceName, String resourceType, String params);

    /**
     * 删除云资源
     * 
     * @param resourceId 资源ID
     * @return 结果
     */
    public boolean deleteCloudResource(Integer resourceId);

    /**
     * 获取云资源状态
     * 
     * @param resourceId 资源ID
     * @return 状态
     */
    public String getCloudResourceStatus(Integer resourceId);

    /**
     * 列出云平台资源
     * 
     * @param serviceId 服务ID
     * @param resourceType 资源类型
     * @return 资源列表
     */
    public List<CloudResource> listCloudPlatformResources(Integer serviceId, String resourceType);
}
