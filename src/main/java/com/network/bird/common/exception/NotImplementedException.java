package com.network.bird.common.exception;

import com.network.bird.common.enums.ErrorCode;

/**
 * Created by zhoupeng on 2017/1/6.
 */
public class NotImplementedException extends BaseException {

    private static final long serialVersionUID = 2602769390098210844L;
    private String message;

    public NotImplementedException() {
        super(ErrorCode.INTERNAL_SERVER_ERROR, "方法未实现");
        StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
        message = "" + stackTraces[2].getClassName() + "#" + stackTraces[2].getMethodName();
    }

    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public String toString(){
        return message;
    }
}
