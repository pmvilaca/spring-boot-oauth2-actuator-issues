package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableResourceServer
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // !!!!!!!!!!!!!!!!!!!!!!
    // This is what we need to do in order to get it working
    // !!!!!!!!!!!!!!!!!!!!!!
    //@Autowired
    //protected void configure(AuthenticationManagerBuilder builder, SecurityProperties security,
    //                         ManagementServerProperties management) throws Exception {
    //    List<String> authorities = new ArrayList<>(security.getUser().getRole());
    //    authorities.add(management.getSecurity().getRole());
    //    builder.inMemoryAuthentication().withUser(security.getUser().getName()).password(security.getUser().getPassword())
    //            .roles(authorities.toArray(new String[0]));
    //}
}
