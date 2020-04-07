package cn.tellsea.sunday.common.enums;

import lombok.Setter;

public class ExceptionEnums implements BaseEnums {


            ;

    @Setter
    private int code;

    @Setter
    private String info;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getInfo() {
        return info;
    }
}
