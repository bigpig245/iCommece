package com.example.user.mapper;

import com.example.user.dto.UserActivityDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class UserActivityMapperTest {
  private UserActivityMapper userActivityMapper = Mappers.getMapper(UserActivityMapper.class);
  private final PodamFactory PODAM = new PodamFactoryImpl();

  @Test
  public void shouldNotConvertUserActivityDtoToUserActivity() {
    Assertions.assertThat(userActivityMapper.userActivityDtoToUserActivity(null)).isNull();
  }

  @Test
  public void shouldConvertUserActivityDtoToUserActivity() {
    UserActivityDto userActivityDto = PODAM.manufacturePojo(UserActivityDto.class);
    Assertions.assertThat(userActivityMapper.userActivityDtoToUserActivity(userActivityDto))
    .isEqualToIgnoringGivenFields(userActivityDto, "trackedDate", "user");
    Assertions.assertThat(userActivityDto.getTrackedDate()).isNotNull();
  }
}
