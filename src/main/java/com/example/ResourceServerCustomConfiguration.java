package com.example;

import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

@Order(ManagementServerProperties.BASIC_AUTH_ORDER + 1)
@Configuration
@EnableResourceServer
public class ResourceServerCustomConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        // With this configuration, the goal is to protect all the resources with oauth, except the actuator endpoints
        http
                .requestMatcher(
                        new NegatedRequestMatcher(new AntPathRequestMatcher("/admin/**")))
                .authorizeRequests().anyRequest().authenticated();
    }
}
