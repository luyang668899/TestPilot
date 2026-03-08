package com.luckyframe.project.testmanagmt.cicd.domain;

import java.util.Date;

/**
 * GitLab CI/CD集成对象 project_cicd_gitlab
 * 
 * @author ruoyi
 */
public class CiCdGitLab
{
    private static final long serialVersionUID = 1L;

    /** GitLab ID */
    private Long gitlabId;

    /** 项目ID */
    private Long projectId;

    /** 项目名称 */
    private String projectName;

    /** GitLab名称 */
    private String gitlabName;

    /** GitLab URL */
    private String gitlabUrl;

    /** 项目路径 */
    private String projectPath;

    /** 访问令牌 */
    private String accessToken;

    /** 状态：0-禁用，1-启用 */
    private Integer status;

    /** 描述 */
    private String description;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    public Long getGitlabId() {
        return gitlabId;
    }

    public void setGitlabId(Long gitlabId) {
        this.gitlabId = gitlabId;
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

    public String getGitlabName() {
        return gitlabName;
    }

    public void setGitlabName(String gitlabName) {
        this.gitlabName = gitlabName;
    }

    public String getGitlabUrl() {
        return gitlabUrl;
    }

    public void setGitlabUrl(String gitlabUrl) {
        this.gitlabUrl = gitlabUrl;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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