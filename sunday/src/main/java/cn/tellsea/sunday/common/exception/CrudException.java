package cn.tellsea.sunday.common.exception;

import lombok.NoArgsConstructor;

/**
 * 增删改查异常
 *
 * @author Tellsea
 * @date 2020/4/4
 */
@NoArgsConstructor
public class CrudException extends BaseException {

    public CrudException(String message) {
        super(message);
    }
}
