package com.luckyframe.project.testmanagmt.projectEnvironment.service;

import java.util.List;
import com.luckyframe.project.testmanagmt.projectEnvironment.domain.ProjectEnvironment;

/**
 * 环境配置管理Service接口
 * 
 * @author luckyframe
 * @date 2024-01-01
 */
public interface IProjectEnvironmentService 
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
     * 设置环境为默认环境
     * 
     * @param projectId 项目ID
     * @param environmentId 环境ID
     * @return 结果
     */
    public int setDefaultEnvironment(Integer projectId, Integer environmentId);
    
    /**
     * 获取项目默认环境
     * 
     * @param projectId 项目ID
     * @return 环境信息
     */
    public ProjectEnvironment getDefaultEnvironment(Integer projectId);
    
    /**
     * 环境名称唯一性检查
     * 
     * @param projectEnvironment 环境信息
     * @return 结果
     */
    public boolean checkEnvironmentNameUnique(ProjectEnvironment projectEnvironment);
}