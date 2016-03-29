package com.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityPrerequisite;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableResourceServer
public class DemoApplication {

  @Component
  protected static class ResourceServerConfigurationUpdater implements SecurityPrerequisite {
    @Autowired
    private ResourceServerConfiguration resourceServerConfiguration;

    @PostConstruct
    public void overrideOrderOfResourceServerConfiguration() {
      resourceServerConfiguration.setOrder(ManagementServerProperties.BASIC_AUTH_ORDER + 1);
    }

  }

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

}
