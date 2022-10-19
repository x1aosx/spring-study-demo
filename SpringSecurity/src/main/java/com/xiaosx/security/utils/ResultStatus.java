package com.xiaosx.security.utils;

/**
 * @author 庞贤明
 * @date 2022-08-15
 * @description <p></p>
 **/

public enum ResultStatus {

    SUCCESS(200, "请求成功"),
    INVALID_REQUEST(400, "用户发出的请求有错误，服务器没有进行新建或修改数据的操作"),
    UNAUTHORIZED(401, "无权限"),
    FORBIDDEN(403, "禁止访问"),
    NOT_ACCEPTABLE(406, "用户请求格式错误"),
    GONE(410, "无此资源"),
    SERVER_ERROR(500, "服务器错误");

    private int status;

    private String message;

    ResultStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultStatus{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
