package com.luckyframe.project.testreport.template.controller;

import com.luckyframe.common.utils.poi.ExcelUtil;
import com.luckyframe.framework.aspectj.lang.annotation.Log;
import com.luckyframe.framework.aspectj.lang.enums.BusinessType;
import com.luckyframe.framework.web.controller.BaseController;
import com.luckyframe.framework.web.domain.AjaxResult;
import com.luckyframe.framework.web.page.TableDataInfo;
import com.luckyframe.project.testreport.template.domain.ReportTemplate;
import com.luckyframe.project.testreport.template.service.IReportTemplateService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试报告模板Controller
 * 
 * @author luckyframe
 * @date 2026-03-08
 */
@RestController
@RequestMapping("/testreport/template")
public class ReportTemplateController extends BaseController
{
    @Autowired
    private IReportTemplateService reportTemplateService;

    /**
     * 获取测试报告模板列表
     */
    @RequiresPermissions("testreport:template:list")
    @GetMapping("/list")
    public TableDataInfo list(ReportTemplate reportTemplate)
    {
        startPage();
        List<ReportTemplate> list = reportTemplateService.selectReportTemplateList(reportTemplate);
        return getDataTable(list);
    }

    /**
     * 导出测试报告模板列表
     */
    @RequiresPermissions("testreport:template:export")
    @Log(title = "测试报告模板", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ReportTemplate reportTemplate)
    {
        List<ReportTemplate> list = reportTemplateService.selectReportTemplateList(reportTemplate);
        ExcelUtil<ReportTemplate> util = new ExcelUtil<ReportTemplate>(ReportTemplate.class);
        return util.exportExcel(list, "测试报告模板");
    }

    /**
     * 获取测试报告模板详细信息
     */
    @RequiresPermissions("testreport:template:query")
    @GetMapping(value = "/{templateId}")
    public AjaxResult getInfo(@PathVariable("templateId") Long templateId)
    {
        return AjaxResult.success().put("template", reportTemplateService.selectReportTemplateById(templateId));
    }

    /**
     * 新增测试报告模板
     */
    @RequiresPermissions("testreport:template:add")
    @Log(title = "测试报告模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReportTemplate reportTemplate)
    {
        return toAjax(reportTemplateService.insertReportTemplate(reportTemplate));
    }

    /**
     * 修改测试报告模板
     */
    @RequiresPermissions("testreport:template:edit")
    @Log(title = "测试报告模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReportTemplate reportTemplate)
    {
        return toAjax(reportTemplateService.updateReportTemplate(reportTemplate));
    }

    /**
     * 删除测试报告模板
     */
    @RequiresPermissions("testreport:template:remove")
    @Log(title = "测试报告模板", businessType = BusinessType.DELETE)
    @DeleteMapping("/{templateIds}")
    public AjaxResult remove(@PathVariable String[] templateIds)
    {
        return toAjax(reportTemplateService.deleteReportTemplateByIds(templateIds));
    }

    /**
     * 获取启用的测试报告模板
     */
    @GetMapping("/enabled")
    public AjaxResult getEnabledTemplates()
    {
        List<ReportTemplate> list = reportTemplateService.selectEnabledReportTemplates();
        return AjaxResult.success().put("templates", list);
    }

    /**
     * 生成测试报告
     */
    @RequiresPermissions("testreport:template:generate")
    @Log(title = "测试报告生成", businessType = BusinessType.OTHER)
    @PostMapping("/generate")
    public AjaxResult generateReport(Long taskId, Long templateId)
    {
        String reportContent = reportTemplateService.generateReport(taskId, templateId);
        return AjaxResult.success(reportContent);
    }
}