package com.luckyframe.project.testreport.defect.service;

import com.luckyframe.project.testreport.defect.domain.DefectManagement;

import java.util.List;

/**
 * 缺陷管理集成Service接口
 * 
 * @author ruoyi
 */
public interface IDefectManagementService
{
    /**
     * 查询缺陷管理集成
     * 
     * @param defectId 缺陷管理集成ID
     * @return 缺陷管理集成
     */
    public DefectManagement selectDefectManagementById(Long defectId);

    /**
     * 查询缺陷管理集成列表
     * 
     * @param defectManagement 缺陷管理集成
     * @return 缺陷管理集成集合
     */
    public List<DefectManagement> selectDefectManagementList(DefectManagement defectManagement);

    /**
     * 新增缺陷管理集成
     * 
     * @param defectManagement 缺陷管理集成
     * @return 结果
     */
    public int insertDefectManagement(DefectManagement defectManagement);

    /**
     * 修改缺陷管理集成
     * 
     * @param defectManagement 缺陷管理集成
     * @return 结果
     */
    public int updateDefectManagement(DefectManagement defectManagement);

    /**
     * 删除缺陷管理集成
     * 
     * @param defectId 缺陷管理集成ID
     * @return 结果
     */
    public int deleteDefectManagementById(Long defectId);

    /**
     * 批量删除缺陷管理集成
     * 
     * @param defectIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteDefectManagementByIds(String[] defectIds);

    /**
     * 根据项目ID查询缺陷管理集成
     * 
     * @param projectId 项目ID
     * @return 缺陷管理集成集合
     */
    public List<DefectManagement> selectDefectManagementByProjectId(Long projectId);

    /**
     * 测试缺陷系统连接
     * 
     * @param defectManagement 缺陷管理集成
     * @return 连接结果
     */
    public boolean testConnection(DefectManagement defectManagement);

    /**
     * 同步缺陷数据
     * 
     * @param defectId 缺陷管理集成ID
     * @return 同步结果
     */
    public int syncDefectData(Long defectId);

    /**
     * 创建缺陷
     * 
     * @param defectManagement 缺陷管理集成
     * @param defectInfo 缺陷信息
     * @return 缺陷ID
     */
    public String createDefect(DefectManagement defectManagement, String defectInfo);
}