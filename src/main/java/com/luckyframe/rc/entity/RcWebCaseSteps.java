package com.luckyframe.rc.entity;


/**
 * WEB自动化测试用例步骤
 * @author lifengyang
 */
public class RcWebCaseSteps {
    /** 包路径|定位路径 */
    private String stepPath;
    /** 方法名|操作 */
    private String stepOperation;
    /** 参数 */
    private String stepParameters;
    /** 步骤动作 */
    private String action;
    /** 预期结果 */
    private String expectedResult;
    /** 默认类型 0 HTTP接口 1 Web UI 2 API驱动  3移动端 */
    private Integer stepType;
    /** 扩展字段，可用于备注、存储HTTP模板等 */
    private String extend;
    /** 备注字段，用于接口类型的步骤的备注 */
    private String stepRemark;

    public String getStepPath() {
        return stepPath;
    }

    public void setStepPath(String stepPath) {
        this.stepPath = stepPath;
    }

    public String getStepOperation() {
        return stepOperation;
    }

    public void setStepOperation(String stepOperation) {
        this.stepOperation = stepOperation;
    }

    public String getStepParameters() {
        return stepParameters;
    }

    public void setStepParameters(String stepParameters) {
        this.stepParameters = stepParameters;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public Integer getStepType() {
        return stepType;
    }

    public void setStepType(Integer stepType) {
        this.stepType = stepType;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getStepRemark() {
        return stepRemark;
    }

    public void setStepRemark(String stepRemark) {
        this.stepRemark = stepRemark;
    }
}
