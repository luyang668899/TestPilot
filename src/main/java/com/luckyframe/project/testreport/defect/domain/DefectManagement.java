package com.luckyframe.project.testreport.defect.domain;

import java.util.Date;

/**
 * 缺陷管理集成对象 project_defect_management
 * 
 * @author ruoyi
 */
public class DefectManagement
{
    private static final long serialVersionUID = 1L;

    /** 缺陷管理ID */
    private Long defectId;

    /** 项目ID */
    private Long projectId;

    /** 项目名称 */
    private String projectName;

    /** 缺陷系统类型：1-JIRA，2-Bugzilla，3-其他 */
    private Integer defectType;

    /** 缺陷系统名称 */
    private String defectSystemName;

    /** 缺陷系统URL */
    private String defectSystemUrl;

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

    public Long getDefectId() {
        return defectId;
    }

    public void setDefectId(Long defectId) {
        this.defectId = defectId;
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

    public Integer getDefectType() {
        return defectType;
    }

    public void setDefectType(Integer defectType) {
        this.defectType = defectType;
    }

    public String getDefectSystemName() {
        return defectSystemName;
    }

    public void setDefectSystemName(String defectSystemName) {
        this.defectSystemName = defectSystemName;
    }

    public String getDefectSystemUrl() {
        return defectSystemUrl;
    }

    public void setDefectSystemUrl(String defectSystemUrl) {
        this.defectSystemUrl = defectSystemUrl;
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