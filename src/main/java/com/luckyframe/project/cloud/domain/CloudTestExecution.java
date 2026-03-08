package com.luckyframe.project.cloud.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luckyframe.framework.aspectj.lang.annotation.Excel;
import com.luckyframe.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 云测试执行对象 cloud_test_execution
 * 
 * @author ruoyi
 * @date 2023-09-15
 */
public class CloudTestExecution extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 执行ID */
    private Integer executionId;

    /** 服务ID */
    private Integer serviceId;

    /** 资源ID */
    private Integer resourceId;

    /** 测试任务ID */
    @Excel(name = "测试任务ID")
    private Integer testTaskId;

    /** 执行状态 */
    @Excel(name = "执行状态")
    private String status;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 执行结果 */
    @Excel(name = "执行结果")
    private String result;

    public void setExecutionId(Integer executionId)
    {
        this.executionId = executionId;
    }

    public Integer getExecutionId()
    {
        return executionId;
    }
    public void setServiceId(Integer serviceId)
    {
        this.serviceId = serviceId;
    }

    public Integer getServiceId()
    {
        return serviceId;
    }
    public void setResourceId(Integer resourceId)
    {
        this.resourceId = resourceId;
    }

    public Integer getResourceId()
    {
        return resourceId;
    }
    public void setTestTaskId(Integer testTaskId)
    {
        this.testTaskId = testTaskId;
    }

    public Integer getTestTaskId()
    {
        return testTaskId;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setResult(String result)
    {
        this.result = result;
    }

    public String getResult()
    {
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("executionId", getExecutionId())
            .append("serviceId", getServiceId())
            .append("resourceId", getResourceId())
            .append("testTaskId", getTestTaskId())
            .append("status", getStatus())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("result", getResult())
            .toString();
    }
}
