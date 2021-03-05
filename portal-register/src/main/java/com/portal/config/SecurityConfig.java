package com.portal.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //springClound2.0默认启用csrf,需关闭csrf,微服务才能注册到服务中心上
//        http.csrf().disable();
        //或者忽略/eurkea/**也可以解决
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
