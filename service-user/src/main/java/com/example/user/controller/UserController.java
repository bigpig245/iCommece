package com.example.user.controller;

import com.example.user.dto.UserActivityDto;
import com.example.user.dto.UserDto;
import com.example.user.dto.UserLoginDto;
import com.example.user.service.UserActivityService;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {
  @Autowired
  private UserService userService;
  @Autowired
  private UserActivityService userActivityService;

  @PostMapping(value = "/login")
  public ResponseEntity<UserDto> signInEmail(@RequestBody UserLoginDto dto) {
    return ResponseEntity.ok(userService.getUserInfo(dto));
  }

  @PostMapping(value = "/activity")
  public ResponseEntity<Void> trackActivity(@RequestBody UserActivityDto activityDto) {
    userActivityService.trackActivity(activityDto);
    return ResponseEntity.noContent().build();
  }

}
