package com.luckyframe.project.testmanagmt.projectTestData.domain;

import com.luckyframe.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 测试数据管理对象 project_test_data
 * 
 * @author luckyframe
 * @date 2026-03-08
 */
public class ProjectTestData
{
    private static final long serialVersionUID = 1L;

    /** 测试数据ID */
    @Excel(name = "测试数据ID")
    private Long dataId;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long projectId;

    /** 数据名称 */
    @Excel(name = "数据名称")
    private String dataName;

    /** 数据类型 */
    @Excel(name = "数据类型")
    private String dataType;

    /** 数据值 */
    @Excel(name = "数据值")
    private String dataValue;

    /** 环境ID */
    @Excel(name = "环境ID")
    private Long environmentId;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private Date updateTime;

    public void setDataId(Long dataId)
    {
        this.dataId = dataId;
    }

    public Long getDataId() 
    {
        return dataId;
    }

    public void setProjectId(Long projectId)
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }

    public void setDataName(String dataName)
    {
        this.dataName = dataName;
    }

    public String getDataName() 
    {
        return dataName;
    }

    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }

    public String getDataType() 
    {
        return dataType;
    }

    public void setDataValue(String dataValue)
    {
        this.dataValue = dataValue;
    }

    public String getDataValue() 
    {
        return dataValue;
    }

    public void setEnvironmentId(Long environmentId)
    {
        this.environmentId = environmentId;
    }

    public Long getEnvironmentId() 
    {
        return environmentId;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dataId", getDataId())
            .append("projectId", getProjectId())
            .append("dataName", getDataName())
            .append("dataType", getDataType())
            .append("dataValue", getDataValue())
            .append("environmentId", getEnvironmentId())
            .append("description", getDescription())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}