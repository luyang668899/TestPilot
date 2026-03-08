package com.luckyframe.project.testmanagmt.cicd.controller;

import com.luckyframe.common.utils.poi.ExcelUtil;
import com.luckyframe.framework.aspectj.lang.annotation.Log;
import com.luckyframe.framework.aspectj.lang.enums.BusinessType;
import com.luckyframe.framework.web.controller.BaseController;
import com.luckyframe.framework.web.domain.AjaxResult;
import com.luckyframe.framework.web.page.TableDataInfo;
import com.luckyframe.project.testmanagmt.cicd.domain.CiCdGitLab;
import com.luckyframe.project.testmanagmt.cicd.service.ICiCdGitLabService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * GitLab CI/CD集成控制器
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/testmanagmt/cicd/gitlab")
public class CiCdGitLabController extends BaseController
{
    private String prefix = "testmanagmt/cicd/gitlab";

    @Autowired
    private ICiCdGitLabService ciCdGitLabService;

    @RequiresPermissions("testmanagmt:cicd:gitlab:view")
    @GetMapping()
    public String gitlab()
    {
        return prefix + "/gitlab";
    }

    /**
     * 查询GitLab CI/CD集成列表
     */
    @RequiresPermissions("testmanagmt:cicd:gitlab:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CiCdGitLab ciCdGitLab)
    {
        startPage();
        List<CiCdGitLab> list = ciCdGitLabService.selectCiCdGitLabList(ciCdGitLab);
        return getDataTable(list);
    }

    /**
     * 导出GitLab CI/CD集成列表
     */
    @RequiresPermissions("testmanagmt:cicd:gitlab:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CiCdGitLab ciCdGitLab)
    {
        List<CiCdGitLab> list = ciCdGitLabService.selectCiCdGitLabList(ciCdGitLab);
        ExcelUtil<CiCdGitLab> util = new ExcelUtil<CiCdGitLab>(CiCdGitLab.class);
        return util.exportExcel(list, "GitLab CI/CD集成数据");
    }

    /**
     * 新增GitLab CI/CD集成
     */
    @RequiresPermissions("testmanagmt:cicd:gitlab:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存GitLab CI/CD集成
     */
    @RequiresPermissions("testmanagmt:cicd:gitlab:add")
    @Log(title = "GitLab CI/CD集成", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CiCdGitLab ciCdGitLab)
    {
        return toAjax(ciCdGitLabService.insertCiCdGitLab(ciCdGitLab));
    }

    /**
     * 修改GitLab CI/CD集成
     */
    @RequiresPermissions("testmanagmt:cicd:gitlab:edit")
    @GetMapping("/edit/{gitlabId}")
    public String edit(@PathVariable("gitlabId") Long gitlabId, ModelMap mmap)
    {
        CiCdGitLab ciCdGitLab = ciCdGitLabService.selectCiCdGitLabById(gitlabId);
        mmap.put("ciCdGitLab", ciCdGitLab);
        return prefix + "/edit";
    }

    /**
     * 修改保存GitLab CI/CD集成
     */
    @RequiresPermissions("testmanagmt:cicd:gitlab:edit")
    @Log(title = "GitLab CI/CD集成", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CiCdGitLab ciCdGitLab)
    {
        return toAjax(ciCdGitLabService.updateCiCdGitLab(ciCdGitLab));
    }

    /**
     * 删除GitLab CI/CD集成
     */
    @RequiresPermissions("testmanagmt:cicd:gitlab:remove")
    @Log(title = "GitLab CI/CD集成", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ciCdGitLabService.deleteCiCdGitLabByIds(ids.split(",")));
    }

    /**
     * 测试连接
     */
    @RequiresPermissions("testmanagmt:cicd:gitlab:test")
    @PostMapping("/testConnection")
    @ResponseBody
    public AjaxResult testConnection(CiCdGitLab ciCdGitLab)
    {
        boolean result = ciCdGitLabService.testConnection(ciCdGitLab);
        if (result) {
            return success("连接测试成功");
        } else {
            return error("连接测试失败");
        }
    }

    /**
     * 获取GitLab流水线列表
     */
    @RequiresPermissions("testmanagmt:cicd:gitlab:pipelines")
    @PostMapping("/pipelines")
    @ResponseBody
    public AjaxResult getGitLabPipelines(@RequestParam("gitlabId") Long gitlabId)
    {
        List<String> pipelines = ciCdGitLabService.getGitLabPipelines(gitlabId);
        return success().put("pipelines", pipelines);
    }

    /**
     * 触发GitLab流水线
     */
    @RequiresPermissions("testmanagmt:cicd:gitlab:trigger")
    @PostMapping("/trigger")
    @ResponseBody
    public AjaxResult triggerGitLabPipeline(@RequestParam("gitlabId") Long gitlabId, @RequestParam("ref") String ref, @RequestParam("variables") String variables)
    {
        String pipelineId = ciCdGitLabService.triggerGitLabPipeline(gitlabId, ref, variables);
        if (pipelineId != null) {
            return success("流水线触发成功，流水线ID: " + pipelineId);
        } else {
            return error("流水线触发失败");
        }
    }

    /**
     * 获取GitLab流水线状态
     */
    @RequiresPermissions("testmanagmt:cicd:gitlab:status")
    @PostMapping("/status")
    @ResponseBody
    public AjaxResult getGitLabPipelineStatus(@RequestParam("gitlabId") Long gitlabId, @RequestParam("pipelineId") String pipelineId)
    {
        String status = ciCdGitLabService.getGitLabPipelineStatus(gitlabId, pipelineId);
        return success().put("status", status);
    }
}