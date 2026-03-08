package com.luckyframe.project.plugin.domain;

import com.luckyframe.framework.aspectj.lang.annotation.Excel;
import com.luckyframe.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 插件管理对象 plugin
 * 
 * @author luckyframe
 * @date 2024-09-05
 */
public class Plugin extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @Excel(name = "主键ID")
    private Integer pluginId;

    /** 插件名称 */
    @Excel(name = "插件名称")
    private String pluginName;

    /** 插件类型 */
    @Excel(name = "插件类型")
    private String pluginType;

    /** 插件版本 */
    @Excel(name = "插件版本")
    private String pluginVersion;

    /** 插件路径 */
    @Excel(name = "插件路径")
    private String pluginPath;

    /** 插件状态 */
    @Excel(name = "插件状态")
    private String status;

    /** 插件描述 */
    @Excel(name = "插件描述")
    private String description;

    /** 创建者 */
    @Excel(name = "创建者")
    private String createBy;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    @Excel(name = "更新者")
    private String updateBy;

    /** 更新时间 */
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public void setPluginId(Integer pluginId)
    {
        this.pluginId = pluginId;
    }

    public Integer getPluginId() 
    {
        return pluginId;
    }

    public void setPluginName(String pluginName)
    {
        this.pluginName = pluginName;
    }

    public String getPluginName() 
    {
        return pluginName;
    }

    public void setPluginType(String pluginType)
    {
        this.pluginType = pluginType;
    }

    public String getPluginType() 
    {
        return pluginType;
    }

    public void setPluginVersion(String pluginVersion)
    {
        this.pluginVersion = pluginVersion;
    }

    public String getPluginVersion() 
    {
        return pluginVersion;
    }

    public void setPluginPath(String pluginPath)
    {
        this.pluginPath = pluginPath;
    }

    public String getPluginPath() 
    {
        return pluginPath;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public void setCreateBy(String createBy)
    {
        this.createBy = createBy;
    }

    @Override
    public String getCreateBy() 
    {
        return createBy;
    }

    @Override
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    @Override
    public Date getCreateTime() 
    {
        return createTime;
    }

    @Override
    public void setUpdateBy(String updateBy)
    {
        this.updateBy = updateBy;
    }

    @Override
    public String getUpdateBy() 
    {
        return updateBy;
    }

    @Override
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    @Override
    public Date getUpdateTime() 
    {
        return updateTime;
    }
}
