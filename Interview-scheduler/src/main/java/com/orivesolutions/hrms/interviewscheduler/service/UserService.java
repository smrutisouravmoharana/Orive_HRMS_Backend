package com.orivesolutions.hrms.interviewscheduler.service;

import com.orivesolutions.hrms.interviewscheduler.domain.User;
import com.orivesolutions.hrms.interviewscheduler.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUser(Long id);

    User findUserByEmailId(String emailId);

    List<UserDto> getUsers();
}
