package com.luckyframe.project.testreport.template.service;

import com.luckyframe.project.testreport.template.domain.ReportTemplate;

import java.util.List;

/**
 * 测试报告模板Service接口
 * 
 * @author luckyframe
 * @date 2026-03-08
 */
public interface IReportTemplateService 
{
    /**
     * 查询测试报告模板
     * 
     * @param templateId 测试报告模板ID
     * @return 测试报告模板
     */
    public ReportTemplate selectReportTemplateById(Long templateId);

    /**
     * 查询测试报告模板列表
     * 
     * @param reportTemplate 测试报告模板
     * @return 测试报告模板集合
     */
    public List<ReportTemplate> selectReportTemplateList(ReportTemplate reportTemplate);

    /**
     * 新增测试报告模板
     * 
     * @param reportTemplate 测试报告模板
     * @return 结果
     */
    public int insertReportTemplate(ReportTemplate reportTemplate);

    /**
     * 修改测试报告模板
     * 
     * @param reportTemplate 测试报告模板
     * @return 结果
     */
    public int updateReportTemplate(ReportTemplate reportTemplate);

    /**
     * 删除测试报告模板
     * 
     * @param templateId 测试报告模板ID
     * @return 结果
     */
    public int deleteReportTemplateById(Long templateId);

    /**
     * 批量删除测试报告模板
     * 
     * @param templateIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteReportTemplateByIds(String[] templateIds);

    /**
     * 查询启用的测试报告模板
     * 
     * @return 测试报告模板集合
     */
    public List<ReportTemplate> selectEnabledReportTemplates();

    /**
     * 生成测试报告
     * 
     * @param taskId 任务ID
     * @param templateId 模板ID
     * @return 报告内容
     */
    public String generateReport(Long taskId, Long templateId);
}