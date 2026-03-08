package com.luckyframe.project.testmanagmt.projectTestData.controller;

import com.luckyframe.common.utils.poi.ExcelUtil;
import com.luckyframe.framework.aspectj.lang.annotation.Log;
import com.luckyframe.framework.aspectj.lang.enums.BusinessType;
import com.luckyframe.framework.web.controller.BaseController;
import com.luckyframe.framework.web.domain.AjaxResult;
import com.luckyframe.framework.web.page.TableDataInfo;
import com.luckyframe.project.system.project.domain.Project;
import com.luckyframe.project.system.project.service.IProjectService;
import com.luckyframe.project.testmanagmt.projectEnvironment.domain.ProjectEnvironment;
import com.luckyframe.project.testmanagmt.projectEnvironment.service.IProjectEnvironmentService;
import com.luckyframe.project.testmanagmt.projectTestData.domain.ProjectTestData;
import com.luckyframe.project.testmanagmt.projectTestData.service.IProjectTestDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试数据管理Controller
 * 
 * @author luckyframe
 * @date 2026-03-08
 */
@RestController
@RequestMapping("/testmanagmt/testData")
public class ProjectTestDataController extends BaseController
{
    @Autowired
    private IProjectTestDataService projectTestDataService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IProjectEnvironmentService projectEnvironmentService;

    /**
     * 获取测试数据管理列表
     */
    @RequiresPermissions("testmanagmt:testData:list")
    @GetMapping("/list")
    public TableDataInfo list(ProjectTestData projectTestData)
    {
        startPage();
        List<ProjectTestData> list = projectTestDataService.selectProjectTestDataList(projectTestData);
        return getDataTable(list);
    }

    /**
     * 导出测试数据管理列表
     */
    @RequiresPermissions("testmanagmt:testData:export")
    @Log(title = "测试数据管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProjectTestData projectTestData)
    {
        List<ProjectTestData> list = projectTestDataService.selectProjectTestDataList(projectTestData);
        ExcelUtil<ProjectTestData> util = new ExcelUtil<ProjectTestData>(ProjectTestData.class);
        return util.exportExcel(list, "测试数据管理");
    }

    /**
     * 获取测试数据管理详细信息
     */
    @RequiresPermissions("testmanagmt:testData:query")
    @GetMapping(value = "/{dataId}")
    public AjaxResult getInfo(@PathVariable("dataId") Long dataId)
    {
        return AjaxResult.success().put("testData", projectTestDataService.selectProjectTestDataById(dataId));
    }

    /**
     * 新增测试数据管理
     */
    @RequiresPermissions("testmanagmt:testData:add")
    @Log(title = "测试数据管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectTestData projectTestData)
    {
        return toAjax(projectTestDataService.insertProjectTestData(projectTestData));
    }

    /**
     * 修改测试数据管理
     */
    @RequiresPermissions("testmanagmt:testData:edit")
    @Log(title = "测试数据管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectTestData projectTestData)
    {
        return toAjax(projectTestDataService.updateProjectTestData(projectTestData));
    }

    /**
     * 删除测试数据管理
     */
    @RequiresPermissions("testmanagmt:testData:remove")
    @Log(title = "测试数据管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dataIds}")
    public AjaxResult remove(@PathVariable String[] dataIds)
    {
        return toAjax(projectTestDataService.deleteProjectTestDataByIds(dataIds));
    }

    /**
     * 根据项目ID和环境ID获取测试数据
     */
    @GetMapping("/byProjectAndEnvironment")
    public AjaxResult getTestDataByProjectAndEnvironment(Long projectId, Long environmentId)
    {
        List<ProjectTestData> list = projectTestDataService.selectProjectTestDataByProjectAndEnvironment(projectId, environmentId);
        return AjaxResult.success().put("testDataList", list);
    }

    /**
     * 生成测试数据
     */
    @RequiresPermissions("testmanagmt:testData:generate")
    @Log(title = "测试数据管理", businessType = BusinessType.OTHER)
    @PostMapping("/generate")
    public AjaxResult generateTestData(Long projectId, Long environmentId, String dataType, int count)
    {
        List<ProjectTestData> list = projectTestDataService.generateTestData(projectId, environmentId, dataType, count);
        return AjaxResult.success().put("testDataList", list);
    }

    /**
     * 获取项目列表
     */
    @GetMapping("/projects")
    public AjaxResult getProjects()
    {
        List<Project> list = projectService.selectProjectList(null);
        return AjaxResult.success().put("projectList", list);
    }

    /**
     * 根据项目ID获取环境列表
     */
    @GetMapping("/environments")
    public AjaxResult getEnvironments(Long projectId)
    {
        ProjectEnvironment environment = new ProjectEnvironment();
        environment.setProjectId(projectId != null ? projectId.intValue() : null);
        List<ProjectEnvironment> list = projectEnvironmentService.selectProjectEnvironmentList(environment);
        return AjaxResult.success().put("environmentList", list);
    }
}