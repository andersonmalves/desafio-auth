package com.devsuperior.bds04.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Autowired
  private Environment environment;

  @Autowired
  private JwtTokenStore jwtTokenStore;

  private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**", "/ping" };
  private static final String[] EVENTS = { "/events/**" };
  private static final String[] CITIES = {"/cities/**"};

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.tokenStore(jwtTokenStore);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {

    // H2
    if(Arrays.asList(environment.getActiveProfiles()).contains("test")){
      http.headers()
          .frameOptions()
          .disable();
    }

    http.authorizeRequests()
        .antMatchers(PUBLIC).permitAll()
        .antMatchers(HttpMethod.GET).permitAll()
        .antMatchers(EVENTS).hasAnyRole("CLIENT","ADMIN")
        .antMatchers(HttpMethod.POST, CITIES).hasRole("ADMIN");
  }

}
