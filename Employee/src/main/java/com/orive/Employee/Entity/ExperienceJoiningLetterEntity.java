package com.orive.Employee.Entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.TypedQuery;
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
@Entity
@Table(name = "experience_joining_letter")
public class ExperienceJoiningLetterEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long experienceJoiningLetterId;
	
	@Column(name = "referrence_no")
	private String referrenceNo;
	
	@Column(name = "form_release_date")
	private LocalDate formReleaseDate;
	
	@Column(name = "employee_name")
	private String employeeName;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "employee_address")
	private String employeeAddress;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "employee_designation")
	private String employeeDesignation;
	
	@Column(name = "annual_ctc")
	private double annualCtc;
	
	@Column(name = "joining_date")
	private LocalDate joiningDate;
	
	@Column(name = "salary_component_per_month")
	private double salaryComponentPerMonth;
	
	@Column(name = "salary_component_per_basic")
	private double salaryComponentPerBasic;
	
	@Column(name = "special_allowance")
	private double specialAllowance;
	
	@Column(name = "gross_salary")
	private double grossSalary;
	
	@Column(name = "probation_period")
	private Long probationPeriod;
	
	@Column(name = "aaddharcard_no")
	private String aaddharcardNo;
	
	@Column(name = "pancard_no")
	private String pancardNo;
	
	@Column(name = "marksheets")
	private String marksheets;
//	private byte[] marksheets;
	
	@Column(name = "reporting_manager")
	private String reportingManager;
	
	@Column(name = "casual_sick_leaves")
	private Long casualSickLeaves;
	
	@Column(name = "earned_leaves")
	private Long earnedLeaves;
	
	@Column(name = "notice_period")
	private Long noticePeriod;
	
	@Column(name = "department")
	private String department;
	
	 

//// Method for auto generated  reference number
//	private static int lastRandomPart = 0;
//
//	@PrePersist
//	public void generateReferenceNumber() {
//	    // Generate reference number logic
//	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-");
//	    String currentDate = dateFormat.format(new Date());
//	    String randomPart = String.format("%03d", getNextRandomPart()); // Adjust length as per requirement
//	    this.referrenceNo = "REF-" + currentDate + randomPart;
//	}
//
//	private synchronized int getNextRandomPart() {
//	    // Increment lastRandomPart and return it as a formatted string
//	    lastRandomPart = (lastRandomPart % 999) + 1; // Reset to 1 if exceeds 999
//	    return lastRandomPart;
//	}

}

