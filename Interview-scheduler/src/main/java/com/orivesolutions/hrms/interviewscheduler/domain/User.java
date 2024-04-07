package com.orivesolutions.hrms.interviewscheduler.domain;

import com.orivesolutions.hrms.interviewscheduler.config.AesEncryptor;
import com.orivesolutions.hrms.interviewscheduler.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;


@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
   // @Convert(converter = AesEncryptor.class)
    private String name;

    @Column(name = "address")
    //@Convert(converter = AesEncryptor.class)
    private String address;

    @Column(name = "email_id", unique = true, nullable = false)
   // @Convert(converter = AesEncryptor.class)
    private String emailId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    //@Convert(converter = AesEncryptor.class)
    private String password;

    @Column(name = "mobile")
    @Convert(converter = AesEncryptor.class)
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    //@Convert(converter = AesEncryptor.class)
    private Role role;

    @Column(name = "profile_url")
    //@Convert(converter = AesEncryptor.class)
    private String profileUrl;

    @Column(name = "forget_token")
    //@Convert(converter = AesEncryptor.class)
    private String forgetToken;

    @Column(name = "otp")
    //@Convert(converter = AesEncryptor.class)
    private Integer otp;

}
