package com.luckyframe.project.cloud.mapper;

import com.luckyframe.project.cloud.domain.CloudService;
import java.util.List;

/**
 * 云服务管理Mapper接口
 * 
 * @author luckyframe
 * @date 2024-09-05
 */
public interface CloudServiceMapper 
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
}
