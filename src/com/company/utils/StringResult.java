package com.company.utils;

public class StringResult {

    private String value = "-1";
    private  boolean error = true;

    public StringResult(String value) {
        this.value = value;
        this.error = false;

    }

    public StringResult() {}


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
