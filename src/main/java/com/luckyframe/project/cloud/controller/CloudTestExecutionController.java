package com.luckyframe.project.cloud.controller;

import com.luckyframe.framework.aspectj.lang.annotation.Log;
import com.luckyframe.framework.web.controller.BaseController;
import com.luckyframe.framework.web.domain.AjaxResult;
import com.luckyframe.framework.web.page.TableDataInfo;
import com.luckyframe.framework.aspectj.lang.enums.BusinessType;
import com.luckyframe.common.utils.poi.ExcelUtil;
import com.luckyframe.project.cloud.domain.CloudTestExecution;
import com.luckyframe.project.cloud.service.ICloudTestExecutionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 云测试执行Controller
 * 
 * @author ruoyi
 * @date 2023-09-15
 */
@Controller
@RequestMapping("/cloud/execution")
public class CloudTestExecutionController extends BaseController
{
    private String prefix = "cloud/execution";

    @Autowired
    private ICloudTestExecutionService cloudTestExecutionService;

    @RequiresPermissions("cloud:execution:view")
    @GetMapping()
    public String execution()
    {
        return prefix + "/execution";
    }

    /**
     * 查询云测试执行列表
     */
    @RequiresPermissions("cloud:execution:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CloudTestExecution cloudTestExecution)
    {
        startPage();
        List<CloudTestExecution> list = cloudTestExecutionService.selectCloudTestExecutionList(cloudTestExecution);
        return getDataTable(list);
    }

    /**
     * 导出云测试执行列表
     */
    @RequiresPermissions("cloud:execution:export")
    @Log(title = "云测试执行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CloudTestExecution cloudTestExecution)
    {
        List<CloudTestExecution> list = cloudTestExecutionService.selectCloudTestExecutionList(cloudTestExecution);
        ExcelUtil<CloudTestExecution> util = new ExcelUtil<CloudTestExecution>(CloudTestExecution.class);
        return util.exportExcel(list, "云测试执行数据");
    }

    /**
     * 新增云测试执行
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存云测试执行
     */
    @RequiresPermissions("cloud:execution:add")
    @Log(title = "云测试执行", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CloudTestExecution cloudTestExecution)
    {
        return toAjax(cloudTestExecutionService.insertCloudTestExecution(cloudTestExecution));
    }

    /**
     * 修改云测试执行
     */
    @RequiresPermissions("cloud:execution:edit")
    @GetMapping("/edit/{executionId}")
    public String edit(@PathVariable("executionId") Integer executionId, ModelMap mmap)
    {
        CloudTestExecution cloudTestExecution = cloudTestExecutionService.selectCloudTestExecutionByExecutionId(executionId);
        mmap.put("cloudTestExecution", cloudTestExecution);
        return prefix + "/edit";
    }

    /**
     * 修改保存云测试执行
     */
    @RequiresPermissions("cloud:execution:edit")
    @Log(title = "云测试执行", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CloudTestExecution cloudTestExecution)
    {
        return toAjax(cloudTestExecutionService.updateCloudTestExecution(cloudTestExecution));
    }

    /**
     * 删除云测试执行
     */
    @RequiresPermissions("cloud:execution:remove")
    @Log(title = "云测试执行", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        String[] idArray = ids.split(",");
        Integer[] executionIds = new Integer[idArray.length];
        for (int i = 0; i < idArray.length; i++) {
            executionIds[i] = Integer.parseInt(idArray[i]);
        }
        return toAjax(cloudTestExecutionService.deleteCloudTestExecutionByExecutionIds(executionIds));
    }

    /**
     * 执行云测试
     */
    @RequiresPermissions("cloud:execution:execute")
    @Log(title = "云测试执行", businessType = BusinessType.OTHER)
    @PostMapping("/execute")
    @ResponseBody
    public AjaxResult executeTest(@RequestParam Integer serviceId, @RequestParam Integer resourceId, @RequestParam Integer testTaskId)
    {
        Integer executionId = cloudTestExecutionService.executeCloudTest(serviceId, resourceId, testTaskId);
        AjaxResult result = new AjaxResult();
        result.put("code", 0);
        result.put("msg", "测试执行开始");
        result.put("data", executionId);
        return result;
    }

    /**
     * 获取测试执行状态
     */
    @PostMapping("/status")
    @ResponseBody
    public AjaxResult getExecutionStatus(@RequestParam Integer executionId)
    {
        String status = cloudTestExecutionService.getTestExecutionStatus(executionId);
        AjaxResult result = new AjaxResult();
        result.put("code", 0);
        result.put("msg", "获取状态成功");
        result.put("data", status);
        return result;
    }

    /**
     * 获取测试执行结果
     */
    @PostMapping("/result")
    @ResponseBody
    public AjaxResult getExecutionResult(@RequestParam Integer executionId)
    {
        String result = cloudTestExecutionService.getTestExecutionResult(executionId);
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("code", 0);
        ajaxResult.put("msg", "获取结果成功");
        ajaxResult.put("data", result);
        return ajaxResult;
    }

    /**
     * 停止测试执行
     */
    @RequiresPermissions("cloud:execution:stop")
    @Log(title = "云测试执行", businessType = BusinessType.OTHER)
    @PostMapping("/stop")
    @ResponseBody
    public AjaxResult stopExecution(@RequestParam Integer executionId)
    {
        boolean result = cloudTestExecutionService.stopTestExecution(executionId);
        return result ? AjaxResult.success("测试执行已停止") : AjaxResult.error("停止测试执行失败");
    }
}
