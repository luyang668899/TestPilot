package com.luckyframe.project.testmanagmt.cicd.mapper;

import com.luckyframe.project.testmanagmt.cicd.domain.CiCdGitLab;

import java.util.List;

/**
 * GitLab CI/CD集成Mapper接口
 * 
 * @author ruoyi
 */
public interface CiCdGitLabMapper
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
     * 根据状态查询GitLab CI/CD集成
     * 
     * @param status 状态
     * @return GitLab CI/CD集成集合
     */
    public List<CiCdGitLab> selectCiCdGitLabByStatus(Integer status);
}