package com.jiacer.modules.mybatis.entity;

import java.io.Serializable;

public class StudentInfo  implements Serializable {

    private Integer id;
    private String sourceValue;
    private String sourceTypeSec;
    private String sourceValueText;
    private String sourceTypeSecText;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceValue() {
        return sourceValue;
    }

    public void setSourceValue(String sourceValue) {
        this.sourceValue = sourceValue;
    }

    public String getSourceTypeSec() {
        return sourceTypeSec;
    }

    public void setSourceTypeSec(String sourceTypeSec) {
        this.sourceTypeSec = sourceTypeSec;
    }

    public String getSourceValueText() {
        return sourceValueText;
    }

    public void setSourceValueText(String sourceValueText) {
        this.sourceValueText = sourceValueText;
    }

    public String getSourceTypeSecText() {
        return sourceTypeSecText;
    }

    public void setSourceTypeSecText(String sourceTypeSecText) {
        this.sourceTypeSecText = sourceTypeSecText;
    }
}
