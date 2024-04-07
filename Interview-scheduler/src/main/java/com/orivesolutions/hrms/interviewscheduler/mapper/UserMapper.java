package com.orivesolutions.hrms.interviewscheduler.mapper;

import com.orivesolutions.hrms.interviewscheduler.domain.User;
import com.orivesolutions.hrms.interviewscheduler.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDto toUserDto(User user);

    User toUser(UserDto user);

    List<User> toUsers(List<UserDto> userDtos);

    List<UserDto> toUserDtos(List<User> users);

}
