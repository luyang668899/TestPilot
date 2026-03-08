package com.luckyframe.project.testmanagmt.projectEnvironment.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.luckyframe.framework.web.domain.BaseEntity;
import com.luckyframe.project.system.project.domain.Project;

import java.util.Date;

/**
 * 环境配置表 project_environment
 * 
 * @author luckyframe
 * @date 2024-01-01
 */
public class ProjectEnvironment extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 环境ID */
	private Integer environmentId;
	/** 环境名称 */
	private String environmentName;
	/** 环境描述 */
	private String environmentDesc;
	/** 项目ID */
	private Integer projectId;
	/** 是否默认环境 */
	private String isDefault;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
	/** 备注 */
	private String remark;
	/** 关联项目实体 */
	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setEnvironmentId(Integer environmentId) 
	{
		this.environmentId = environmentId;
	}

	public Integer getEnvironmentId() 
	{
		return environmentId;
	}
	public String getEnvironmentName() {
		return environmentName;
	}

	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
	}
	public String getEnvironmentDesc() {
		return environmentDesc;
	}

	public void setEnvironmentDesc(String environmentDesc) {
		this.environmentDesc = environmentDesc;
	}
	public void setProjectId(Integer projectId) 
	{
		this.projectId = projectId;
	}

	public Integer getProjectId() 
	{
		return projectId;
	}
	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("environmentId", getEnvironmentId())
			.append("environmentName", getEnvironmentName())
			.append("environmentDesc", getEnvironmentDesc())
            .append("projectId", getProjectId())
            .append("isDefault", getIsDefault())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}