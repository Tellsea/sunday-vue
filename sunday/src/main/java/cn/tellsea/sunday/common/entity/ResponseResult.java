package cn.tellsea.sunday.common.entity;

import cn.tellsea.sunday.common.enums.BaseEnums;
import cn.tellsea.sunday.common.enums.CrudEnums;
import cn.tellsea.sunday.common.enums.StatusEnums;
import cn.tellsea.sunday.common.exception.CrudException;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 公共返回结果集
 *
 * @author Tellsea
 * @date 2020/3/3
 */
@Slf4j
@Data
@Accessors(chain = true)
public class ResponseResult implements Serializable {

    private int code;

    private String message;

    private int count;

    private Object data;

    public static ResponseResult success() {
        return new ResponseResult().setCode(StatusEnums.OK.getCode())
                .setMessage(StatusEnums.OK.getInfo());
    }

    public static ResponseResult success(Object data) {
        return new ResponseResult().setCode(StatusEnums.OK.getCode())
                .setMessage(StatusEnums.OK.getInfo())
                .setData(data);
    }

    public static ResponseResult successMsg(String message) {
        return new ResponseResult().setCode(StatusEnums.OK.getCode())
                .setMessage(message);
    }

    public static ResponseResult success(BaseEnums baseEnums) {
        return new ResponseResult().setCode(baseEnums.getCode())
                .setMessage(baseEnums.getInfo());
    }

    public static ResponseResult success(BaseEnums baseEnums, Object data) {
        return new ResponseResult().setCode(baseEnums.getCode())
                .setMessage(baseEnums.getInfo())
                .setData(data);
    }

    // 失败

    public static ResponseResult error() {
        return new ResponseResult().setCode(StatusEnums.SERVER_ERROR.getCode())
                .setMessage(StatusEnums.SERVER_ERROR.getInfo());
    }

    public static ResponseResult error(Object data) {
        return new ResponseResult().setCode(StatusEnums.SERVER_ERROR.getCode())
                .setMessage(StatusEnums.SERVER_ERROR.getInfo())
                .setData(data);
    }

    public static ResponseResult errorMsg(String message) {
        return new ResponseResult().setCode(StatusEnums.SERVER_ERROR.getCode())
                .setMessage(message);
    }

    public static ResponseResult error(BaseEnums baseEnums) {
        return new ResponseResult().setCode(baseEnums.getCode())
                .setMessage(baseEnums.getInfo());
    }

    public static ResponseResult error(BaseEnums baseEnums, Object data) {
        return new ResponseResult().setCode(baseEnums.getCode())
                .setMessage(baseEnums.getInfo())
                .setData(data);
    }

    public static ResponseResult table(TableData tableData) {
        return new ResponseResult().setCode(StatusEnums.SELECT_SUCCESS.getCode())
                .setMessage(StatusEnums.SELECT_SUCCESS.getInfo())
                .setCount(tableData.getCount())
                .setData(tableData.getData());
    }

    public static ResponseResult verify(CrudEnums crudEnums, int count) throws CrudException {
        if (count > 0) {
            log.info("影响数据库行数：" + count);
            if (crudEnums.getCode() == CrudEnums.SAVE.getCode()) {
                return success(StatusEnums.SAVE_SUCCESS);
            } else if (crudEnums.getCode() == CrudEnums.UPDATE.getCode()) {
                return success(StatusEnums.UPDATE_SUCCESS);
            } else if (crudEnums.getCode() == CrudEnums.DELETE.getCode()) {
                return success(StatusEnums.DELETE_SUCCESS);
            }
        }
        if (crudEnums.getCode() == CrudEnums.SAVE.getCode()) {
            throw new CrudException(StatusEnums.SAVE_ERROR.getInfo());
        } else if (crudEnums.getCode() == CrudEnums.UPDATE.getCode()) {
            throw new CrudException(StatusEnums.UPDATE_ERROR.getInfo());
        } else if (crudEnums.getCode() == CrudEnums.DELETE.getCode()) {
            throw new CrudException(StatusEnums.DELETE_ERROR.getInfo());
        }
        return ResponseResult.error();
    }
}
