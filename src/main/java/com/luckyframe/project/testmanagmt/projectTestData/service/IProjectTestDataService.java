package com.luckyframe.project.testmanagmt.projectTestData.service;

import com.luckyframe.project.testmanagmt.projectTestData.domain.ProjectTestData;

import java.util.List;

/**
 * 测试数据管理Service接口
 * 
 * @author luckyframe
 * @date 2026-03-08
 */
public interface IProjectTestDataService 
{
    /**
     * 查询测试数据管理
     * 
     * @param dataId 测试数据管理ID
     * @return 测试数据管理
     */
    public ProjectTestData selectProjectTestDataById(Long dataId);

    /**
     * 查询测试数据管理列表
     * 
     * @param projectTestData 测试数据管理
     * @return 测试数据管理集合
     */
    public List<ProjectTestData> selectProjectTestDataList(ProjectTestData projectTestData);

    /**
     * 新增测试数据管理
     * 
     * @param projectTestData 测试数据管理
     * @return 结果
     */
    public int insertProjectTestData(ProjectTestData projectTestData);

    /**
     * 修改测试数据管理
     * 
     * @param projectTestData 测试数据管理
     * @return 结果
     */
    public int updateProjectTestData(ProjectTestData projectTestData);

    /**
     * 删除测试数据管理
     * 
     * @param dataId 测试数据管理ID
     * @return 结果
     */
    public int deleteProjectTestDataById(Long dataId);

    /**
     * 批量删除测试数据管理
     * 
     * @param dataIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectTestDataByIds(String[] dataIds);

    /**
     * 根据项目ID和环境ID查询测试数据
     * 
     * @param projectId 项目ID
     * @param environmentId 环境ID
     * @return 测试数据列表
     */
    public List<ProjectTestData> selectProjectTestDataByProjectAndEnvironment(Long projectId, Long environmentId);

    /**
     * 根据项目ID查询测试数据
     * 
     * @param projectId 项目ID
     * @return 测试数据列表
     */
    public List<ProjectTestData> selectProjectTestDataByProjectId(Long projectId);

    /**
     * 生成测试数据
     * 
     * @param projectId 项目ID
     * @param environmentId 环境ID
     * @param dataType 数据类型
     * @param count 生成数量
     * @return 生成的测试数据列表
     */
    public List<ProjectTestData> generateTestData(Long projectId, Long environmentId, String dataType, int count);
}