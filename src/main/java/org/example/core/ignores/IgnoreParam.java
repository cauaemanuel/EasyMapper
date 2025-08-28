package org.example.core.ignores;

public class IgnoreParam {

    private String fieldName;

    private IgnoreParam() {
    }

    public IgnoreParam(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

}
