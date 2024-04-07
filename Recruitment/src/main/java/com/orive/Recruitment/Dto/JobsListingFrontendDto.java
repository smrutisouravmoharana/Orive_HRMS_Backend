package com.orive.Recruitment.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JobsListingFrontendDto {
	
    private Long jobsListingFrontendId;
	private String jobTitle;
	private String departmentName;
	private String reportsToManagersName;
	private  String jobLocation;
	private String jobDescription;
	private String employmentType;
	private String workSchedule;
	private String educationalRequirements;
	private String experienceLevel;
	private String skillsAndQualifications;
	private String responsibilities;
	private double salaryRange;
	private String benefits;
	private String applicationInstructions;
	private String companyName;
	private String companyAddress;
	private Long companyPhoneNumber;
	private String companyEmail;
	private String companyWebsite;
	private String additionalInformation;
	private String approval;
}
