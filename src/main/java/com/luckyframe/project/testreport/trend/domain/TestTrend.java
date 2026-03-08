package com.luckyframe.project.testreport.trend.domain;

import com.luckyframe.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 测试趋势分析表 test_trend
 * 
 * @author luckyframe
 * @date 2026-03-08
 */
public class TestTrend extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 趋势ID */
    private Long trendId;

    /** 项目ID */
    private Long projectId;

    /** 测试类型：1-接口测试 2-UI测试 3-性能测试 4-安全测试 */
    private Integer testType;

    /** 执行日期 */
    private Date executeDate;

    /** 总用例数 */
    private Integer caseTotalCount;

    /** 成功数 */
    private Integer caseSuccCount;

    /** 失败数 */
    private Integer caseFailCount;

    /** 成功率 */
    private Double successRate;

    /** 执行时间（秒） */
    private Double executeTime;

    public void setTrendId(Long trendId)
    {
        this.trendId = trendId;
    }

    public Long getTrendId() 
    {
        return trendId;
    }

    public void setProjectId(Long projectId)
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }

    public void setTestType(Integer testType)
    {
        this.testType = testType;
    }

    public Integer getTestType() 
    {
        return testType;
    }

    public void setExecuteDate(Date executeDate)
    {
        this.executeDate = executeDate;
    }

    public Date getExecuteDate() 
    {
        return executeDate;
    }

    public void setCaseTotalCount(Integer caseTotalCount)
    {
        this.caseTotalCount = caseTotalCount;
    }

    public Integer getCaseTotalCount() 
    {
        return caseTotalCount;
    }

    public void setCaseSuccCount(Integer caseSuccCount)
    {
        this.caseSuccCount = caseSuccCount;
    }

    public Integer getCaseSuccCount() 
    {
        return caseSuccCount;
    }

    public void setCaseFailCount(Integer caseFailCount)
    {
        this.caseFailCount = caseFailCount;
    }

    public Integer getCaseFailCount() 
    {
        return caseFailCount;
    }

    public void setSuccessRate(Double successRate)
    {
        this.successRate = successRate;
    }

    public Double getSuccessRate() 
    {
        return successRate;
    }

    public void setExecuteTime(Double executeTime)
    {
        this.executeTime = executeTime;
    }

    public Double getExecuteTime() 
    {
        return executeTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("trendId", getTrendId())
            .append("projectId", getProjectId())
            .append("testType", getTestType())
            .append("executeDate", getExecuteDate())
            .append("caseTotalCount", getCaseTotalCount())
            .append("caseSuccCount", getCaseSuccCount())
            .append("caseFailCount", getCaseFailCount())
            .append("successRate", getSuccessRate())
            .append("executeTime", getExecuteTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}