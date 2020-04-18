package cn.tellsea.sunday.system.controller;

import cn.tellsea.sunday.common.authorization.JwtUtils;
import cn.tellsea.sunday.common.consts.JwtConstant;
import cn.tellsea.sunday.common.consts.RedisConstant;
import cn.tellsea.sunday.common.entity.ResponseResult;
import cn.tellsea.sunday.common.properties.BaseProperties;
import cn.tellsea.sunday.common.util.RedisUtils;
import cn.tellsea.sunday.system.entity.ResourceInfo;
import cn.tellsea.sunday.system.entity.RoleInfo;
import cn.tellsea.sunday.system.entity.UserInfo;
import cn.tellsea.sunday.system.service.ResourceInfoService;
import cn.tellsea.sunday.system.service.RoleInfoService;
import cn.tellsea.sunday.system.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 登录 控制器
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Slf4j
@Api(tags = "登录接口")
@Validated
@RestController
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RoleInfoService roleInfoService;
    @Autowired
    private ResourceInfoService resourceInfoService;
    @Autowired
    private RedisUtils redis;
    @Autowired
    private BaseProperties properties;

    @ApiOperation("登录")
    @PostMapping("login")
    public ResponseResult login(@NotNull(message = "用户名不能为空") @RequestParam("username") String username,
                                @NotNull(message = "密码不能为空") @RequestParam("password") String password,
                                HttpServletRequest request) {
        UserInfo userInfo = userInfoService.getByUserName(username);
        if (userInfo == null) {
            throw new AuthenticationException("用户不存在");
        }
        if (!StringUtils.equals(userInfo.getPassword(), password)) {
            // 需要改为md5 + salt验证密码
            throw new AuthenticationException("密码错误");
        }
        if (userInfo.getStatus() == 2) {
            throw new AuthenticationException("该用户已经被禁用");
        }
        // 清除可能存在的shiro权限信息缓存
        if (redis.hasKey(RedisConstant.PREFIX_SHIRO_CACHE + username)) {
            redis.del(RedisConstant.PREFIX_SHIRO_CACHE + username);
        }

        // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        redis.set(RedisConstant.PREFIX_SHIRO_REFRESH_TOKEN + username, currentTimeMillis,
                properties.getShiro().getRefreshTokenExpireTime());

        // 从Header中Authorization返回AccessToken，时间戳为当前时间戳
        String token = JwtUtils.sign(username, currentTimeMillis);
        return ResponseResult.success(token);
    }

    @ApiOperation("根据token获取用户信息")
    @GetMapping("getUserInfo")
    public ResponseResult getUserInfo(@RequestParam("token") String token) {
        // 能进这个方法，说明头部携带了令牌，且进过了认证操作，无需再验证
        String userName = JwtUtils.getClaim(token, JwtConstant.USER_NAME);
        // 用户信息
        UserInfo userInfo = userInfoService.getByUserName(userName);
        // 拥有的角色
        Set<String> roles = roleInfoService.getByUserName(userName).stream().map(RoleInfo::getName).collect(Collectors.toSet());
        userInfo.setRoles(roles);
        // 拥有的权限
        Set<String> permissions = resourceInfoService.getByUserName(userName).stream().map(ResourceInfo::getPerms).collect(Collectors.toSet());
        userInfo.setPermissions(permissions);
        // 生成菜单
        List<ResourceInfo> menus = resourceInfoService.getByUserName(userName);
        userInfo.setMenus(menus);
        return ResponseResult.success(userInfo);
    }

    @ApiOperation("退出登录")
    @PostMapping("logout")
    public ResponseResult logout() {
        // tokenService.logout();
        return ResponseResult.successMsg("退出成功");
    }

    @GetMapping("401")
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseResult unauthorized(HttpServletRequest request) {
        String msg = (String) request.getAttribute("msg");
        return ResponseResult.build(HttpStatus.UNAUTHORIZED.value(), msg);
    }

    /**
     * 获取当前登录用户
     */
    public UserInfo getCurrent() {
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject != null) {
                String token = (String) subject.getPrincipal();
                if (StringUtils.isNotBlank(token)) {
                    String account = JwtUtils.getClaim(token, JwtConstant.USER_NAME);
                    if (StringUtils.isNotBlank(account)) {
                        // return userService.getByMobile(account);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前登录用户ID
     */
    public int getCurrentId() {
        UserInfo current = getCurrent();
        if (current != null) {
            return current.getId();
        }
        return 0;
    }
}
