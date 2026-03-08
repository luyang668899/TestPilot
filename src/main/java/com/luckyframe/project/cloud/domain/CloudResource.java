package com.luckyframe.project.cloud.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luckyframe.framework.aspectj.lang.annotation.Excel;
import com.luckyframe.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 云资源对象 cloud_resource
 * 
 * @author ruoyi
 * @date 2023-09-15
 */
public class CloudResource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资源ID */
    private Integer resourceId;

    /** 服务ID */
    private Integer serviceId;

    /** 资源名称 */
    @Excel(name = "资源名称")
    private String resourceName;

    /** 资源类型 */
    @Excel(name = "资源类型")
    private String resourceType;

    /** 云平台资源ID */
    @Excel(name = "云平台资源ID")
    private String resourceIdCloud;

    /** 资源状态 */
    @Excel(name = "资源状态")
    private String status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
    private String remark;

    public void setResourceId(Integer resourceId)
    {
        this.resourceId = resourceId;
    }

    public Integer getResourceId()
    {
        return resourceId;
    }
    public void setServiceId(Integer serviceId)
    {
        this.serviceId = serviceId;
    }

    public Integer getServiceId()
    {
        return serviceId;
    }
    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public String getResourceName()
    {
        return resourceName;
    }
    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }

    public String getResourceType()
    {
        return resourceType;
    }
    public void setResourceIdCloud(String resourceIdCloud)
    {
        this.resourceIdCloud = resourceIdCloud;
    }

    public String getResourceIdCloud()
    {
        return resourceIdCloud;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getRemark()
    {
        return remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("resourceId", getResourceId())
            .append("serviceId", getServiceId())
            .append("resourceName", getResourceName())
            .append("resourceType", getResourceType())
            .append("resourceIdCloud", getResourceIdCloud())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
