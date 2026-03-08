package com.luckyframe.project.testmanagmt.projectEnvironment.service;

import java.util.List;
import com.luckyframe.project.testmanagmt.projectEnvironment.domain.ProjectEnvironment;
import com.luckyframe.project.testmanagmt.projectEnvironment.mapper.ProjectEnvironmentMapper;
import com.luckyframe.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 环境配置管理Service实现
 * 
 * @author luckyframe
 * @date 2024-01-01
 */
@Service
public class ProjectEnvironmentServiceImpl implements IProjectEnvironmentService
{
    @Autowired
    private ProjectEnvironmentMapper projectEnvironmentMapper;

    /**
     * 查询环境配置管理
     * 
     * @param environmentId 环境配置管理ID
     * @return 环境配置管理
     */
    @Override
    public ProjectEnvironment selectProjectEnvironmentById(Integer environmentId)
    {
        return projectEnvironmentMapper.selectProjectEnvironmentById(environmentId);
    }

    /**
     * 查询环境配置管理列表
     * 
     * @param projectEnvironment 环境配置管理
     * @return 环境配置管理集合
     */
    @Override
    public List<ProjectEnvironment> selectProjectEnvironmentList(ProjectEnvironment projectEnvironment)
    {
        return projectEnvironmentMapper.selectProjectEnvironmentList(projectEnvironment);
    }

    /**
     * 新增环境配置管理
     * 
     * @param projectEnvironment 环境配置管理
     * @return 结果
     */
    @Override
    public int insertProjectEnvironment(ProjectEnvironment projectEnvironment)
    {
        // 如果是第一个环境，设置为默认环境
        List<ProjectEnvironment> environmentList = projectEnvironmentMapper.selectProjectEnvironmentByProjectId(projectEnvironment.getProjectId());
        if (environmentList == null || environmentList.isEmpty()) {
            projectEnvironment.setIsDefault("Y");
        } else {
            projectEnvironment.setIsDefault("N");
        }
        return projectEnvironmentMapper.insertProjectEnvironment(projectEnvironment);
    }

    /**
     * 修改环境配置管理
     * 
     * @param projectEnvironment 环境配置管理
     * @return 结果
     */
    @Override
    public int updateProjectEnvironment(ProjectEnvironment projectEnvironment)
    {
        return projectEnvironmentMapper.updateProjectEnvironment(projectEnvironment);
    }

    /**
     * 删除环境配置管理
     * 
     * @param environmentId 环境配置管理ID
     * @return 结果
     */
    @Override
    public int deleteProjectEnvironmentById(Integer environmentId)
    {
        return projectEnvironmentMapper.deleteProjectEnvironmentById(environmentId);
    }

    /**
     * 批量删除环境配置管理
     * 
     * @param environmentIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProjectEnvironmentByIds(String[] environmentIds)
    {
        return projectEnvironmentMapper.deleteProjectEnvironmentByIds(environmentIds);
    }

    /**
     * 根据项目ID查询环境列表
     * 
     * @param projectId 项目ID
     * @return 环境列表
     */
    @Override
    public List<ProjectEnvironment> selectProjectEnvironmentByProjectId(Integer projectId)
    {
        return projectEnvironmentMapper.selectProjectEnvironmentByProjectId(projectId);
    }

    /**
     * 设置环境为默认环境
     * 
     * @param projectId 项目ID
     * @param environmentId 环境ID
     * @return 结果
     */
    @Override
    public int setDefaultEnvironment(Integer projectId, Integer environmentId)
    {
        // 先将所有环境设置为非默认
        ProjectEnvironment updateEnv = new ProjectEnvironment();
        updateEnv.setProjectId(projectId);
        updateEnv.setIsDefault("N");
        projectEnvironmentMapper.updateProjectEnvironment(updateEnv);
        
        // 将指定环境设置为默认
        ProjectEnvironment defaultEnv = new ProjectEnvironment();
        defaultEnv.setEnvironmentId(environmentId);
        defaultEnv.setIsDefault("Y");
        return projectEnvironmentMapper.updateProjectEnvironment(defaultEnv);
    }

    /**
     * 获取项目默认环境
     * 
     * @param projectId 项目ID
     * @return 环境信息
     */
    @Override
    public ProjectEnvironment getDefaultEnvironment(Integer projectId)
    {
        ProjectEnvironment environment = projectEnvironmentMapper.selectDefaultEnvironmentByProjectId(projectId);
        if (environment == null) {
            // 如果没有默认环境，返回第一个环境
            List<ProjectEnvironment> environmentList = projectEnvironmentMapper.selectProjectEnvironmentByProjectId(projectId);
            if (environmentList != null && !environmentList.isEmpty()) {
                environment = environmentList.get(0);
            }
        }
        return environment;
    }

    /**
     * 环境名称唯一性检查
     * 
     * @param projectEnvironment 环境信息
     * @return 结果
     */
    @Override
    public boolean checkEnvironmentNameUnique(ProjectEnvironment projectEnvironment)
    {
        ProjectEnvironment env = projectEnvironmentMapper.selectProjectEnvironmentByName(projectEnvironment.getProjectId(), projectEnvironment.getEnvironmentName());
        if (env != null && !env.getEnvironmentId().equals(projectEnvironment.getEnvironmentId())) {
            return false;
        }
        return true;
    }
}