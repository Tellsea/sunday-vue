package cn.tellsea.sunday.common.authorization;

import cn.tellsea.sunday.common.consts.JwtConstant;
import cn.tellsea.sunday.common.exception.ShiroCustomException;
import cn.tellsea.sunday.common.properties.BaseProperties;
import cn.tellsea.sunday.common.util.SpringUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * jwt 工具类
 *
 * @author Tellsea
 * @date 2020/4/10
 */
public class JwtUtils {

    /**
     * 校验token是否正确
     *
     * @param token
     * @return
     */
    public static boolean verify(String token) throws TokenExpiredException {
        // 帐号加JWT私钥解密
        BaseProperties properties = SpringUtils.getBean(BaseProperties.class);
        String secret = getClaim(token, JwtConstant.USER_NAME) + Base64Utils.decode(properties.getShiro().getEncryptJWTKey());
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return true;
    }

    /**
     * 获得Token中的信息无需secret解密也能获得
     *
     * @param token
     * @param claim
     * @return
     */
    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            throw new ShiroCustomException("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
        }
    }

    /**
     * 生成签名
     *
     * @param username
     * @param currentTimeMillis
     * @return
     */
    public static String sign(String username, String currentTimeMillis) {
        // 帐号加JWT私钥加密
        BaseProperties properties = SpringUtils.getBean(BaseProperties.class);
        String secret = username + Base64Utils.decode(properties.getShiro().getEncryptJWTKey());
        // 此处过期时间是以毫秒为单位，所以乘以1000
        Date date = new Date(System.currentTimeMillis() + properties.getShiro().getAccessTokenExpireTime() * 1000);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带account帐号信息
        return JWT.create().withClaim(JwtConstant.USER_NAME, username).withClaim(JwtConstant.CURRENT_TIME_MILLIS, currentTimeMillis)
                .withExpiresAt(date).sign(algorithm);
    }
}

