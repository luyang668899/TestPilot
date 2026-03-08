package com.luckyframe.project.testmanagmt.cicd.controller;

import com.luckyframe.common.utils.poi.ExcelUtil;
import com.luckyframe.framework.aspectj.lang.annotation.Log;
import com.luckyframe.framework.aspectj.lang.enums.BusinessType;
import com.luckyframe.framework.web.controller.BaseController;
import com.luckyframe.framework.web.domain.AjaxResult;
import com.luckyframe.framework.web.page.TableDataInfo;
import com.luckyframe.project.testmanagmt.cicd.domain.CiCdJenkins;
import com.luckyframe.project.testmanagmt.cicd.service.ICiCdJenkinsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Jenkins集成控制器
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/testmanagmt/cicd/jenkins")
public class CiCdJenkinsController extends BaseController
{
    private String prefix = "testmanagmt/cicd/jenkins";

    @Autowired
    private ICiCdJenkinsService ciCdJenkinsService;

    @RequiresPermissions("testmanagmt:cicd:jenkins:view")
    @GetMapping()
    public String jenkins()
    {
        return prefix + "/jenkins";
    }

    /**
     * 查询Jenkins集成列表
     */
    @RequiresPermissions("testmanagmt:cicd:jenkins:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CiCdJenkins ciCdJenkins)
    {
        startPage();
        List<CiCdJenkins> list = ciCdJenkinsService.selectCiCdJenkinsList(ciCdJenkins);
        return getDataTable(list);
    }

    /**
     * 导出Jenkins集成列表
     */
    @RequiresPermissions("testmanagmt:cicd:jenkins:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CiCdJenkins ciCdJenkins)
    {
        List<CiCdJenkins> list = ciCdJenkinsService.selectCiCdJenkinsList(ciCdJenkins);
        ExcelUtil<CiCdJenkins> util = new ExcelUtil<CiCdJenkins>(CiCdJenkins.class);
        return util.exportExcel(list, "Jenkins集成数据");
    }

    /**
     * 新增Jenkins集成
     */
    @RequiresPermissions("testmanagmt:cicd:jenkins:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存Jenkins集成
     */
    @RequiresPermissions("testmanagmt:cicd:jenkins:add")
    @Log(title = "Jenkins集成", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CiCdJenkins ciCdJenkins)
    {
        return toAjax(ciCdJenkinsService.insertCiCdJenkins(ciCdJenkins));
    }

    /**
     * 修改Jenkins集成
     */
    @RequiresPermissions("testmanagmt:cicd:jenkins:edit")
    @GetMapping("/edit/{jenkinsId}")
    public String edit(@PathVariable("jenkinsId") Long jenkinsId, ModelMap mmap)
    {
        CiCdJenkins ciCdJenkins = ciCdJenkinsService.selectCiCdJenkinsById(jenkinsId);
        mmap.put("ciCdJenkins", ciCdJenkins);
        return prefix + "/edit";
    }

    /**
     * 修改保存Jenkins集成
     */
    @RequiresPermissions("testmanagmt:cicd:jenkins:edit")
    @Log(title = "Jenkins集成", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CiCdJenkins ciCdJenkins)
    {
        return toAjax(ciCdJenkinsService.updateCiCdJenkins(ciCdJenkins));
    }

    /**
     * 删除Jenkins集成
     */
    @RequiresPermissions("testmanagmt:cicd:jenkins:remove")
    @Log(title = "Jenkins集成", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ciCdJenkinsService.deleteCiCdJenkinsByIds(ids.split(",")));
    }

    /**
     * 测试连接
     */
    @RequiresPermissions("testmanagmt:cicd:jenkins:test")
    @PostMapping("/testConnection")
    @ResponseBody
    public AjaxResult testConnection(CiCdJenkins ciCdJenkins)
    {
        boolean result = ciCdJenkinsService.testConnection(ciCdJenkins);
        if (result) {
            return success("连接测试成功");
        } else {
            return error("连接测试失败");
        }
    }

    /**
     * 获取Jenkins任务列表
     */
    @RequiresPermissions("testmanagmt:cicd:jenkins:jobs")
    @PostMapping("/jobs")
    @ResponseBody
    public AjaxResult getJenkinsJobs(@RequestParam("jenkinsId") Long jenkinsId)
    {
        List<String> jobs = ciCdJenkinsService.getJenkinsJobs(jenkinsId);
        return success().put("jobs", jobs);
    }

    /**
     * 触发Jenkins任务
     */
    @RequiresPermissions("testmanagmt:cicd:jenkins:trigger")
    @PostMapping("/trigger")
    @ResponseBody
    public AjaxResult triggerJenkinsJob(@RequestParam("jenkinsId") Long jenkinsId, @RequestParam("jobName") String jobName, @RequestParam("parameters") String parameters)
    {
        String buildId = ciCdJenkinsService.triggerJenkinsJob(jenkinsId, jobName, parameters);
        if (buildId != null) {
            return success("任务触发成功，构建ID: " + buildId);
        } else {
            return error("任务触发失败");
        }
    }

    /**
     * 获取Jenkins任务构建状态
     */
    @RequiresPermissions("testmanagmt:cicd:jenkins:status")
    @PostMapping("/status")
    @ResponseBody
    public AjaxResult getJenkinsBuildStatus(@RequestParam("jenkinsId") Long jenkinsId, @RequestParam("buildId") String buildId)
    {
        String status = ciCdJenkinsService.getJenkinsBuildStatus(jenkinsId, buildId);
        return success().put("status", status);
    }
}