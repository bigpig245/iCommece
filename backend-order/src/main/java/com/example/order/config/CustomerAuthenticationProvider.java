package com.example.order.config;

import com.example.order.dto.UserDto;
import com.example.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomerAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private UserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    UserDto userDto = userService.getUserInfo(authentication.getPrincipal().toString());
    if (userDto == null) {
      throw new AuthenticationCredentialsNotFoundException("Not Authorized");
    }

    return new UsernamePasswordAuthenticationToken(userDto, null, Collections.emptyList());
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return true;
  }
}