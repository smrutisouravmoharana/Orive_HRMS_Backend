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
@Table(name = "jobcandidates")
public class JobCandidatesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobCandidatesId;
	
	@Column(name = "candidates_full_name")
	private String candidatesFullName;
	
	@Column(name = "candidates_phone_number")
	private String candidatesPhoneNumber;
	
	@Column(name = "candidates_email_address")
	private String candidatesEmailAddress;
	
	@Column(name = "candidates_address")
	private String candidatesAddress;
	
	@Column(name = "applying_for_job_position")
	private String applyingForJobPosition;
	
	@Column(name = "reference_number")
	private String referenceNumber;
	
	@Column(name = "most_recent_employer")
	private String mostRecentEmployer;
	
	@Column(name = "job_title")
	private String jobTitle;
	
	@Column(name = "employment_start_date")
	private String employmentStartDate;
	
	@Column(name = "employment_end_date")
	private String employmentEndDate;
	
	@Column(name = "responsibilities")
	private String responsibilities;
	
	@Column(name = "highest_level_of_education")
	private String highestLevelOfEducation;
	
	@Column(name = "name_of_institution")
	private String nameOfInstitution;
	
	@Column(name = "graduation_year")
	private String graduationYear;
	
	@Column(name = "technical_skills")
	private String technicalSkills;
	
	@Column(name = "soft_skills")
	private String softSkills;
	
	@Column(name = "certification_name")
	private String certificationName;
	
	@Column(name = "issuing_institution")
	private String issuingInstitution;
	
	@Column(name = "year_obtained")
	private String yearObtained;
	
	@Column(name = "cover_letter")
	private String coverLetter;
	
	@Column(name = "resume")
	private String resume;
	
	@Column(name = "source_of_information")
	private String sourceOfInformation;
	
	@Column(name = "previously_applied")
	private String previouslyApplied;
	
	@Column(name = "previously_applied_details")
	private String previouslyAppliedDetails;
	
	@Column(name = "notice_period")
	private String noticePeriod;
	
	@Column(name = "date_available_to_start_work")
	private String dateAvailableToStartWork;
	
	@Column(name = "candidates_availability_for_interviews")
	private String candidatesAvailabilityForInterviews;
	
	@Column(name = "consent")
	private String consent;
	
	@Column(name = "agreement")
	private String agreement;
}
