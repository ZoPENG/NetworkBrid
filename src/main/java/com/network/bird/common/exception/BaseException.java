package com.network.bird.common.exception;

import com.network.bird.common.enums.ErrorCode;

/**
 * 所有异常 基类
 * Created by zhoupeng on 2016/12/28.
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -2533747858028636723L;
    private ErrorCode errorCode;

    /**
     *
     * @param message 错误信息
     */
    public BaseException(String message){
        super(message);
    }

    /**
     *
     * @param message 错误信息
     * @param e 原始异常
     */
    public BaseException(String message,Exception e){
        super(message, e);
    }

    /**
     *
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public BaseException(ErrorCode errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    /**
     *
     * @param errorCode 错误码
     * @param message 错误信息
     * @param e 原始异常
     */
    public BaseException(ErrorCode errorCode, String message,Exception e){
        super(message, e);
        this.errorCode = errorCode;
    }

    /**
     * 取得错误码
     * @return 错误码 , 如果没有设置错误码，则返回
     * {@link ErrorCode#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}
     */
    public ErrorCode getErrorCode(){
        return errorCode == null ? ErrorCode.INTERNAL_SERVER_ERROR : errorCode;
    }
}
