package com.luckyframe.project.plugin.service.impl;

import com.luckyframe.project.plugin.domain.Plugin;
import com.luckyframe.project.plugin.mapper.PluginMapper;
import com.luckyframe.project.plugin.service.IPluginService;
import com.luckyframe.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 插件管理Service实现
 * 
 * @author luckyframe
 * @date 2024-09-05
 */
@Service
public class PluginServiceImpl implements IPluginService 
{
    @Autowired
    private PluginMapper pluginMapper;

    /**
     * 查询插件管理
     * 
     * @param pluginId 插件管理ID
     * @return 插件管理
     */
    @Override
    public Plugin selectPluginById(Integer pluginId)
    {
        return pluginMapper.selectPluginById(pluginId);
    }

    /**
     * 查询插件管理列表
     * 
     * @param plugin 插件管理
     * @return 插件管理集合
     */
    @Override
    public List<Plugin> selectPluginList(Plugin plugin)
    {
        return pluginMapper.selectPluginList(plugin);
    }

    /**
     * 新增插件管理
     * 
     * @param plugin 插件管理
     * @return 结果
     */
    @Override
    public int insertPlugin(Plugin plugin)
    {
        plugin.setCreateTime(DateUtils.getNowDate());
        return pluginMapper.insertPlugin(plugin);
    }

    /**
     * 修改插件管理
     * 
     * @param plugin 插件管理
     * @return 结果
     */
    @Override
    public int updatePlugin(Plugin plugin)
    {
        plugin.setUpdateTime(DateUtils.getNowDate());
        return pluginMapper.updatePlugin(plugin);
    }

    /**
     * 删除插件管理
     * 
     * @param pluginId 插件管理ID
     * @return 结果
     */
    @Override
    public int deletePluginById(Integer pluginId)
    {
        return pluginMapper.deletePluginById(pluginId);
    }

    /**
     * 批量删除插件管理
     * 
     * @param pluginIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePluginByIds(String[] pluginIds)
    {
        return pluginMapper.deletePluginByIds(pluginIds);
    }

    /**
     * 启用插件
     * 
     * @param pluginId 插件管理ID
     * @return 结果
     */
    @Override
    public int enablePlugin(Integer pluginId)
    {
        Plugin plugin = new Plugin();
        plugin.setPluginId(pluginId);
        plugin.setStatus("启用");
        plugin.setUpdateTime(DateUtils.getNowDate());
        return pluginMapper.updatePlugin(plugin);
    }

    /**
     * 禁用插件
     * 
     * @param pluginId 插件管理ID
     * @return 结果
     */
    @Override
    public int disablePlugin(Integer pluginId)
    {
        Plugin plugin = new Plugin();
        plugin.setPluginId(pluginId);
        plugin.setStatus("禁用");
        plugin.setUpdateTime(DateUtils.getNowDate());
        return pluginMapper.updatePlugin(plugin);
    }

    /**
     * 测试插件
     * 
     * @param pluginId 插件管理ID
     * @return 结果
     */
    @Override
    public String testPlugin(Integer pluginId)
    {
        try {
            Plugin plugin = pluginMapper.selectPluginById(pluginId);
            if (plugin == null) {
                return "插件不存在";
            }

            // 这里可以添加插件测试逻辑
            // 例如：加载插件、执行测试方法等
            return "插件测试成功";
        } catch (Exception e) {
            return "插件测试失败: " + e.getMessage();
        }
    }
}
