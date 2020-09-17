package com.hang.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


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


    @RequestMapping("/admin/all")
    @ResponseBody
    @PreAuthorize("hasPermission('/admin/all','r')")
    public String printAdminR() {
        return "如果你看见这句话，说明你访问/admin路径具有r权限";
    }

    @RequestMapping("/admin/c")
    @ResponseBody
    @PreAuthorize("hasPermission('/admin/c','c')")
    public String printAdminC() {
        return "如果你看见这句话，说明你访问/admin路径具有c权限";
    }


    @RequestMapping("/login/invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String invalid() {
        return "Session 已过期，请重新登录";
    }
}