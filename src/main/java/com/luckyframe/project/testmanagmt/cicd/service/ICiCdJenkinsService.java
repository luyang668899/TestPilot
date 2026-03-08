package com.luckyframe.project.testmanagmt.cicd.service;

import com.luckyframe.project.testmanagmt.cicd.domain.CiCdJenkins;

import java.util.List;

/**
 * Jenkins集成Service接口
 * 
 * @author ruoyi
 */
public interface ICiCdJenkinsService
{
    /**
     * 查询Jenkins集成
     * 
     * @param jenkinsId Jenkins集成ID
     * @return Jenkins集成
     */
    public CiCdJenkins selectCiCdJenkinsById(Long jenkinsId);

    /**
     * 查询Jenkins集成列表
     * 
     * @param ciCdJenkins Jenkins集成
     * @return Jenkins集成集合
     */
    public List<CiCdJenkins> selectCiCdJenkinsList(CiCdJenkins ciCdJenkins);

    /**
     * 新增Jenkins集成
     * 
     * @param ciCdJenkins Jenkins集成
     * @return 结果
     */
    public int insertCiCdJenkins(CiCdJenkins ciCdJenkins);

    /**
     * 修改Jenkins集成
     * 
     * @param ciCdJenkins Jenkins集成
     * @return 结果
     */
    public int updateCiCdJenkins(CiCdJenkins ciCdJenkins);

    /**
     * 删除Jenkins集成
     * 
     * @param jenkinsId Jenkins集成ID
     * @return 结果
     */
    public int deleteCiCdJenkinsById(Long jenkinsId);

    /**
     * 批量删除Jenkins集成
     * 
     * @param jenkinsIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCiCdJenkinsByIds(String[] jenkinsIds);

    /**
     * 根据项目ID查询Jenkins集成
     * 
     * @param projectId 项目ID
     * @return Jenkins集成集合
     */
    public List<CiCdJenkins> selectCiCdJenkinsByProjectId(Long projectId);

    /**
     * 测试Jenkins连接
     * 
     * @param ciCdJenkins Jenkins集成
     * @return 连接结果
     */
    public boolean testConnection(CiCdJenkins ciCdJenkins);

    /**
     * 获取Jenkins任务列表
     * 
     * @param jenkinsId Jenkins集成ID
     * @return 任务列表
     */
    public List<String> getJenkinsJobs(Long jenkinsId);

    /**
     * 触发Jenkins任务
     * 
     * @param jenkinsId Jenkins集成ID
     * @param jobName 任务名称
     * @param parameters 任务参数
     * @return 构建ID
     */
    public String triggerJenkinsJob(Long jenkinsId, String jobName, String parameters);

    /**
     * 获取Jenkins任务构建状态
     * 
     * @param jenkinsId Jenkins集成ID
     * @param buildId 构建ID
     * @return 构建状态
     */
    public String getJenkinsBuildStatus(Long jenkinsId, String buildId);
}