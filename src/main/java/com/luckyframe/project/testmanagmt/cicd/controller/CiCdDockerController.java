package com.luckyframe.project.testmanagmt.cicd.controller;

import com.luckyframe.project.testmanagmt.cicd.domain.CiCdDocker;
import com.luckyframe.project.testmanagmt.cicd.service.ICiCdDockerService;
import com.luckyframe.common.utils.poi.ExcelUtil;
import com.luckyframe.framework.aspectj.lang.annotation.Log;
import com.luckyframe.framework.aspectj.lang.enums.BusinessType;
import com.luckyframe.framework.web.controller.BaseController;
import com.luckyframe.framework.web.domain.AjaxResult;
import com.luckyframe.framework.web.page.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Docker容器管理Controller
 * 
 * @author luckyframe
 * @date 2024-09-05
 */
@Controller
@RequestMapping("/testmanagmt/docker")
public class CiCdDockerController extends BaseController
{
    private String prefix = "testmanagmt/cicd/docker";

    @Autowired
    private ICiCdDockerService ciCdDockerService;

    @RequiresPermissions("testmanagmt:docker:view")
    @GetMapping()
    public String docker()
    {
        return prefix + "/docker";
    }

    /**
     * 查询Docker容器管理列表
     */
    @RequiresPermissions("testmanagmt:docker:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CiCdDocker ciCdDocker)
    {
        startPage();
        List<CiCdDocker> list = ciCdDockerService.selectCiCdDockerList(ciCdDocker);
        return getDataTable(list);
    }

    /**
     * 导出Docker容器管理列表
     */
    @RequiresPermissions("testmanagmt:docker:export")
    @Log(title = "Docker容器管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CiCdDocker ciCdDocker)
    {
        List<CiCdDocker> list = ciCdDockerService.selectCiCdDockerList(ciCdDocker);
        ExcelUtil<CiCdDocker> util = new ExcelUtil<CiCdDocker>(CiCdDocker.class);
        return util.exportExcel(list, "Docker容器管理数据");
    }

    /**
     * 新增Docker容器管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存Docker容器管理
     */
    @RequiresPermissions("testmanagmt:docker:add")
    @Log(title = "Docker容器管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CiCdDocker ciCdDocker)
    {
        return toAjax(ciCdDockerService.insertCiCdDocker(ciCdDocker));
    }

    /**
     * 修改Docker容器管理
     */
    @GetMapping("/edit/{dockerId}")
    public String edit(@PathVariable("dockerId") Integer dockerId, ModelMap mmap)
    {
        CiCdDocker ciCdDocker = ciCdDockerService.selectCiCdDockerById(dockerId);
        mmap.put("ciCdDocker", ciCdDocker);
        return prefix + "/edit";
    }

    /**
     * 修改保存Docker容器管理
     */
    @RequiresPermissions("testmanagmt:docker:edit")
    @Log(title = "Docker容器管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CiCdDocker ciCdDocker)
    {
        return toAjax(ciCdDockerService.updateCiCdDocker(ciCdDocker));
    }

    /**
     * 删除Docker容器管理
     */
    @RequiresPermissions("testmanagmt:docker:remove")
    @Log(title = "Docker容器管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ciCdDockerService.deleteCiCdDockerByIds(ids.split(",")));
    }

    /**
     * 测试Docker连接
     */
    @PostMapping("/testConnection")
    @ResponseBody
    public AjaxResult testConnection(CiCdDocker ciCdDocker)
    {
        String result = ciCdDockerService.testDockerConnection(ciCdDocker);
        return AjaxResult.success(result);
    }

    /**
     * 执行Docker容器测试
     */
    @PostMapping("/executeTest")
    @ResponseBody
    public AjaxResult executeTest(Integer dockerId, Integer testCaseId)
    {
        String result = ciCdDockerService.executeDockerTest(dockerId, testCaseId);
        return AjaxResult.success(result);
    }
}
