package cn.tellsea.sunday.system.service;

import cn.tellsea.sunday.common.entity.TableData;
import cn.tellsea.sunday.common.exception.CrudException;
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

    UserInfo getUserInfoById(int id);

    TableData listUserInfoByTable(UserInfo userInfo);

    int saveUserInfo(UserInfo userInfo) throws CrudException;

    int updateUserInfo(UserInfo userInfo) throws CrudException;

    int updateStatus(UserInfo userInfo) throws CrudException;

}
