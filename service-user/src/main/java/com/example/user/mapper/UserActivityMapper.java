package com.example.user.mapper;

import com.example.user.domain.UserActivity;
import com.example.user.dto.UserActivityDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
    imports = {LocalDateTime.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserActivityMapper {

  @Mapping(target = "trackedDate", expression = "java(LocalDateTime.now())")
  UserActivity userActivityDtoToUserActivity(UserActivityDto activityDto);
}
