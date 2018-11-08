package com.zx.order.base;

/**
 * 基类model
 * 作者：JHJ
 * 日期：2018/10/30 14:43
 * Q Q: 1320666709
 */
public class BaseModel {
    private boolean success;
    private String message;
    private int code;
    private int totalNumber;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }
}