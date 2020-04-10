package cn.tellsea.sunday.common.properties;

import lombok.Data;

/**
 * shiro 属性
 *
 * @author Tellsea
 * @date 2020/3/5
 */
@Data
public class ShiroProperties {

    /**
     * jwt token 时长，默认3600，单位秒
     */
    private int jwtTokenTimeOut;
    /**
     * AES密码加密私钥(Base64加密)
     */
     private String encryptAESKey;
    /**
     * JWT认证加密私钥(Base64加密)
     */
    private String encryptJWTKey;
    /**
     * AccessToken过期时间-5分钟-5*60(秒为单位)
     */
    private int accessTokenExpireTime;
    /**
     * RefreshToken过期时间-30分钟-30*60(秒为单位)
     */
    private int refreshTokenExpireTime;
    /**
     * Shiro缓存过期时间-5分钟-5*60(秒为单位)(一般设置与AccessToken过期时间一致)
     */
    private int shiroCacheExpireTime;
}
