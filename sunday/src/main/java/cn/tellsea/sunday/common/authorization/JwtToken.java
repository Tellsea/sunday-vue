package cn.tellsea.sunday.common.authorization;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * JwtToken
 *
 * @author Tellsea
 * @date 2020/4/10
 */
public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = 1900286977895826147L;

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

