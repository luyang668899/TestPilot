package com.luckyframe.rc.entity;



/**
 * 元素行为
 */

public class ElementAction {
    /**
     * access
     * action
     * actionValue
     */
    private String access;
    private String action;
    private String actionValue;

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionValue() {
        return actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }

    @Override
    public String toString() {
        return "ElementAction{" +
                "access='" + access + '\'' +
                ", action='" + action + '\'' +
                ", actionValue='" + actionValue + '\'' +
                '}';
    }
}

