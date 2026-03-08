package com.luckyframe.project.testreport.template.service;

import com.luckyframe.common.utils.DateUtils;
import com.luckyframe.common.utils.security.ShiroUtils;
import com.luckyframe.project.testexecution.taskExecute.domain.TaskExecute;
import com.luckyframe.project.testexecution.taskExecute.mapper.TaskExecuteMapper;
import com.luckyframe.project.testexecution.taskCaseLog.domain.TaskCaseLog;
import com.luckyframe.project.testexecution.taskCaseLog.mapper.TaskCaseLogMapper;
import com.luckyframe.project.testreport.template.domain.ReportTemplate;
import com.luckyframe.project.testreport.template.mapper.ReportTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * 测试报告模板Service实现
 * 
 * @author luckyframe
 * @date 2026-03-08
 */
@Service
public class ReportTemplateServiceImpl implements IReportTemplateService
{
    @Autowired
    private ReportTemplateMapper reportTemplateMapper;

    @Autowired
    private TaskExecuteMapper taskExecuteMapper;

    @Autowired
    private TaskCaseLogMapper taskCaseLogMapper;

    /**
     * 查询测试报告模板
     */
    @Override
    public ReportTemplate selectReportTemplateById(Long templateId)
    {
        return reportTemplateMapper.selectReportTemplateById(templateId);
    }

    /**
     * 查询测试报告模板列表
     */
    @Override
    public List<ReportTemplate> selectReportTemplateList(ReportTemplate reportTemplate)
    {
        return reportTemplateMapper.selectReportTemplateList(reportTemplate);
    }

    /**
     * 新增测试报告模板
     */
    @Override
    public int insertReportTemplate(ReportTemplate reportTemplate)
    {
        reportTemplate.setCreateBy(ShiroUtils.getLoginName());
        reportTemplate.setCreateTime(DateUtils.getNowDate());
        reportTemplate.setUpdateBy(ShiroUtils.getLoginName());
        reportTemplate.setUpdateTime(DateUtils.getNowDate());
        return reportTemplateMapper.insertReportTemplate(reportTemplate);
    }

    /**
     * 修改测试报告模板
     */
    @Override
    public int updateReportTemplate(ReportTemplate reportTemplate)
    {
        reportTemplate.setUpdateBy(ShiroUtils.getLoginName());
        reportTemplate.setUpdateTime(DateUtils.getNowDate());
        return reportTemplateMapper.updateReportTemplate(reportTemplate);
    }

    /**
     * 删除测试报告模板
     */
    @Override
    public int deleteReportTemplateById(Long templateId)
    {
        return reportTemplateMapper.deleteReportTemplateById(templateId);
    }

    /**
     * 批量删除测试报告模板
     */
    @Override
    public int deleteReportTemplateByIds(String[] templateIds)
    {
        return reportTemplateMapper.deleteReportTemplateByIds(templateIds);
    }

    /**
     * 查询启用的测试报告模板
     */
    @Override
    public List<ReportTemplate> selectEnabledReportTemplates()
    {
        return reportTemplateMapper.selectEnabledReportTemplates();
    }

    /**
     * 生成测试报告
     */
    @Override
    public String generateReport(Long taskId, Long templateId)
    {
        // 获取任务执行信息
        TaskExecute taskExecute = taskExecuteMapper.selectTaskExecuteById(taskId.intValue());
        if (taskExecute == null) {
            return "任务不存在";
        }

        // 获取报告模板
        ReportTemplate template = reportTemplateMapper.selectReportTemplateById(templateId);
        if (template == null) {
            return "模板不存在";
        }

        // 获取用例日志
        TaskCaseLog taskCaseLog = new TaskCaseLog();
        taskCaseLog.setTaskId(taskId.intValue());
        List<TaskCaseLog> caseLogs = taskCaseLogMapper.selectTaskCaseLogList(taskCaseLog);

        // 生成报告内容
        String templateContent = template.getTemplateContent();
        templateContent = templateContent.replace("${taskName}", taskExecute.getTaskName());
        templateContent = templateContent.replace("${taskStatus}", getTaskStatusText(taskExecute.getTaskStatus()));
        templateContent = templateContent.replace("${caseTotalCount}", String.valueOf(taskExecute.getCaseTotalCount()));
        templateContent = templateContent.replace("${caseSuccCount}", String.valueOf(taskExecute.getCaseSuccCount()));
        templateContent = templateContent.replace("${caseFailCount}", String.valueOf(taskExecute.getCaseFailCount()));
        templateContent = templateContent.replace("${caseLockCount}", String.valueOf(taskExecute.getCaseLockCount()));
        templateContent = templateContent.replace("${caseNoexecCount}", String.valueOf(taskExecute.getCaseNoexecCount()));
        templateContent = templateContent.replace("${createTime}", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, taskExecute.getCreateTime()));
        templateContent = templateContent.replace("${finishTime}", taskExecute.getFinishTime() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, taskExecute.getFinishTime()) : "");

        // 计算成功率
        int totalCount = taskExecute.getCaseTotalCount() != null ? taskExecute.getCaseTotalCount() : 0;
        int succCount = taskExecute.getCaseSuccCount() != null ? taskExecute.getCaseSuccCount() : 0;
        double successRate = totalCount > 0 ? (double) succCount / totalCount * 100 : 0;
        templateContent = templateContent.replace("${successRate}", String.format("%.2f%%", successRate));

        // 添加用例详细信息
        StringBuilder caseDetails = new StringBuilder();
        for (TaskCaseLog log : caseLogs) {
            caseDetails.append("<tr>");
            caseDetails.append("<td>").append(log.getLogStep()).append("</td>");
            caseDetails.append("<td>").append(log.getLogDetail()).append("</td>");
            caseDetails.append("<td>").append(log.getLogGrade()).append("</td>");
            caseDetails.append("<td>").append(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, log.getCreateTime())).append("</td>");
            caseDetails.append("</tr>");
        }
        templateContent = templateContent.replace("${caseDetails}", caseDetails.toString());

        return templateContent;
    }

    /**
     * 获取任务状态文本
     */
    private String getTaskStatusText(Integer taskStatus) {
        switch (taskStatus) {
            case 0:
                return "未执行";
            case 1:
                return "执行中";
            case 2:
                return "成功";
            case 4:
                return "失败";
            case 5:
                return "唤起客户端失败";
            default:
                return "未知状态";
        }
    }
}