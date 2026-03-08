package com.luckyframe.project.testreport.template.domain;

import com.luckyframe.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 测试报告模板表 report_template
 * 
 * @author luckyframe
 * @date 2026-03-08
 */
public class ReportTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 模板ID */
    private Long templateId;

    /** 模板名称 */
    private String templateName;

    /** 模板类型：1-系统模板 2-自定义模板 */
    private Integer templateType;

    /** 模板内容 */
    private String templateContent;

    /** 模板描述 */
    private String templateDesc;

    /** 状态：0-禁用 1-启用 */
    private Integer status;

    public void setTemplateId(Long templateId)
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }

    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }

    public String getTemplateName() 
    {
        return templateName;
    }

    public void setTemplateType(Integer templateType)
    {
        this.templateType = templateType;
    }

    public Integer getTemplateType() 
    {
        return templateType;
    }

    public void setTemplateContent(String templateContent)
    {
        this.templateContent = templateContent;
    }

    public String getTemplateContent() 
    {
        return templateContent;
    }

    public void setTemplateDesc(String templateDesc)
    {
        this.templateDesc = templateDesc;
    }

    public String getTemplateDesc() 
    {
        return templateDesc;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("templateId", getTemplateId())
            .append("templateName", getTemplateName())
            .append("templateType", getTemplateType())
            .append("templateContent", getTemplateContent())
            .append("templateDesc", getTemplateDesc())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}