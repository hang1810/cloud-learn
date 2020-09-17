package com.hang.springcloud.controller;

import com.hang.springcloud.utils.ResultMap;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hang
 * @date 2020-08-01 17:08
 */

/*
  如代码所示，获取当前登录用户：SecurityContextHolder.getContext().getAuthentication()
  @PreAuthorize 用于判断用户是否有指定权限，没有就不能访问
*/
@Slf4j
@Controller
public class LoginController {


    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SessionRegistry sessionRegistry;

    @RequestMapping("/")
    public String showHome() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登陆用户：" + name);

        return "home.html";
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "login.html";
    }

//    @RequestMapping("/login-error")
//    public String loginError(Model model) {
//        log.info("登录失败");
//        model.addAttribute("error", true);
//        model.addAttribute("msg", "登录失败");
//        return "login";
//    }
    @RequestMapping("/login/error")
    public void loginError(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        AuthenticationException exception =
                (AuthenticationException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        try {
            response.getWriter().write(exception.toString()+" 账户密码错啦    ");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }


    @RequestMapping("/permission/admin/r")
    @ResponseBody
    @PreAuthorize("hasPermission('/admin/all','r')")
    public String printAdminR() {
        return "如果你看见这句话，说明你访问/admin路径具有r权限";
    }

    @RequestMapping("/permission/admin/c")
    @ResponseBody
    @PreAuthorize("hasPermission('/admin/c','c')")
    public String printAdminC() {
        return "如果你看见这句话，说明你访问/admin路径具有c权限";
    }



    @RequestMapping("/login/invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String invalid() {
        log.info("Session 已过期，请重新登录");
        return "Session 已过期，请重新登录";
    }

    /**
     * 踢出指定用户
     * todo: 还需要清理持久化表，不然无法踢出自动登陆用户，我就不做了
     */
    @PostMapping("/kick")
    @ResponseBody
    public ResultMap removeUserSessionByUsername(String username) {
        int count = 0;

        // 获取session中所有的用户信息
        List<Object> users = sessionRegistry.getAllPrincipals();
        for (Object principal : users) {
            if (principal instanceof User) {
                String principalName = ((User) principal).getUsername();
                if (principalName.equals(username)) {
                    /*
                     * 获取指定用户所有的 session 信息
                     * 参数二：是否包含过期的Session
                     */
                    List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
                    if (null != sessionsInfo && sessionsInfo.size() > 0) {
                        for (SessionInformation sessionInformation : sessionsInfo) {
                            sessionInformation.expireNow();
                            count++;
                        }
                    }
                }
            }
        }
        log.info(getClass() + ":removeUserSessionByUsername()", "操作成功，清理session共" + count + "个");
        return new ResultMap(getClass() + ":removeUserSessionByUsername()", "操作成功，清理session共" + count + "个");
    }
    @GetMapping("/me")
    @ResponseBody
    public Object me(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/meUserDetails")
    @ResponseBody
    public Object meUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }



    @RequestMapping("/sms/code")
    @ResponseBody
    public void sms(String mobile, HttpSession session) {
        int code = (int) Math.ceil(Math.random() * 9000 + 1000);

        Map<String, Object> map = new HashMap<>(16);
        map.put("mobile", mobile);
        map.put("code", code);

        session.setAttribute("smsCode", map);

        logger.info("{}：为 {} 设置短信验证码：{}", session.getId(), mobile, code);
    }
}