package com.msql.dynamicdatasource.common;


import lombok.Data;

@Data
public class ResponseResult<T> {

    private Integer code=0000;
    private String message="成功";
    private Object data;


    public static <T> ResponseResult<T> build(T obj) {

        ResponseResult responseResult=new ResponseResult();

        responseResult.setData(obj);

        return responseResult;
    }

    public static <T> ResponseResult<T> Fail(Integer code, String msg) {
        return Fail(code, msg, new Object());
    }


    public static <T> ResponseResult<T> Fail(ResponseCode responseCode, Object data) {
        return Fail(responseCode.getCode(), responseCode.getMessage(), data);
    }

    public static <T> ResponseResult<T> Fail(Integer code, String msg, Object data) {
        ResponseResult<T> tResultFail = new ResponseResult<>();
        tResultFail.setCode(code);
        tResultFail.setMessage(msg);
        tResultFail.setData(data);
        return tResultFail;
    }
}
