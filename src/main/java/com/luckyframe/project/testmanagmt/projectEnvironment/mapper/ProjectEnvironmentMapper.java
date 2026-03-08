package com.luckyframe.project.testmanagmt.projectEnvironment.mapper;

import java.util.List;
import com.luckyframe.project.testmanagmt.projectEnvironment.domain.ProjectEnvironment;

/**
 * 环境配置管理Mapper接口
 * 
 * @author luckyframe
 * @date 2024-01-01
 */
public interface ProjectEnvironmentMapper 
{
    /**
     * 查询环境配置管理
     * 
     * @param environmentId 环境配置管理ID
     * @return 环境配置管理
     */
    public ProjectEnvironment selectProjectEnvironmentById(Integer environmentId);

    /**
     * 查询环境配置管理列表
     * 
     * @param projectEnvironment 环境配置管理
     * @return 环境配置管理集合
     */
    public List<ProjectEnvironment> selectProjectEnvironmentList(ProjectEnvironment projectEnvironment);

    /**
     * 新增环境配置管理
     * 
     * @param projectEnvironment 环境配置管理
     * @return 结果
     */
    public int insertProjectEnvironment(ProjectEnvironment projectEnvironment);

    /**
     * 修改环境配置管理
     * 
     * @param projectEnvironment 环境配置管理
     * @return 结果
     */
    public int updateProjectEnvironment(ProjectEnvironment projectEnvironment);

    /**
     * 删除环境配置管理
     * 
     * @param environmentId 环境配置管理ID
     * @return 结果
     */
    public int deleteProjectEnvironmentById(Integer environmentId);

    /**
     * 批量删除环境配置管理
     * 
     * @param environmentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectEnvironmentByIds(String[] environmentIds);
    
    /**
     * 根据项目ID查询环境列表
     * 
     * @param projectId 项目ID
     * @return 环境列表
     */
    public List<ProjectEnvironment> selectProjectEnvironmentByProjectId(Integer projectId);
    
    /**
     * 根据项目ID和环境名称查询环境
     * 
     * @param projectId 项目ID
     * @param environmentName 环境名称
     * @return 环境信息
     */
    public ProjectEnvironment selectProjectEnvironmentByName(Integer projectId, String environmentName);
    
    /**
     * 查询项目默认环境
     * 
     * @param projectId 项目ID
     * @return 环境信息
     */
    public ProjectEnvironment selectDefaultEnvironmentByProjectId(Integer projectId);
}