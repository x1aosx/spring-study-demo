package com.xiaosx.security.utils;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Result implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NonNull
    //业务码，比如成功、失败、权限不足等 code，可自行定义
    private int status;

    @NonNull
    //返回信息，后端在进行业务处理后返回给前端一个提示信息，可自行定义
    private String message;

    //数据结果，泛型，可以是列表、单个对象、数字、布尔值等
    private Object data;

    @NonNull
    // 请求路径
    private String path;

    /**
     * 无数据请求成功方法
     *
     * @return Result(200, " 请求成功 ")
     */
    public static Result SUCCESS(String path) {
        return new Result(ResultStatus.SUCCESS.getStatus(), ResultStatus.SUCCESS.getMessage(),path);
    }

    /**
     * 有数据请求成功方法
     *
     * @param data 获得数据
     * @return Result(200, " 请求成功 ", data)
     */
    public static Result SUCCESS(Object data,String path) {
        return new Result(ResultStatus.SUCCESS.getStatus(), ResultStatus.SUCCESS.getMessage(), data,path);
    }

    /**
     * 自定义信息无数据请求成功方法
     *
     * @param message
     * @return Result(200, message)
     */
    public static Result SUCCESS(String message,String path) {
        return new Result(ResultStatus.SUCCESS.getStatus(), message,path);
    }

    /**
     * 自定义信息有数据请求成功方法
     *
     * @param message
     * @param data
     * @return Result(200, message, data)
     */
    public static Result SUCCESS(String message, Object data,String path) {
        return new Result(ResultStatus.SUCCESS.getStatus(), message, data,path);
    }

    /**
     * 修改数据请求错误方法
     *
     * @return Result(400, " 用户发出的请求有错误 ， 服务器没有进行新建或修改数据的操作 ")
     */
    public static Result INVALID_REQUEST(String path) {
        return new Result(ResultStatus.INVALID_REQUEST.getStatus(), ResultStatus.INVALID_REQUEST.getMessage(),path);
    }

    /**
     * 自定义信息修改数据请求错误方法
     *
     * @param message
     * @return Result(400, message)
     */
    public static Result INVALID_REQUEST(String message,String path) {
        return new Result(ResultStatus.INVALID_REQUEST.getStatus(), message,path);
    }

    /**
     * 无权限方法
     *
     * @return Result(401, " 无权限 ")
     */
    public static Result UNAUTHORIZED(String path) {
        return new Result(ResultStatus.UNAUTHORIZED.getStatus(), ResultStatus.UNAUTHORIZED.getMessage(),path);
    }

    /**
     * 自定义信息无权限方法
     *
     * @param message
     * @return Result(401, message)
     */
    public static Result UNAUTHORIZED(String message,String path) {
        return new Result(ResultStatus.UNAUTHORIZED.getStatus(), message,path);
    }

    /**
     * 禁止访问方法
     *
     * @return Result(403, " 禁止访问 ")
     */
    public static Result FORBIDDEN(String path) {
        return new Result(ResultStatus.FORBIDDEN.getStatus(), ResultStatus.FORBIDDEN.getMessage(),path);
    }

    /**
     * 自定义信息禁止访问方法
     *
     * @param message
     * @return Result(403, message)
     */
    public static Result FORBIDDEN(String message,String path) {
        return new Result(ResultStatus.FORBIDDEN.getStatus(),message,path);
    }

    /**
     * 用户请求格式错误方法
     *
     * @return Result(406, " 用户请求格式错误 ")
     */
    public static Result NOT_ACCEPTABLE(String path) {
        return new Result(ResultStatus.NOT_ACCEPTABLE.getStatus(), ResultStatus.NOT_ACCEPTABLE.getMessage(),path);
    }

    /**
     * 自定义信息用户请求格式错误方法
     *
     * @param message
     * @return Result(406, " 用户请求格式错误 ")
     */
    public static Result NOT_ACCEPTABLE(String message,String path) {
        return new Result(ResultStatus.NOT_ACCEPTABLE.getStatus(), message,path);
    }

    /**
     * 没有资源方法
     *
     * @return Result(410, " 无此资源 ")
     */
    public static Result GONE(String path) {
        return new Result(ResultStatus.GONE.getStatus(), ResultStatus.GONE.getMessage(),path);
    }

    /**
     * 自定义信息没有资源方法
     *
     * @param message
     * @return Result(410, message)
     */
    public static Result GONE(String message,String path) {
        return new Result(ResultStatus.GONE.getStatus(), message,path);
    }

    /**
     * 服务器错误方法
     *
     * @return Result(500, " 服务器错误 ")
     */
    public static Result SERVER_ERROR(String path) {
        return new Result(ResultStatus.SERVER_ERROR.getStatus(), ResultStatus.SERVER_ERROR.getMessage(),path);
    }

    /**
     * 自定义信息服务器错误方法
     *
     * @param message
     * @return Result(500, message)
     */
    public static Result SERVER_ERROR(String message,String path) {
        return new Result(ResultStatus.SERVER_ERROR.getStatus(), message,path);
    }

    /**
     * 自定义错误方法
     *
     * @param resultCode
     * @param message
     * @return Result(resultCode, message)
     */
    public static Result ERROR_RESULT(Integer resultCode, String message,String path) {
        return new Result(resultCode, message,path);
    }
}

