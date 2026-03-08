package com.luckyframe.project.plugin.controller;

import com.luckyframe.project.plugin.domain.Plugin;
import com.luckyframe.project.plugin.service.IPluginService;
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
 * 插件管理Controller
 * 
 * @author luckyframe
 * @date 2024-09-05
 */
@Controller
@RequestMapping("/plugin")
public class PluginController extends BaseController
{
    private String prefix = "plugin";

    @Autowired
    private IPluginService pluginService;

    @RequiresPermissions("plugin:view")
    @GetMapping()
    public String plugin()
    {
        return prefix + "/plugin";
    }

    /**
     * 查询插件管理列表
     */
    @RequiresPermissions("plugin:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Plugin plugin)
    {
        startPage();
        List<Plugin> list = pluginService.selectPluginList(plugin);
        return getDataTable(list);
    }

    /**
     * 导出插件管理列表
     */
    @RequiresPermissions("plugin:export")
    @Log(title = "插件管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Plugin plugin)
    {
        List<Plugin> list = pluginService.selectPluginList(plugin);
        ExcelUtil<Plugin> util = new ExcelUtil<Plugin>(Plugin.class);
        return util.exportExcel(list, "插件管理数据");
    }

    /**
     * 新增插件管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存插件管理
     */
    @RequiresPermissions("plugin:add")
    @Log(title = "插件管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Plugin plugin)
    {
        return toAjax(pluginService.insertPlugin(plugin));
    }

    /**
     * 修改插件管理
     */
    @GetMapping("/edit/{pluginId}")
    public String edit(@PathVariable("pluginId") Integer pluginId, ModelMap mmap)
    {
        Plugin plugin = pluginService.selectPluginById(pluginId);
        mmap.put("plugin", plugin);
        return prefix + "/edit";
    }

    /**
     * 修改保存插件管理
     */
    @RequiresPermissions("plugin:edit")
    @Log(title = "插件管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Plugin plugin)
    {
        return toAjax(pluginService.updatePlugin(plugin));
    }

    /**
     * 删除插件管理
     */
    @RequiresPermissions("plugin:remove")
    @Log(title = "插件管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(pluginService.deletePluginByIds(ids.split(",")));
    }

    /**
     * 启用插件
     */
    @PostMapping("/enable")
    @ResponseBody
    public AjaxResult enable(Integer pluginId)
    {
        return toAjax(pluginService.enablePlugin(pluginId));
    }

    /**
     * 禁用插件
     */
    @PostMapping("/disable")
    @ResponseBody
    public AjaxResult disable(Integer pluginId)
    {
        return toAjax(pluginService.disablePlugin(pluginId));
    }

    /**
     * 测试插件
     */
    @PostMapping("/test")
    @ResponseBody
    public AjaxResult test(Integer pluginId)
    {
        String result = pluginService.testPlugin(pluginId);
        return AjaxResult.success(result);
    }
}
