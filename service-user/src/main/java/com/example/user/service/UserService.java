package com.example.user.service;

import com.example.user.domain.User;
import com.example.user.dto.UserDto;
import com.example.user.dto.UserLoginDto;
import com.example.user.mapper.UserMapper;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserMapper userMapper;

  public UserDto getUserInfo(UserLoginDto loginDto) {
    User user = userRepository.findByAccessToken(loginDto.getAccessToken());
    return userMapper.userToUserDto(user);
  }
}
