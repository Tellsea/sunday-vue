package cn.tellsea.sunday.common.exception;

import lombok.NoArgsConstructor;

/**
 * 系统异常
 *
 * @author: Tellsea
 * @date : 2020/3/4
 */
@NoArgsConstructor
public class BaseException extends Exception {

    public BaseException(String message) {
        super(message);
    }
}
