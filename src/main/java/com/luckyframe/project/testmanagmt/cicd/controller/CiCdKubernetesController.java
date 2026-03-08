package com.luckyframe.project.testmanagmt.cicd.controller;

import com.luckyframe.project.testmanagmt.cicd.domain.CiCdKubernetes;
import com.luckyframe.project.testmanagmt.cicd.service.ICiCdKubernetesService;
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
 * Kubernetes集群管理Controller
 * 
 * @author luckyframe
 * @date 2024-09-05
 */
@Controller
@RequestMapping("/testmanagmt/kubernetes")
public class CiCdKubernetesController extends BaseController
{
    private String prefix = "testmanagmt/cicd/kubernetes";

    @Autowired
    private ICiCdKubernetesService ciCdKubernetesService;

    @RequiresPermissions("testmanagmt:kubernetes:view")
    @GetMapping()
    public String kubernetes()
    {
        return prefix + "/kubernetes";
    }

    /**
     * 查询Kubernetes集群管理列表
     */
    @RequiresPermissions("testmanagmt:kubernetes:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CiCdKubernetes ciCdKubernetes)
    {
        startPage();
        List<CiCdKubernetes> list = ciCdKubernetesService.selectCiCdKubernetesList(ciCdKubernetes);
        return getDataTable(list);
    }

    /**
     * 导出Kubernetes集群管理列表
     */
    @RequiresPermissions("testmanagmt:kubernetes:export")
    @Log(title = "Kubernetes集群管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CiCdKubernetes ciCdKubernetes)
    {
        List<CiCdKubernetes> list = ciCdKubernetesService.selectCiCdKubernetesList(ciCdKubernetes);
        ExcelUtil<CiCdKubernetes> util = new ExcelUtil<CiCdKubernetes>(CiCdKubernetes.class);
        return util.exportExcel(list, "Kubernetes集群管理数据");
    }

    /**
     * 新增Kubernetes集群管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存Kubernetes集群管理
     */
    @RequiresPermissions("testmanagmt:kubernetes:add")
    @Log(title = "Kubernetes集群管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CiCdKubernetes ciCdKubernetes)
    {
        return toAjax(ciCdKubernetesService.insertCiCdKubernetes(ciCdKubernetes));
    }

    /**
     * 修改Kubernetes集群管理
     */
    @GetMapping("/edit/{kubernetesId}")
    public String edit(@PathVariable("kubernetesId") Integer kubernetesId, ModelMap mmap)
    {
        CiCdKubernetes ciCdKubernetes = ciCdKubernetesService.selectCiCdKubernetesById(kubernetesId);
        mmap.put("ciCdKubernetes", ciCdKubernetes);
        return prefix + "/edit";
    }

    /**
     * 修改保存Kubernetes集群管理
     */
    @RequiresPermissions("testmanagmt:kubernetes:edit")
    @Log(title = "Kubernetes集群管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CiCdKubernetes ciCdKubernetes)
    {
        return toAjax(ciCdKubernetesService.updateCiCdKubernetes(ciCdKubernetes));
    }

    /**
     * 删除Kubernetes集群管理
     */
    @RequiresPermissions("testmanagmt:kubernetes:remove")
    @Log(title = "Kubernetes集群管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ciCdKubernetesService.deleteCiCdKubernetesByIds(ids.split(",")));
    }

    /**
     * 测试Kubernetes连接
     */
    @PostMapping("/testConnection")
    @ResponseBody
    public AjaxResult testConnection(CiCdKubernetes ciCdKubernetes)
    {
        String result = ciCdKubernetesService.testKubernetesConnection(ciCdKubernetes);
        return AjaxResult.success(result);
    }

    /**
     * 执行Kubernetes集群测试
     */
    @PostMapping("/executeTest")
    @ResponseBody
    public AjaxResult executeTest(Integer kubernetesId, Integer testCaseId)
    {
        String result = ciCdKubernetesService.executeKubernetesTest(kubernetesId, testCaseId);
        return AjaxResult.success(result);
    }
}
