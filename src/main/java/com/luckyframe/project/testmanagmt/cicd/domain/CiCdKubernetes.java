package com.luckyframe.project.testmanagmt.cicd.domain;

import com.luckyframe.framework.aspectj.lang.annotation.Excel;
import com.luckyframe.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * Kubernetes集群管理对象 ci_cd_kubernetes
 * 
 * @author luckyframe
 * @date 2024-09-05
 */
public class CiCdKubernetes extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @Excel(name = "主键ID")
    private Integer kubernetesId;

    /** Kubernetes名称 */
    @Excel(name = "Kubernetes名称")
    private String kubernetesName;

    /** 集群地址 */
    @Excel(name = "集群地址")
    private String clusterUrl;

    /** 命名空间 */
    @Excel(name = "命名空间")
    private String namespace;

    /** 认证方式 */
    @Excel(name = "认证方式")
    private String authType;

    /** Kubeconfig文件路径 */
    @Excel(name = "Kubeconfig文件路径")
    private String kubeconfigPath;

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

    public void setKubernetesId(Integer kubernetesId)
    {
        this.kubernetesId = kubernetesId;
    }

    public Integer getKubernetesId() 
    {
        return kubernetesId;
    }

    public void setKubernetesName(String kubernetesName)
    {
        this.kubernetesName = kubernetesName;
    }

    public String getKubernetesName() 
    {
        return kubernetesName;
    }

    public void setClusterUrl(String clusterUrl)
    {
        this.clusterUrl = clusterUrl;
    }

    public String getClusterUrl() 
    {
        return clusterUrl;
    }

    public void setNamespace(String namespace)
    {
        this.namespace = namespace;
    }

    public String getNamespace() 
    {
        return namespace;
    }

    public void setAuthType(String authType)
    {
        this.authType = authType;
    }

    public String getAuthType() 
    {
        return authType;
    }

    public void setKubeconfigPath(String kubeconfigPath)
    {
        this.kubeconfigPath = kubeconfigPath;
    }

    public String getKubeconfigPath() 
    {
        return kubeconfigPath;
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
