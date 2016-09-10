package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @author krullert
 */
@EnableWebSecurity
public class WebSecurityCustomConfiguration implements WebSecurityConfigurer<WebSecurity> {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void init(WebSecurity builder) throws Exception {
        // adapter method
    }

    @Override
    public void configure(WebSecurity builder) throws Exception {
        // adapter method
    }

    protected AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }
}
