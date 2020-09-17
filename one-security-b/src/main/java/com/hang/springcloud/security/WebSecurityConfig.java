package com.hang.springcloud.security;

import com.hang.springcloud.filter.VerifyFilter;
import com.hang.springcloud.security.authentication.CustomAuthenticationFailureHandler;
import com.hang.springcloud.security.authentication.CustomAuthenticationSuccessHandler;
import com.hang.springcloud.security.authentication.CustomExpiredSessionStrategy;
import com.hang.springcloud.security.authentication.CustomLogoutSuccessHandler;
import com.hang.springcloud.security.smsCodeAuthentication.SmsCodeAuthenticationSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 * @author jitwxs
 * @date 2018/3/29 16:57
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 如果token表不存在，使用下面语句可以初始化该表；若存在，会报错。
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    /**
     * 注入自定义PermissionEvaluator
     */
    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return handler;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 指定 provider
        auth.authenticationProvider(customAuthenticationProvider);
    }

/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }*/
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("进入WebSecurityConfig  网址验证");
        http.apply(smsCodeAuthenticationSecurityConfig).and().authorizeRequests()
                // 如果有允许匿名的url，填在下面
                .antMatchers("/getVerifyCode","/login/invalid","/sms/**").permitAll()
                .anyRequest().authenticated()
                .and()
                // 设置登陆页  登录路径设置为 /login
                .formLogin()
                    .loginPage("/login")//  没有权限时跳转的Url
                    .successHandler(customAuthenticationSuccessHandler)// 设置登陆成功页
                    .failureHandler(customAuthenticationFailureHandler)// 登录失败跳转到
    //                    .failureUrl("/login/error") // 登录失败跳转到 /login-error
    //                    .defaultSuccessUrl("/")// 设置登陆成功页
                    .permitAll()

                    // 指定authenticationDetailsSource
                    .authenticationDetailsSource(authenticationDetailsSource)
                    .and()
                    .addFilterBefore(new VerifyFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(customLogoutSuccessHandler)
                    .deleteCookies("JSESSIONID").and()
                .sessionManagement()
                    .invalidSessionUrl("/login/invalid")
                    .maximumSessions(1)//限制最大登陆数 原理就是限制单个用户能够存在的最大 session 数。
                    .maxSessionsPreventsLogin(false) // 当达到最大值时，是否保留已经登录的用户；为true，新用户无法登录；为 false，旧用户被踢出
                    .expiredSessionStrategy(new CustomExpiredSessionStrategy()) // 当达到最大值时，旧用户被踢出后处理方法
                    .sessionRegistry(sessionRegistry()).and().and()//踢出用户

                // 自动登录
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    // 有效时间：单位s
                    .tokenValiditySeconds(60)
                    .userDetailsService(userDetailsService);

        // 关闭CSRF跨域
        http.csrf().disable();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        log.info("进入WebSecurityConfig  网址验证");
//        http.authorizeRequests()
//                // 如果有允许匿名的url，填在下面
//                .antMatchers("/getVerifyCode").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                // 设置登陆页  登录路径设置为 /login
//                .formLogin().loginPage("/login")
//
//                // 登录失败跳转到 /login-error
//                .failureUrl("/login/error")
//                // 设置登陆成功页
//                .defaultSuccessUrl("/").permitAll()
//                // 自定义登陆用户名和密码参数，默认为username和password
////                .usernameParameter("username")
////                .passwordParameter("password")
//
//
//                // 指定authenticationDetailsSource
//                .authenticationDetailsSource(authenticationDetailsSource)
//                .and()
//                .addFilterBefore(new VerifyFilter(), UsernamePasswordAuthenticationFilter.class)
//                .logout().permitAll()
//                // 自动登录
//                .and().rememberMe()
//                .tokenRepository(persistentTokenRepository())
//                // 有效时间：单位s
//                .tokenValiditySeconds(60)
//                .userDetailsService(userDetailsService);
//
//        // 关闭CSRF跨域
//        http.csrf().disable();
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
//        web.ignoring().antMatchers("/css/**", "/js/**");
        web.ignoring().antMatchers("/assets/**");
    }
}