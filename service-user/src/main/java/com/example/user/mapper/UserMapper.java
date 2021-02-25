package com.example.user.mapper;

import com.example.user.domain.User;
import com.example.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.nio.CharBuffer;

@Mapper(componentModel = "spring",
    imports = {CharBuffer.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
  UserDto userToUserDto(User user);
}
