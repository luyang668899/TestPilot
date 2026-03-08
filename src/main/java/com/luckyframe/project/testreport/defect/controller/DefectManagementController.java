package com.luckyframe.project.testreport.defect.controller;

import com.luckyframe.common.utils.poi.ExcelUtil;
import com.luckyframe.framework.aspectj.lang.annotation.Log;
import com.luckyframe.framework.aspectj.lang.enums.BusinessType;
import com.luckyframe.framework.web.controller.BaseController;
import com.luckyframe.framework.web.domain.AjaxResult;
import com.luckyframe.framework.web.page.TableDataInfo;
import com.luckyframe.project.testreport.defect.domain.DefectManagement;
import com.luckyframe.project.testreport.defect.service.IDefectManagementService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 缺陷管理集成控制器
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/testreport/defect")
public class DefectManagementController extends BaseController
{
    private String prefix = "testreport/defect";

    @Autowired
    private IDefectManagementService defectManagementService;

    @RequiresPermissions("testreport:defect:view")
    @GetMapping()
    public String defect()
    {
        return prefix + "/defect";
    }

    /**
     * 查询缺陷管理集成列表
     */
    @RequiresPermissions("testreport:defect:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DefectManagement defectManagement)
    {
        startPage();
        List<DefectManagement> list = defectManagementService.selectDefectManagementList(defectManagement);
        return getDataTable(list);
    }

    /**
     * 导出缺陷管理集成列表
     */
    @RequiresPermissions("testreport:defect:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DefectManagement defectManagement)
    {
        List<DefectManagement> list = defectManagementService.selectDefectManagementList(defectManagement);
        ExcelUtil<DefectManagement> util = new ExcelUtil<DefectManagement>(DefectManagement.class);
        return util.exportExcel(list, "缺陷管理集成数据");
    }

    /**
     * 新增缺陷管理集成
     */
    @RequiresPermissions("testreport:defect:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存缺陷管理集成
     */
    @RequiresPermissions("testreport:defect:add")
    @Log(title = "缺陷管理集成", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DefectManagement defectManagement)
    {
        return toAjax(defectManagementService.insertDefectManagement(defectManagement));
    }

    /**
     * 修改缺陷管理集成
     */
    @RequiresPermissions("testreport:defect:edit")
    @GetMapping("/edit/{defectId}")
    public String edit(@PathVariable("defectId") Long defectId, ModelMap mmap)
    {
        DefectManagement defectManagement = defectManagementService.selectDefectManagementById(defectId);
        mmap.put("defectManagement", defectManagement);
        return prefix + "/edit";
    }

    /**
     * 修改保存缺陷管理集成
     */
    @RequiresPermissions("testreport:defect:edit")
    @Log(title = "缺陷管理集成", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DefectManagement defectManagement)
    {
        return toAjax(defectManagementService.updateDefectManagement(defectManagement));
    }

    /**
     * 删除缺陷管理集成
     */
    @RequiresPermissions("testreport:defect:remove")
    @Log(title = "缺陷管理集成", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(defectManagementService.deleteDefectManagementByIds(ids.split(",")));
    }

    /**
     * 测试连接
     */
    @RequiresPermissions("testreport:defect:test")
    @PostMapping("/testConnection")
    @ResponseBody
    public AjaxResult testConnection(DefectManagement defectManagement)
    {
        boolean result = defectManagementService.testConnection(defectManagement);
        if (result) {
            return success("连接测试成功");
        } else {
            return error("连接测试失败");
        }
    }

    /**
     * 同步缺陷数据
     */
    @RequiresPermissions("testreport:defect:sync")
    @PostMapping("/sync")
    @ResponseBody
    public AjaxResult syncDefectData(@RequestParam("defectId") Long defectId)
    {
        int count = defectManagementService.syncDefectData(defectId);
        if (count > 0) {
            return success("同步成功，共同步 " + count + " 条缺陷数据");
        } else {
            return error("同步失败");
        }
    }

    /**
     * 创建缺陷
     */
    @RequiresPermissions("testreport:defect:create")
    @PostMapping("/create")
    @ResponseBody
    public AjaxResult createDefect(@RequestParam("defectId") Long defectId, @RequestParam("defectInfo") String defectInfo)
    {
        DefectManagement defectManagement = defectManagementService.selectDefectManagementById(defectId);
        if (defectManagement == null) {
            return error("缺陷管理配置不存在");
        }
        String defectIdStr = defectManagementService.createDefect(defectManagement, defectInfo);
        if (defectIdStr != null) {
            return success("缺陷创建成功，缺陷ID: " + defectIdStr);
        } else {
            return error("缺陷创建失败");
        }
    }
}