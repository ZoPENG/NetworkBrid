package com.network.bird.common.enums;

/**
 * 异常码
 * Created by zhoupeng on 2017/1/4.
 */
public enum ErrorCode {

    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * 请求参数错误
     */
    PARAMETER_ERROR(HttpStatus.BAD_REQUEST);

    private HttpStatus statusCode;

    /**
     *
     * @param statusCode http状态码
     */
    private ErrorCode(HttpStatus statusCode){
        this.statusCode = statusCode;
    }

    /**
     * 取得HTTP状态码
     * @return HTTP状态码
     * @since 1.0.0
     */
    public HttpStatus getStatusCode(){
        return statusCode;
    }
}
