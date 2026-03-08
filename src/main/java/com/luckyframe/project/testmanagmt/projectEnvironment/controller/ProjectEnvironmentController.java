package com.luckyframe.project.testmanagmt.projectEnvironment.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.luckyframe.framework.aspectj.lang.annotation.Log;
import com.luckyframe.framework.aspectj.lang.enums.BusinessType;
import com.luckyframe.framework.web.controller.BaseController;
import com.luckyframe.framework.web.domain.AjaxResult;
import com.luckyframe.framework.web.page.TableDataInfo;
import com.luckyframe.project.system.project.domain.Project;
import com.luckyframe.project.system.project.service.IProjectService;
import com.luckyframe.project.testmanagmt.projectEnvironment.domain.ProjectEnvironment;
import com.luckyframe.project.testmanagmt.projectEnvironment.service.IProjectEnvironmentService;

/**
 * 环境配置管理Controller
 * 
 * @author luckyframe
 * @date 2024-01-01
 */
@Controller
@RequestMapping("/testmanagmt/environment")
public class ProjectEnvironmentController extends BaseController
{
    private String prefix = "testmanagmt/environment";

    @Autowired
    private IProjectEnvironmentService projectEnvironmentService;

    @Autowired
    private IProjectService projectService;

    @RequiresPermissions("testmanagmt:environment:view")
    @GetMapping()
    public String environment(ModelMap mmap)
    {
        List<Project> projects = projectService.selectProjectAll(0);
        mmap.put("projects", projects);
        return prefix + "/environment";
    }

    /**
     * 查询环境配置管理列表
     */
    @RequiresPermissions("testmanagmt:environment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProjectEnvironment projectEnvironment)
    {
        startPage();
        List<ProjectEnvironment> list = projectEnvironmentService.selectProjectEnvironmentList(projectEnvironment);
        return getDataTable(list);
    }

    /**
     * 新增环境配置管理
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        List<Project> projects = projectService.selectProjectAll(0);
        mmap.put("projects", projects);
        return prefix + "/add";
    }

    /**
     * 新增保存环境配置管理
     */
    @RequiresPermissions("testmanagmt:environment:add")
    @Log(title = "环境配置管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProjectEnvironment projectEnvironment)
    {
        if (!projectEnvironmentService.checkEnvironmentNameUnique(projectEnvironment)) {
            return error("新增环境" + projectEnvironment.getEnvironmentName() + "失败，环境名称已存在");
        }
        return toAjax(projectEnvironmentService.insertProjectEnvironment(projectEnvironment));
    }

    /**
     * 修改环境配置管理
     */
    @GetMapping("/edit/{environmentId}")
    public String edit(@PathVariable("environmentId") Integer environmentId, ModelMap mmap)
    {
        ProjectEnvironment projectEnvironment = projectEnvironmentService.selectProjectEnvironmentById(environmentId);
        mmap.put("projectEnvironment", projectEnvironment);
        List<Project> projects = projectService.selectProjectAll(0);
        mmap.put("projects", projects);
        return prefix + "/edit";
    }

    /**
     * 修改保存环境配置管理
     */
    @RequiresPermissions("testmanagmt:environment:edit")
    @Log(title = "环境配置管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProjectEnvironment projectEnvironment)
    {
        if (!projectEnvironmentService.checkEnvironmentNameUnique(projectEnvironment)) {
            return error("修改环境" + projectEnvironment.getEnvironmentName() + "失败，环境名称已存在");
        }
        return toAjax(projectEnvironmentService.updateProjectEnvironment(projectEnvironment));
    }

    /**
     * 删除环境配置管理
     */
    @RequiresPermissions("testmanagmt:environment:remove")
    @Log(title = "环境配置管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(projectEnvironmentService.deleteProjectEnvironmentByIds(ids.split(",")));
    }

    /**
     * 设置默认环境
     */
    @RequiresPermissions("testmanagmt:environment:edit")
    @Log(title = "环境配置管理", businessType = BusinessType.UPDATE)
    @PostMapping("/setDefault")
    @ResponseBody
    public AjaxResult setDefault(Integer projectId, Integer environmentId)
    {
        return toAjax(projectEnvironmentService.setDefaultEnvironment(projectId, environmentId));
    }

    /**
     * 根据项目ID获取环境列表
     */
    @GetMapping("/getEnvironments/{projectId}")
    @ResponseBody
    public List<ProjectEnvironment> getEnvironments(@PathVariable("projectId") Integer projectId)
    {
        return projectEnvironmentService.selectProjectEnvironmentByProjectId(projectId);
    }
}