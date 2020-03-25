package cn.tellsea.sunday.system.service;

import cn.tellsea.sunday.common.entity.ResponseResult;
import cn.tellsea.sunday.system.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户表 Service接口
 *
 * @author Tellsea
 * @date 2020-03-04
 */
public interface UserInfoService extends IService<UserInfo> {

    UserInfo getByUserName(String userName);

    ResponseResult listByTable(UserInfo userInfo);

    void saveUserInfo(UserInfo userInfo);

    void updateUserInfo(UserInfo userInfo);

    void updateStatus(UserInfo userInfo);
}
