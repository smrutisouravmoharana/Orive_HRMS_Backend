package com.orive.Employee.Dto;

import java.time.LocalDate;

import com.orive.Employee.Entity.ExperienceJoiningLetterEntity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ExperienceJoiningLetterDto {
	
	private Long experienceJoiningLetterId;
	private String referrenceNo;
	private LocalDate formReleaseDate;
	private String employeeName;
	private String username;
	private String employeeAddress;
	private String subject;
	private String employeeDesignation;
	private double annualCtc;
	private LocalDate joiningDate;
	private double salaryComponentPerMonth;
	private double salaryComponentPerBasic;
	private double specialAllowance;
	private double grossSalary;
	private Long probationPeriod;
	private String aaddharcardNo;
	private String pancardNo;
//	private byte[] marksheets;
	private String marksheets;
	private String reportingManager;
	private Long casualSickLeaves;
	private Long earnedLeaves;
	private Long noticePeriod;
	private String department;
}
