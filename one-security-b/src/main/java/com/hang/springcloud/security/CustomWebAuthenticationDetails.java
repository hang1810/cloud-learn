package com.hang.springcloud.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import javax.servlet.http.HttpServletRequest;
/**
 * @author Hang
 * @date 2020-08-02 20:59
 */
@Slf4j
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 6975601077710753878L;
    private final String verifyCode;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        // verifyCode为页面中验证码的name
        verifyCode = request.getParameter("verifyCode");
        log.info("获取验证码："+verifyCode);
    }

    public String getVerifyCode() {
        return this.verifyCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; VerifyCode: ").append(this.getVerifyCode());
        return sb.toString();
    }
}