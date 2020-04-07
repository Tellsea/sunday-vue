package cn.tellsea.sunday.common.service;

import cn.tellsea.sunday.system.entity.UserInfo;

/**
 * token service 接口
 * @author Tellsea
 * @date 2020/4/6
 */
public interface TokenService {

    UserInfo getActiveUser(String token);

    UserInfo getActiveUser();

    void logout();
}
