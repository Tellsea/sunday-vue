package cn.tellsea.sunday.common.exception;

/**
 * 系统异常
 *
 * @author: Tellsea
 * @date : 2020/3/4
 */
public class BaseException extends Exception {

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }
}
