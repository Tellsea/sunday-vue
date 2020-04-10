package cn.tellsea.sunday.common.authorization;

import cn.tellsea.sunday.common.consts.JwtConstant;
import cn.tellsea.sunday.common.consts.RedisConstant;
import cn.tellsea.sunday.common.properties.BaseProperties;
import cn.tellsea.sunday.common.util.RedisUtils;
import cn.tellsea.sunday.system.entity.ResourceInfo;
import cn.tellsea.sunday.system.entity.RoleInfo;
import cn.tellsea.sunday.system.entity.UserInfo;
import cn.tellsea.sunday.system.service.ResourceInfoService;
import cn.tellsea.sunday.system.service.RoleInfoService;
import cn.tellsea.sunday.system.service.UserInfoService;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义Realm
 * 
 * @author lwx
 * @date 2019/03/08
 */
@Service
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private RoleInfoService roleInfoService;
	@Autowired
	private ResourceInfoService resourceInfoService;
	@Autowired
	private BaseProperties properties;
	@Autowired
	private RedisUtils redis;


	/**
	 * 大坑，必须重写此方法，不然Shiro会报错
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		String userName = JwtUtils.getClaim(principals.toString(), JwtConstant.USER_NAME);
		// 设置角色集合
		Set<String> roleSet = roleInfoService.getByUserName(userName).stream().map(RoleInfo::getName).collect(Collectors.toSet());
		info.addRoles(roleSet);
		// 设置权限集合
		Set<String> permissionSet = resourceInfoService.getByUserName(userName).stream().map(ResourceInfo::getPerms).collect(Collectors.toSet());
		info.setStringPermissions(permissionSet);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
		String token = (String) auth.getCredentials();
		// 验证token是否有效、过期等，有问题则抛出异常，通过异常处理
		JwtUtils.verify(token);
		// 解密获得account，用于和数据库进行对比
		String userName = JwtUtils.getClaim(token, JwtConstant.USER_NAME);
		// 帐号为空
		if (StringUtils.isBlank(userName)) {
			throw new AuthenticationException("令牌无效，Token中帐号为空");
		}
		// 查询用户是否存在
		UserInfo userInfo = userInfoService.getByUserName(userName);
		if (userInfo == null) {
			throw new AuthenticationException("用户不存在");
		}
		if (userInfo.getStatus() == 2) {
			throw new AuthenticationException("用户已锁定，不可操作");
		}
		// 开始认证，要AccessToken认证通过，且Redis中存在RefreshToken，且两个Token时间戳一致
		if (JwtUtils.verify(token) && redis.hasKey(RedisConstant.PREFIX_SHIRO_REFRESH_TOKEN + userName)) {
			// 获取RefreshToken的时间戳
			String currentTimeMillisRedis = redis.get(RedisConstant.PREFIX_SHIRO_REFRESH_TOKEN + userName).toString();
			// 获取AccessToken时间戳，与RefreshToken的时间戳对比
			if (JwtUtils.getClaim(token, JwtConstant.CURRENT_TIME_MILLIS).equals(currentTimeMillisRedis)) {
				return new SimpleAuthenticationInfo(token, token, getName());
			}
		}
		throw new AuthenticationException("Token已过期(Token expired or incorrect.)");
	}
}

