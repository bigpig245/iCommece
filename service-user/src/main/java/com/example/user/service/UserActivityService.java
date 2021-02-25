package com.example.user.service;

import com.example.user.domain.UserActivity;
import com.example.user.dto.UserActivityDto;
import com.example.user.mapper.UserActivityMapper;
import com.example.user.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserActivityService {
  @Autowired
  private UserActivityRepository userActivityRepository;
  @Autowired
  private UserActivityMapper userActivityMapper;

  @Transactional
  public void trackActivity(UserActivityDto userActivityDto) {
    UserActivity userActivity = userActivityMapper.userActivityDtoToUserActivity(userActivityDto);
    userActivityRepository.save(userActivity);
  }
}
