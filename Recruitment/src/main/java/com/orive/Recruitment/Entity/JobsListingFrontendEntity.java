package com.orive.Recruitment.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "jobslistingfrontend")
public class JobsListingFrontendEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobsListingFrontendId;
	
	@Column(name = "job_title")
	private String jobTitle;
	
	@Column(name = "department_name")
	private String departmentName;
	
	@Column(name = "reports_to_managers_name")
	private String reportsToManagersName;
	
	@Column(name = "job_location")
	private  String jobLocation;
	
	@Column(name = "job_description")
	private String jobDescription;
	
	@Column(name = "employment_type")
	private String employmentType;
	
	@Column(name = "work_schedule")
	private String workSchedule;
	
	@Column(name = "educational_requirements")
	private String educationalRequirements;
	
	@Column(name = "experience_level")
	private String experienceLevel;
	
	@Column(name = "skills_and_qualifications")
	private String skillsAndQualifications;
	
	@Column(name = "responsibilities")
	private String responsibilities;
	
	@Column(name = "salary_range")
	private double salaryRange;
	
	@Column(name = "benefits")
	private String benefits;
	
	@Column(name = "application_instructions")
	private String applicationInstructions;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "company_address")
	private String companyAddress;
	
	@Column(name = "company_Phone_number")
	private Long companyPhoneNumber;
	
	@Column(name = "company_email")
	private String companyEmail;
	
	@Column(name = "company_website")
	private String companyWebsite;
	
	@Column(name = "additional_information")
	private String additionalInformation;
	
	@Column(name = "approval")
	private String approval;
}
