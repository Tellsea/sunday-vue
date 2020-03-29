package cn.tellsea.sunday.system.entity;

import cn.tellsea.sunday.common.entity.BaseEntity;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *  实体类
 *
 * @author Tellsea
 * @date 2020-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("login_log")
public class LoginLog extends BaseEntity {

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * IP地址
     */
    @TableField("ip")
    private String ip;

    /**
     * 登录地点
     */
    @TableField("location")
    private String location;

    /**
     * 登录时间
     */
    @TableField("create_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 登录设备
     */
    @TableField("device")
    private String device;

    /**
     * 状态码
     */
    @TableField("code")
    private Integer code;

    /**
     * 消息
     */
    @TableField("message")
    private String message;

    @TableField(exist = false)
    private String userName;
}
