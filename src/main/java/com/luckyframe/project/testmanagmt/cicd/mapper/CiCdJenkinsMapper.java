package com.luckyframe.project.testmanagmt.cicd.mapper;

import com.luckyframe.project.testmanagmt.cicd.domain.CiCdJenkins;

import java.util.List;

/**
 * Jenkins集成Mapper接口
 * 
 * @author ruoyi
 */
public interface CiCdJenkinsMapper
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
     * 根据状态查询Jenkins集成
     * 
     * @param status 状态
     * @return Jenkins集成集合
     */
    public List<CiCdJenkins> selectCiCdJenkinsByStatus(Integer status);
}