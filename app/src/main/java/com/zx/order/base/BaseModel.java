package com.zx.order.base;

/**
 * Created by HaiJun on 2018/6/11 17:17
 * 基类model
 */
public class BaseModel {
    private boolean success;
    private String message;
    private String fileUrl;
    private int code;

    public String getFileUrl() {
        return fileUrl == null ? "" : fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
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
}
