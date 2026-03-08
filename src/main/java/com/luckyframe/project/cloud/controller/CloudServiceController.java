package com.luckyframe.project.cloud.controller;

import com.luckyframe.project.cloud.domain.CloudService;
import com.luckyframe.project.cloud.service.ICloudServiceService;
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
 * 云服务管理Controller
 * 
 * @author luckyframe
 * @date 2024-09-05
 */
@Controller
@RequestMapping("/cloud")
public class CloudServiceController extends BaseController
{
    private String prefix = "cloud";

    @Autowired
    private ICloudServiceService cloudServiceService;

    @RequiresPermissions("cloud:view")
    @GetMapping()
    public String cloud()
    {
        return prefix + "/cloud";
    }

    @RequiresPermissions("cloud:resource:view")
    @GetMapping("/resource")
    public String resource()
    {
        return "cloud/resource/resource";
    }

    @RequiresPermissions("cloud:execution:view")
    @GetMapping("/execution")
    public String execution()
    {
        return "cloud/execution/execution";
    }

    /**
     * 查询云服务管理列表
     */
    @RequiresPermissions("cloud:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CloudService cloudService)
    {
        startPage();
        List<CloudService> list = cloudServiceService.selectCloudServiceList(cloudService);
        return getDataTable(list);
    }

    /**
     * 导出云服务管理列表
     */
    @RequiresPermissions("cloud:export")
    @Log(title = "云服务管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CloudService cloudService)
    {
        List<CloudService> list = cloudServiceService.selectCloudServiceList(cloudService);
        ExcelUtil<CloudService> util = new ExcelUtil<CloudService>(CloudService.class);
        return util.exportExcel(list, "云服务管理数据");
    }

    /**
     * 新增云服务管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存云服务管理
     */
    @RequiresPermissions("cloud:add")
    @Log(title = "云服务管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CloudService cloudService)
    {
        return toAjax(cloudServiceService.insertCloudService(cloudService));
    }

    /**
     * 修改云服务管理
     */
    @GetMapping("/edit/{cloudId}")
    public String edit(@PathVariable("cloudId") Integer cloudId, ModelMap mmap)
    {
        CloudService cloudService = cloudServiceService.selectCloudServiceById(cloudId);
        mmap.put("cloudService", cloudService);
        return prefix + "/edit";
    }

    /**
     * 修改保存云服务管理
     */
    @RequiresPermissions("cloud:edit")
    @Log(title = "云服务管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CloudService cloudService)
    {
        return toAjax(cloudServiceService.updateCloudService(cloudService));
    }

    /**
     * 删除云服务管理
     */
    @RequiresPermissions("cloud:remove")
    @Log(title = "云服务管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cloudServiceService.deleteCloudServiceByIds(ids.split(",")));
    }

    /**
     * 测试云服务连接
     */
    @PostMapping("/testConnection")
    @ResponseBody
    public AjaxResult testConnection(CloudService cloudService)
    {
        String result = cloudServiceService.testCloudConnection(cloudService);
        return AjaxResult.success(result);
    }

    /**
     * 列出云服务资源
     */
    @PostMapping("/listResources")
    @ResponseBody
    public AjaxResult listResources(Integer cloudId)
    {
        String result = cloudServiceService.listCloudResources(cloudId);
        return AjaxResult.success(result);
    }

    /**
     * 创建云服务资源
     */
    @PostMapping("/createResource")
    @ResponseBody
    public AjaxResult createResource(Integer cloudId, String resourceType, String resourceName, String resourceConfig)
    {
        String result = cloudServiceService.createCloudResource(cloudId, resourceType, resourceName, resourceConfig);
        return AjaxResult.success(result);
    }

    /**
     * 删除云服务资源
     */
    @PostMapping("/deleteResource")
    @ResponseBody
    public AjaxResult deleteResource(Integer cloudId, String resourceType, String resourceName)
    {
        String result = cloudServiceService.deleteCloudResource(cloudId, resourceType, resourceName);
        return AjaxResult.success(result);
    }

    /**
     * 在云环境执行测试
     */
    @PostMapping("/executeTest")
    @ResponseBody
    public AjaxResult executeTest(Integer cloudId, Integer testCaseId, String resourceId)
    {
        String result = cloudServiceService.executeTestInCloud(cloudId, testCaseId, resourceId);
        return AjaxResult.success(result);
    }
}
