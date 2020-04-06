package cn.tellsea.sunday.common.exception;

/**
 * redis 连接异常
 * @author Tellsea
 * @date 2020/4/4
 */
public class RedisConnectionException extends Exception {

    public RedisConnectionException() {
        super();
    }

    public RedisConnectionException(String message) {
        super(message);
    }
}
