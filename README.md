# Goal

The goal of this simple project is to demonstrate that by enabling (OAuth2) ResourceServer, the Actuator + Spring Boot
Security Configuration is broken.
Even after moving the `ResourceServerConfiguration` to a higher order, in order to just run it after the `ManagementWebSecurityAutoConfiguration`,
it isn't fully working.

### Things that I want to demonstrate:

* The password for the default user isn't printed at the startup
* If you define the security.user.password property, it isn't considered as well
* You're only able to perform requests without security (e.g. /health without passing the Authorization header)