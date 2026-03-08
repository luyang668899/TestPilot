package com.luckyframe.project.cloud.controller;

import com.luckyframe.framework.aspectj.lang.annotation.Log;
import com.luckyframe.framework.web.controller.BaseController;
import com.luckyframe.framework.web.domain.AjaxResult;
import com.luckyframe.framework.web.page.TableDataInfo;
import com.luckyframe.framework.aspectj.lang.enums.BusinessType;
import com.luckyframe.common.utils.poi.ExcelUtil;
import com.luckyframe.project.cloud.domain.CloudResource;
import com.luckyframe.project.cloud.service.ICloudResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 云资源Controller
 * 
 * @author ruoyi
 * @date 2023-09-15
 */
@Controller
@RequestMapping("/cloud/resource")
public class CloudResourceController extends BaseController
{
    private String prefix = "cloud/resource";

    @Autowired
    private ICloudResourceService cloudResourceService;

    @RequiresPermissions("cloud:resource:view")
    @GetMapping()
    public String resource()
    {
        return prefix + "/resource";
    }

    /**
     * 查询云资源列表
     */
    @RequiresPermissions("cloud:resource:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CloudResource cloudResource)
    {
        startPage();
        List<CloudResource> list = cloudResourceService.selectCloudResourceList(cloudResource);
        return getDataTable(list);
    }

    /**
     * 导出云资源列表
     */
    @RequiresPermissions("cloud:resource:export")
    @Log(title = "云资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CloudResource cloudResource)
    {
        List<CloudResource> list = cloudResourceService.selectCloudResourceList(cloudResource);
        ExcelUtil<CloudResource> util = new ExcelUtil<CloudResource>(CloudResource.class);
        return util.exportExcel(list, "云资源数据");
    }

    /**
     * 新增云资源
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存云资源
     */
    @RequiresPermissions("cloud:resource:add")
    @Log(title = "云资源", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CloudResource cloudResource)
    {
        return toAjax(cloudResourceService.insertCloudResource(cloudResource));
    }

    /**
     * 修改云资源
     */
    @RequiresPermissions("cloud:resource:edit")
    @GetMapping("/edit/{resourceId}")
    public String edit(@PathVariable("resourceId") Integer resourceId, ModelMap mmap)
    {
        CloudResource cloudResource = cloudResourceService.selectCloudResourceByResourceId(resourceId);
        mmap.put("cloudResource", cloudResource);
        return prefix + "/edit";
    }

    /**
     * 修改保存云资源
     */
    @RequiresPermissions("cloud:resource:edit")
    @Log(title = "云资源", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CloudResource cloudResource)
    {
        return toAjax(cloudResourceService.updateCloudResource(cloudResource));
    }

    /**
     * 删除云资源
     */
    @RequiresPermissions("cloud:resource:remove")
    @Log(title = "云资源", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        String[] idArray = ids.split(",");
        Integer[] resourceIds = new Integer[idArray.length];
        for (int i = 0; i < idArray.length; i++) {
            resourceIds[i] = Integer.parseInt(idArray[i]);
        }
        return toAjax(cloudResourceService.deleteCloudResourceByResourceIds(resourceIds));
    }

    /**
     * 创建云资源
     */
    @RequiresPermissions("cloud:resource:create")
    @Log(title = "云资源", businessType = BusinessType.OTHER)
    @PostMapping("/create")
    @ResponseBody
    public AjaxResult createResource(@RequestParam Integer serviceId, @RequestParam String resourceName, 
                                     @RequestParam String resourceType, @RequestParam String params)
    {
        CloudResource resource = cloudResourceService.createCloudResource(serviceId, resourceName, resourceType, params);
        AjaxResult result = new AjaxResult();
        result.put("code", 0);
        result.put("msg", "资源创建成功");
        result.put("data", resource);
        return result;
    }

    /**
     * 删除云资源（云平台）
     */
    @RequiresPermissions("cloud:resource:delete")
    @Log(title = "云资源", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult deleteResource(@RequestParam Integer resourceId)
    {
        boolean result = cloudResourceService.deleteCloudResource(resourceId);
        return result ? AjaxResult.success("资源删除成功") : AjaxResult.error("资源删除失败");
    }

    /**
     * 获取云资源状态
     */
    @PostMapping("/status")
    @ResponseBody
    public AjaxResult getResourceStatus(@RequestParam Integer resourceId)
    {
        String status = cloudResourceService.getCloudResourceStatus(resourceId);
        AjaxResult result = new AjaxResult();
        result.put("code", 0);
        result.put("msg", "获取状态成功");
        result.put("data", status);
        return result;
    }

    /**
     * 列出云平台资源
     */
    @PostMapping("/listPlatform")
    @ResponseBody
    public AjaxResult listPlatformResources(@RequestParam Integer serviceId, @RequestParam String resourceType)
    {
        List<CloudResource> resources = cloudResourceService.listCloudPlatformResources(serviceId, resourceType);
        AjaxResult result = new AjaxResult();
        result.put("code", 0);
        result.put("msg", "获取资源列表成功");
        result.put("data", resources);
        return result;
    }
}
