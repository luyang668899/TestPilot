package com.luckyframe.project.plugin.mapper;

import com.luckyframe.project.plugin.domain.Plugin;
import java.util.List;

/**
 * 插件管理Mapper接口
 * 
 * @author luckyframe
 * @date 2024-09-05
 */
public interface PluginMapper 
{
    /**
     * 查询插件管理
     * 
     * @param pluginId 插件管理ID
     * @return 插件管理
     */
    public Plugin selectPluginById(Integer pluginId);

    /**
     * 查询插件管理列表
     * 
     * @param plugin 插件管理
     * @return 插件管理集合
     */
    public List<Plugin> selectPluginList(Plugin plugin);

    /**
     * 新增插件管理
     * 
     * @param plugin 插件管理
     * @return 结果
     */
    public int insertPlugin(Plugin plugin);

    /**
     * 修改插件管理
     * 
     * @param plugin 插件管理
     * @return 结果
     */
    public int updatePlugin(Plugin plugin);

    /**
     * 删除插件管理
     * 
     * @param pluginId 插件管理ID
     * @return 结果
     */
    public int deletePluginById(Integer pluginId);

    /**
     * 批量删除插件管理
     * 
     * @param pluginIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePluginByIds(String[] pluginIds);
}
