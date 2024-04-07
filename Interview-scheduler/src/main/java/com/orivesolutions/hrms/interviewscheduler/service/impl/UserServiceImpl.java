package com.orivesolutions.hrms.interviewscheduler.service.impl;

import com.orivesolutions.hrms.interviewscheduler.domain.User;
import com.orivesolutions.hrms.interviewscheduler.dto.UserDto;
import com.orivesolutions.hrms.interviewscheduler.exception.ResourceNotFoundException;
import com.orivesolutions.hrms.interviewscheduler.mapper.UserMapper;
import com.orivesolutions.hrms.interviewscheduler.repository.UserRepository;
import com.orivesolutions.hrms.interviewscheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        User userEntity = userRepository.save(user);
        userDto = userMapper.toUserDto(userEntity);
        return userDto;
    }

    @Override
    public UserDto getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new ResourceNotFoundException("Resource not Found", HttpStatus.NOT_FOUND));
        UserDto userDto = userMapper.toUserDto(user);
        return userDto;
    }

    @Override
    public User findUserByEmailId(String emailId) {
        User user = userRepository.findByEmailId(emailId).orElseThrow(() -> new ResourceNotFoundException("User not found ", HttpStatus.NOT_FOUND));
        return user;
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = userMapper.toUserDtos(users);
        return userDtos;
    }
}
