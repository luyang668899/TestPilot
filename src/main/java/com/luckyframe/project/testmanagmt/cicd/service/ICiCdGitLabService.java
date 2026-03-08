package com.luckyframe.project.testmanagmt.cicd.service;

import com.luckyframe.project.testmanagmt.cicd.domain.CiCdGitLab;

import java.util.List;

/**
 * GitLab CI/CD集成Service接口
 * 
 * @author ruoyi
 */
public interface ICiCdGitLabService
{
    /**
     * 查询GitLab CI/CD集成
     * 
     * @param gitlabId GitLab CI/CD集成ID
     * @return GitLab CI/CD集成
     */
    public CiCdGitLab selectCiCdGitLabById(Long gitlabId);

    /**
     * 查询GitLab CI/CD集成列表
     * 
     * @param ciCdGitLab GitLab CI/CD集成
     * @return GitLab CI/CD集成集合
     */
    public List<CiCdGitLab> selectCiCdGitLabList(CiCdGitLab ciCdGitLab);

    /**
     * 新增GitLab CI/CD集成
     * 
     * @param ciCdGitLab GitLab CI/CD集成
     * @return 结果
     */
    public int insertCiCdGitLab(CiCdGitLab ciCdGitLab);

    /**
     * 修改GitLab CI/CD集成
     * 
     * @param ciCdGitLab GitLab CI/CD集成
     * @return 结果
     */
    public int updateCiCdGitLab(CiCdGitLab ciCdGitLab);

    /**
     * 删除GitLab CI/CD集成
     * 
     * @param gitlabId GitLab CI/CD集成ID
     * @return 结果
     */
    public int deleteCiCdGitLabById(Long gitlabId);

    /**
     * 批量删除GitLab CI/CD集成
     * 
     * @param gitlabIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCiCdGitLabByIds(String[] gitlabIds);

    /**
     * 根据项目ID查询GitLab CI/CD集成
     * 
     * @param projectId 项目ID
     * @return GitLab CI/CD集成集合
     */
    public List<CiCdGitLab> selectCiCdGitLabByProjectId(Long projectId);

    /**
     * 测试GitLab连接
     * 
     * @param ciCdGitLab GitLab CI/CD集成
     * @return 连接结果
     */
    public boolean testConnection(CiCdGitLab ciCdGitLab);

    /**
     * 获取GitLab流水线列表
     * 
     * @param gitlabId GitLab CI/CD集成ID
     * @return 流水线列表
     */
    public List<String> getGitLabPipelines(Long gitlabId);

    /**
     * 触发GitLab流水线
     * 
     * @param gitlabId GitLab CI/CD集成ID
     * @param ref 分支或标签
     * @param variables 流水线变量
     * @return 流水线ID
     */
    public String triggerGitLabPipeline(Long gitlabId, String ref, String variables);

    /**
     * 获取GitLab流水线状态
     * 
     * @param gitlabId GitLab CI/CD集成ID
     * @param pipelineId 流水线ID
     * @return 流水线状态
     */
    public String getGitLabPipelineStatus(Long gitlabId, String pipelineId);
}