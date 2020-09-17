package com.hang.springcloud.security;

/**
 * @author Hang
 * @date 2020-08-02 21:03
 */

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 该接口用于在Spring Security登录过程中对用户的登录信息的详细信息进行填充
 * @author jitwxs
 * @since 2018/5/9 11:18
 */
@Component("authenticationDetailsSource")
class CustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    //自定义WebAuthenticationDetails 获取除账户密码意外的数据
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
        return new CustomWebAuthenticationDetails(request);
    }
}
