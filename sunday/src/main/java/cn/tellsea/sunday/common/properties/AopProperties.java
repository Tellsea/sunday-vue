package cn.tellsea.sunday.common.properties;

import lombok.Data;

/**
 * aop 属性
 *
 * @author Tellsea
 * @date 2020/3/5
 */
@Data
public class AopProperties {

    /**
     * 是否打开系统日志，默认false
     */
    private boolean openLog = false;
}
