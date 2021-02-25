package com.example.product.config;

import com.example.product.dto.UserDto;
import com.example.product.service.UserService;
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