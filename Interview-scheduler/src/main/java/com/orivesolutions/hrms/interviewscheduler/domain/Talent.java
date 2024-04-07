package com.orivesolutions.hrms.interviewscheduler.domain;

import lombok.Data;


import java.time.LocalDate;

import com.orivesolutions.hrms.interviewscheduler.config.AesEncryptor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "talent")
@Data
public class Talent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "country")
    //@Convert(converter = AesEncryptor.class)
    private String country;
    
    @Column(name = "city")
    //@Convert(converter = AesEncryptor.class)
    private String city;
    
    @Column(name = "role")
   // @Convert(converter = AesEncryptor.class)
    private String role;
    
    @Column(name = "sub_role")   
    //@Convert(converter = AesEncryptor.class)
    private String subRole;
    
    @Column(name = "project_role")
    //@Convert(converter = AesEncryptor.class)
    private String projectRole;
    
    @Column(name = "business_area")
   // @Convert(converter = AesEncryptor.class)
    private String businessArea;
    
    @Column(name = "start_date")
    //@Convert(converter = AesEncryptor.class)
    private LocalDate startDate;
    
    @Column(name = "end_date")
    // @Convert(converter = AesEncryptor.class)
     private LocalDate endDate;
    
    @Column(name = "jobNo")
    //@Convert(converter = AesEncryptor.class)
    private String jobNo;
    
    @Column(name = "designation")
    //@Convert(converter = AesEncryptor.class)
    private String designation;
    
    @Column(name = "project_role_desc")
    //@Convert(converter = AesEncryptor.class)
    private String projectRoleDesc;

    @Column(name = "must_have_skills")
    //@Convert(converter = AesEncryptor.class)
    private String mustHaveSkills;
    
    @Column(name = "good_have_skills")
    //@Convert(converter = AesEncryptor.class)
    private String goodHaveSkills;
    
    @Column(name = "qualification")
    //@Convert(converter = AesEncryptor.class)
    private String qualification;
    
    @Column(name = "description", length = 3000)
    //@Convert(converter = AesEncryptor.class)
    private String description;
}
