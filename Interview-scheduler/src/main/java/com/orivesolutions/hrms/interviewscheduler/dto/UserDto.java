package com.orivesolutions.hrms.interviewscheduler.dto;

import com.orivesolutions.hrms.interviewscheduler.enums.Role;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String address;

    private String emailId;

    private String password;

    private String mobile;

    private Role role;

    private String profileUrl;

    private String forgetToken;

    private Integer otp;

}
