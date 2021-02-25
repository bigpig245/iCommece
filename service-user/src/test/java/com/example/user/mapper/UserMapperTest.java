package com.example.user.mapper;

import com.example.user.domain.User;
import com.example.user.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class UserMapperTest {
  private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
  private final PodamFactory PODAM = new PodamFactoryImpl();

  @Test
  public void shouldNotConvertUserToUserDto() {
    Assertions.assertThat(userMapper.userToUserDto(null)).isNull();
  }

  @Test
  public void shouldConvertUserToUserDto() {
    User user = PODAM.manufacturePojo(User.class);
    UserDto userDto = userMapper.userToUserDto(user);
    Assertions.assertThat(userDto).isEqualToComparingFieldByField(user);
  }
}
