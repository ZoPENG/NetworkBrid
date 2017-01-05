package com.network.bird.common.exception;

import com.network.bird.common.enums.ErrorCode;

/**
 * 当客户端提供的参数无效时抛出该异常，内部方法调用时的参数错误不应该抛出该异常
 * Created by zhoupeng on 2017/1/4.
 */
public class InvalidParameterException extends BaseException {

    private static final long serialVersionUID = 5029542931359357748L;

    public InvalidParameterException(String message) {
        super(ErrorCode.PARAMETER_ERROR, message);
    }
}
