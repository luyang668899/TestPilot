package com.luckyframe.project.testmanagmt.cicd.domain;

import com.luckyframe.framework.aspectj.lang.annotation.Excel;
import com.luckyframe.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * Docker容器管理对象 ci_cd_docker
 * 
 * @author luckyframe
 * @date 2024-09-05
 */
public class CiCdDocker extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @Excel(name = "主键ID")
    private Integer dockerId;

    /** Docker名称 */
    @Excel(name = "Docker名称")
    private String dockerName;

    /** Docker地址 */
    @Excel(name = "Docker地址")
    private String dockerUrl;

    /** 端口 */
    @Excel(name = "端口")
    private Integer port;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

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

    public void setDockerId(Integer dockerId)
    {
        this.dockerId = dockerId;
    }

    public Integer getDockerId() 
    {
        return dockerId;
    }

    public void setDockerName(String dockerName)
    {
        this.dockerName = dockerName;
    }

    public String getDockerName() 
    {
        return dockerName;
    }

    public void setDockerUrl(String dockerUrl)
    {
        this.dockerUrl = dockerUrl;
    }

    public String getDockerUrl() 
    {
        return dockerUrl;
    }

    public void setPort(Integer port)
    {
        this.port = port;
    }

    public Integer getPort() 
    {
        return port;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
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
