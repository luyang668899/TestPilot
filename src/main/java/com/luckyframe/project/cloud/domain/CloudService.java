package com.luckyframe.project.cloud.domain;

import com.luckyframe.framework.aspectj.lang.annotation.Excel;
import com.luckyframe.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 云服务管理对象 cloud_service
 * 
 * @author luckyframe
 * @date 2024-09-05
 */
public class CloudService extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @Excel(name = "主键ID")
    private Integer cloudId;

    /** 云服务名称 */
    @Excel(name = "云服务名称")
    private String cloudName;

    /** 云服务类型 */
    @Excel(name = "云服务类型")
    private String cloudType;

    /** 访问密钥 */
    @Excel(name = "访问密钥")
    private String accessKey;

    /** 秘密密钥 */
    @Excel(name = "秘密密钥")
    private String secretKey;

    /** 区域 */
    @Excel(name = "区域")
    private String region;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

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

    public void setCloudId(Integer cloudId)
    {
        this.cloudId = cloudId;
    }

    public Integer getCloudId() 
    {
        return cloudId;
    }

    public void setCloudName(String cloudName)
    {
        this.cloudName = cloudName;
    }

    public String getCloudName() 
    {
        return cloudName;
    }

    public void setCloudType(String cloudType)
    {
        this.cloudType = cloudType;
    }

    public String getCloudType() 
    {
        return cloudType;
    }

    public void setAccessKey(String accessKey)
    {
        this.accessKey = accessKey;
    }

    public String getAccessKey() 
    {
        return accessKey;
    }

    public void setSecretKey(String secretKey)
    {
        this.secretKey = secretKey;
    }

    public String getSecretKey() 
    {
        return secretKey;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getRegion() 
    {
        return region;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
