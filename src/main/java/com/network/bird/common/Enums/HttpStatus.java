package com.network.bird.common.Enums;

/**
 * HTTP状态码
 * Created by zhoupeng on 2017/1/4.
 */
public enum HttpStatus {
    /**
     * 100 Continue/继续。
     * <p>客户端应当继续发送请求。这个临时响应是用来通知客户端它的部分请求已经被服务器接收，
     * 且仍未被拒绝。客户端应当继续发送请求的剩余部分，或者如果请求已经完成，忽略这个响应。
     * 服务器必须在请求完成后向客户端发送一个最终响应。
     */
    CONTINUE(100);

    private final int value;

    private HttpStatus(int value) {
        this.value = value;
    }

    /**
     * 数字格式的状态码
     *
     * @return 状态码
     */
    public int value() {
        return this.value;
    }
}
