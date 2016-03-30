# Goal

The goal of this simple project is to demonstrate that by enabling (OAuth2) ResourceServer, the Actuator + Spring Boot
Security Configuration is broken.
Even after moving the `ResourceServerConfiguration` to a higher order, in order to just run it after the `ManagementWebSecurityAutoConfiguration`,
it isn't fully working.

### Things that I want to demonstrate:

* The password for the default user isn't printed at the startup (if you comment the custom password configuration)
* You're only able to perform requests without security (e.g. /health without passing the Authorization header)
* When you try to perform an authentication request, you get an error saying that there is no AuthenticationProvider

```
curl -u user:secret 'http://localhost:8080/admin/health'
```

Stacktrace:

```
2016-03-30 16:20:06.390 DEBUG 42870 --- [nio-8080-exec-1] o.s.s.w.u.matcher.AntPathRequestMatcher  : Checking match of request : '/admin/health'; against '/admin/health'
2016-03-30 16:20:06.390 DEBUG 42870 --- [nio-8080-exec-1] o.s.s.web.util.matcher.OrRequestMatcher  : matched
2016-03-30 16:20:06.390 DEBUG 42870 --- [nio-8080-exec-1] o.s.s.w.a.i.FilterSecurityInterceptor    : Secure object: FilterInvocation: URL: /admin/health; Attributes: [permitAll]
2016-03-30 16:20:06.390 DEBUG 42870 --- [nio-8080-exec-1] o.s.s.w.a.i.FilterSecurityInterceptor    : Previously Authenticated: org.springframework.security.authentication.UsernamePasswordAuthenticationToken@fb776cb9: Principal: org.springframework.security.core.userdetails.User@36ebcb: Username: user; Password: [PROTECTED]; Enabled: true; AccountNonExpired: true; credentialsNonExpired: true; AccountNonLocked: true; Granted Authorities: ROLE_ADMIN,ROLE_USER; Credentials: [PROTECTED]; Authenticated: true; Details: org.springframework.security.web.authentication.WebAuthenticationDetails@b364: RemoteIpAddress: 0:0:0:0:0:0:0:1; SessionId: null; Granted Authorities: ROLE_ADMIN, ROLE_USER
2016-03-30 16:20:06.400 DEBUG 42870 --- [nio-8080-exec-1] o.s.s.access.vote.AffirmativeBased       : Voter: org.springframework.security.web.access.expression.WebExpressionVoter@45aa1687, returned: 1
2016-03-30 16:20:06.401 DEBUG 42870 --- [nio-8080-exec-1] o.s.s.w.a.i.FilterSecurityInterceptor    : Authorization successful
2016-03-30 16:20:06.401 DEBUG 42870 --- [nio-8080-exec-1] o.s.s.w.a.i.FilterSecurityInterceptor    : RunAsManager did not change Authentication object
2016-03-30 16:20:06.401 DEBUG 42870 --- [nio-8080-exec-1] o.s.security.web.FilterChainProxy        : /admin/health reached end of additional filter chain; proceeding with original chain
2016-03-30 16:20:06.646 DEBUG 42870 --- [nio-8080-exec-1] o.s.s.w.a.ExceptionTranslationFilter     : Chain processed normally
2016-03-30 16:20:06.646 DEBUG 42870 --- [nio-8080-exec-1] s.s.w.c.SecurityContextPersistenceFilter : SecurityContextHolder now cleared, as request processing completed
```

