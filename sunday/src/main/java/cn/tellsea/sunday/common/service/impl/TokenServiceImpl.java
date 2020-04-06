package cn.tellsea.sunday.common.service.impl;

import cn.tellsea.sunday.common.authentication.JwtUtils;
import cn.tellsea.sunday.common.service.TokenService;
import cn.tellsea.sunday.common.util.RedisUtils;
import cn.tellsea.sunday.system.entity.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tellsea
 * @date 2020/4/6
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public UserInfo getUserInfo(String token) {
        if (StringUtils.isNotEmpty(token)) {
            throw new AuthenticationException("令牌未找到");
        }
        String userName = JwtUtils.getUsername(token);
        if (StringUtils.isNotEmpty(userName)) {
            throw new AuthenticationException("令牌无效");
        }
        UserInfo userInfo = (UserInfo) redisUtils.get(token);
        if (userInfo == null) {
            throw new AuthenticationException("令牌已过期");
        }
        return userInfo;
    }
}
