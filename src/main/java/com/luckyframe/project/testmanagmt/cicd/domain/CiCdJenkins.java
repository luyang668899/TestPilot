package com.luckyframe.project.testmanagmt.cicd.domain;

import java.util.Date;

/**
 * Jenkins集成对象 project_cicd_jenkins
 * 
 * @author ruoyi
 */
public class CiCdJenkins
{
    private static final long serialVersionUID = 1L;

    /** Jenkins ID */
    private Long jenkinsId;

    /** 项目ID */
    private Long projectId;

    /** 项目名称 */
    private String projectName;

    /** Jenkins名称 */
    private String jenkinsName;

    /** Jenkins URL */
    private String jenkinsUrl;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** API令牌 */
    private String apiToken;

    /** 状态：0-禁用，1-启用 */
    private Integer status;

    /** 描述 */
    private String description;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    public Long getJenkinsId() {
        return jenkinsId;
    }

    public void setJenkinsId(Long jenkinsId) {
        this.jenkinsId = jenkinsId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getJenkinsName() {
        return jenkinsName;
    }

    public void setJenkinsName(String jenkinsName) {
        this.jenkinsName = jenkinsName;
    }

    public String getJenkinsUrl() {
        return jenkinsUrl;
    }

    public void setJenkinsUrl(String jenkinsUrl) {
        this.jenkinsUrl = jenkinsUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}