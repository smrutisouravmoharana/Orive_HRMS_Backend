package com.orivesolutions.hrms.interviewscheduler.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TalentDto {

    private long id;
    private String country;
    private String city;
    private String role;
    private String subRole;
    private String projectRole;
    private String businessArea;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String jobNo;
    private String designation;
    private String projectRoleDesc;
    private String mustHaveSkills;
    private String goodHaveSkills;
    private String qualification;
    private String description;
}
