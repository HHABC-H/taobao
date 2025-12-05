package org.taobao.pojo;

/**
 * 后端统一返回结果类
 */
public class Result<T> {
    private Integer code; // 编码：使用标准HTTP状态码，如200成功，400请求错误，401未授权，403禁止访问，404资源不存在，500服务器错误
    private String msg; // 错误信息
    private T data; // 数据

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.code = 200; // 成功状态码
        result.msg = "success";
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.data = object;
        result.code = 200; // 成功状态码
        result.msg = "success";
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 500; // 服务器错误状态码
        return result;
    }
    
    public static <T> Result<T> badRequest(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 400; // 请求错误状态码
        return result;
    }
    
    public static <T> Result<T> unauthorized(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 401; // 未授权状态码
        return result;
    }
    
    public static <T> Result<T> forbidden(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 403; // 禁止访问状态码
        return result;
    }
    
    public static <T> Result<T> notFound(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 404; // 资源不存在状态码
        return result;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}