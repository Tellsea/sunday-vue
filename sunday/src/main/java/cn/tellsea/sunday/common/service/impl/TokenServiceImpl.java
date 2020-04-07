package cn.tellsea.sunday.common.service.impl;

import cn.tellsea.sunday.common.authentication.JwtUtils;
import cn.tellsea.sunday.common.service.TokenService;
import cn.tellsea.sunday.common.util.RedisUtils;
import cn.tellsea.sunday.system.entity.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
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
    public UserInfo getActiveUser(String token) {
        return verify(token);
    }

    @Override
    public UserInfo getActiveUser() {
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        return verify(token);
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        String token = (String) subject.getPrincipal();
        if (StringUtils.isNotEmpty(token)) {
            redisUtils.del(token);
        }
        subject.logout();
    }

    private UserInfo verify(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new AuthenticationException("令牌未找到");
        }
        String userName = JwtUtils.getUsername(token);
        if (StringUtils.isEmpty(userName)) {
            throw new AuthenticationException("令牌无效");
        }
        UserInfo userInfo = (UserInfo) redisUtils.get(token);
        if (userInfo == null) {
            throw new AuthenticationException("令牌已过期");
        }
        return userInfo;
    }
}
