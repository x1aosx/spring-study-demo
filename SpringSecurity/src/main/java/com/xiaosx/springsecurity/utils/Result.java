package com.xiaosx.springsecurity.utils;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    @NonNull
    //业务码，比如成功、失败、权限不足等 code，可自行定义
    private int resultCode;

    @NonNull
    //返回信息，后端在进行业务处理后返回给前端一个提示信息，可自行定义
    private String message;

    //数据结果，泛型，可以是列表、单个对象、数字、布尔值等
    private Object data;

    /**
     * 无数据请求成功方法
     * @return Result(200, "请求成功")
     */
    public static Result SUCCESS() {
        return new Result(ResultStatus.SUCCESS.getResultCode(),ResultStatus.SUCCESS.getMessage());
    }

    /**
     * 有数据请求成功方法
     * @param data 获得数据
     * @return Result(200,"请求成功",data)
     */
    public static Result SUCCESS(Object data) {
        return new Result(ResultStatus.SUCCESS.getResultCode(),ResultStatus.SUCCESS.getMessage(),data);
    }

    /**
     * 自定义信息无数据请求成功方法
     * @param message
     * @return Result(200,message)
     */
    public static Result SUCCESS(String message) {
        return new Result(ResultStatus.SUCCESS.getResultCode(),message);
    }

    /**
     * 自定义信息有数据请求成功方法
     * @param message
     * @param data
     * @return Result(200,message,data)
     */
    public static Result SUCCESS(String message,Object data) {
        return new Result(ResultStatus.SUCCESS.getResultCode(),message,data);
    }

    /**
     * 修改数据请求错误方法
     * @return Result(400,"用户发出的请求有错误，服务器没有进行新建或修改数据的操作")
     */
    public static Result INVALID_REQUEST() {
        return new Result(ResultStatus.INVALID_REQUEST.getResultCode(),ResultStatus.INVALID_REQUEST.getMessage());
    }

    /**
     * 自定义信息修改数据请求错误方法
     * @param message
     * @return Result(400,message)
     */
    public static Result INVALID_REQUEST(String message) {
        return new Result(ResultStatus.INVALID_REQUEST.getResultCode(),message);
    }

    /**
     * 无权限方法
     * @return Result(401,"无权限")
     */
    public static Result UNAUTHORIZED() {
        return new Result(ResultStatus.UNAUTHORIZED.getResultCode(),ResultStatus.UNAUTHORIZED.getMessage());
    }

    /**
     * 自定义信息无权限方法
     * @param message
     * @return Result(401,message)
     */
    public static Result UNAUTHORIZED(String message) {
        return new Result(ResultStatus.UNAUTHORIZED.getResultCode(),message);
    }

    /**
     * 禁止访问方法
     * @return Result(403,"禁止访问")
     */
    public static Result FORBIDDEN() {
        return new Result(ResultStatus.FORBIDDEN.getResultCode(),ResultStatus.FORBIDDEN.getMessage());
    }

    /**
     * 自定义信息禁止访问方法
     * @param message
     * @return Result(403,message)
     */
    public static Result FORBIDDEN(String message) {
        return new Result(ResultStatus.FORBIDDEN.getResultCode(),message);
    }

    /**
     * 用户请求格式错误方法
     * @return Result(406, "用户请求格式错误")
     */
    public static Result NOT_ACCEPTABLE() {
        return new Result(ResultStatus.NOT_ACCEPTABLE.getResultCode(),ResultStatus.NOT_ACCEPTABLE.getMessage());
    }

    /**
     * 自定义信息用户请求格式错误方法
     * @param message
     * @return Result(406, "用户请求格式错误")
     */
    public static Result NOT_ACCEPTABLE(String message) {
        return new Result(ResultStatus.NOT_ACCEPTABLE.getResultCode(),message);
    }

    /**
     * 没有资源方法
     * @return Result(410, "无此资源")
     */
    public static Result GONE() {
        return new Result(ResultStatus.GONE.getResultCode(),ResultStatus.GONE.getMessage());
    }

    /**
     * 自定义信息没有资源方法
     * @param message
     * @return Result(410,message)
     */
    public static Result GONE(String message) {
        return new Result(ResultStatus.GONE.getResultCode(),message);
    }

    /**
     * 服务器错误方法
     * @return Result(500,"服务器错误")
     */
    public static Result SERVER_ERROR() {
        return new Result(ResultStatus.SERVER_ERROR.getResultCode(),ResultStatus.SERVER_ERROR.getMessage());
    }

    /**
     * 自定义信息服务器错误方法
     * @param message
     * @return Result(500,message)
     */
    public static Result SERVER_ERROR(String message) {
        return new Result(ResultStatus.SERVER_ERROR.getResultCode(),message);
    }

    /**
     * 自定义错误方法
     * @param resultCode
     * @param message
     * @return Result(resultCode,message)
     */
    public static Result ERROR_RESULT(Integer resultCode,String message) {
        return new Result(resultCode,message);
    }
}

