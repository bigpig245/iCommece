package com.example.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan("com.example.order.config")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
      new AntPathRequestMatcher("/v*/carts**"),
      new AntPathRequestMatcher("/v*/orders**")
  );

  CustomerAuthenticationProvider provider;

  public SecurityConfiguration(final CustomerAuthenticationProvider authenticationProvider) {
    super();
    this.provider = authenticationProvider;
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(provider);
  }

  @Override
  public void configure(final WebSecurity webSecurity) {
    webSecurity.ignoring().antMatchers("/v*/products/**");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authenticationProvider(provider)
        .addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class)
        .anonymous()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/products**")
        .permitAll()
        .and()
        .csrf().disable()
        .formLogin().disable()
        .httpBasic().disable()
        .logout().disable();
  }

  @Bean
  CustomerAuthenticationFilter authenticationFilter() throws Exception {
    final CustomerAuthenticationFilter filter = new CustomerAuthenticationFilter(PROTECTED_URLS);
    filter.setAuthenticationManager(authenticationManager());
    return filter;
  }

  @Bean
  AuthenticationEntryPoint forbiddenEntryPoint() {
    return new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);
  }
}