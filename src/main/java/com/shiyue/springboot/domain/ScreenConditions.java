package com.shiyue.springboot.domain;

import java.io.Serializable;

public class ScreenConditions implements Serializable {
    private Integer id;

    private String ruleName;

    private String ruleCode;

    private String conditionsCode;

    private String conditionsName;

    private String operator;

    private String refValue;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode == null ? null : ruleCode.trim();
    }

    public String getConditionsCode() {
        return conditionsCode;
    }

    public void setConditionsCode(String conditionsCode) {
        this.conditionsCode = conditionsCode;
    }

    public String getConditionsName() {
        return conditionsName;
    }

    public void setConditionsName(String conditionsName) {
        this.conditionsName = conditionsName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRefValue() {
        return refValue;
    }

    public void setRefValue(String refValue) {
        this.refValue = refValue == null ? null : refValue.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ruleName=").append(ruleName);
        sb.append(", ruleCode=").append(ruleCode);
        sb.append(", conditionsCode=").append(conditionsCode);
        sb.append(", conditionsName=").append(conditionsName);
        sb.append(", operator=").append(operator);
        sb.append(", refValue=").append(refValue);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}