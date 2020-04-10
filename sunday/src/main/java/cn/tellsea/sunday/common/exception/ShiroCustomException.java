package cn.tellsea.sunday.common.exception;

/**
 * shiro 自定义异常
 * redis 连接异常
 *
 * @author Tellsea
 * @date 2020/4/4
 */
public class ShiroCustomException extends BaseException {

    public ShiroCustomException() {
        super();
    }

    public ShiroCustomException(String message) {
        super(message);
    }
}
