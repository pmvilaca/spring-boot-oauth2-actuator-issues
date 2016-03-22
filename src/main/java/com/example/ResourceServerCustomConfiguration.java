package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.annotation.PostConstruct;

// Just added this configuration class at the beginning of my tests in order to whitelist the /health endpoint.
@Configuration
public class ResourceServerCustomConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private ResourceServerConfiguration resourceServerConfiguration;

    // Workaround suggested by Dave Syer to get the ManagementWebSecurityAutoConfiguration running before
    // ResourceServerConfiguration to avoid breaking the Actuator configuration
    @PostConstruct
    public void overrideOrderOfResourceServerConfiguration() {
        resourceServerConfiguration.setOrder(ManagementServerProperties.BASIC_AUTH_ORDER + 10);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/health").permitAll()
                .anyRequest().authenticated();

    }
}
