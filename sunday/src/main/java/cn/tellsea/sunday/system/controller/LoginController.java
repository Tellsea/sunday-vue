package cn.tellsea.sunday.system.controller;

import cn.tellsea.sunday.common.authentication.JwtUtils;
import cn.tellsea.sunday.common.entity.ResponseResult;
import cn.tellsea.sunday.common.properties.BaseProperties;
import cn.tellsea.sunday.common.service.TokenService;
import cn.tellsea.sunday.common.util.IpUtils;
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
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
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
    private RedisUtils redisUtils;
    @Autowired
    private BaseProperties properties;
    @Autowired
    private TokenService tokenService;

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
            throw new AuthenticationException("密码错误");
        }
        if (userInfo.getStatus() == 2) {
            throw new AuthenticationException("该用户已经被禁用");
        }
        String token = JwtUtils.sign(username, password);

        String ip = IpUtils.getClientIp(request);
        //redisUtil.set(FreestyleConst.TOKEN_PREFIX + token + StringPool.DOT + ip, userInfo, properties.getShiro().getJwtTokenTimeOut());
        // 返回前端所需数据
        // 拥有的角色
        userInfo.setRoles(roleInfoService.getByUserName(username).stream().map(RoleInfo::getName).collect(Collectors.toSet()));
        // 拥有的权限
        userInfo.setPermissions(resourceInfoService.getByUserName(username).stream().map(ResourceInfo::getPerms).collect(Collectors.toSet()));
        // 生成菜单
        userInfo.setMenus(resourceInfoService.getByUserName(username));
        redisUtils.set(token, userInfo, properties.getShiro().getJwtTokenTimeOut());
        return ResponseResult.success(token);
    }

    @ApiOperation("根据token获取用户信息")
    @GetMapping("getUserInfo")
    public ResponseResult getUserInfo(@RequestParam("token") String token) {
        return ResponseResult.success(tokenService.getActiveUser(token));
    }

    @ApiOperation("退出登录")
    @PostMapping("logout")
    public ResponseResult logout() {
        tokenService.logout();
        return ResponseResult.successMsg("退出成功");
    }

    @GetMapping("401")
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseResult unauthorized(HttpServletRequest request) {
        String msg = (String) request.getAttribute("msg");
        return ResponseResult.build(HttpStatus.UNAUTHORIZED.value(), msg);
    }
}
