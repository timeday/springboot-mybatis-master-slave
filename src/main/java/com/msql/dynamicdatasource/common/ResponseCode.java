package com.msql.dynamicdatasource.common;

import lombok.Data;


public enum ResponseCode {

    SUCCESS(200,"ok"),
    NOT_FOUND(404,"找不到服务"),
    INTERNAL_SERVER_ERROR(500,"服务错误");

    public int code;

    public String message;

    ResponseCode(int code,String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

}
