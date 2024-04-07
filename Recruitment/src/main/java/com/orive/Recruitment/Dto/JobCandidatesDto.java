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
public class JobCandidatesDto {
	
    private  Long jobCandidatesId;
	private String candidatesFullName;
	private String candidatesPhoneNumber;
	private String candidatesEmailAddress;
	private String candidatesAddress;
	private String applyingForJobPosition;
	private String referenceNumber;
	private String mostRecentEmployer;
	private String jobTitle;
	private String employmentStartDate;
	private String employmentEndDate;
	private String responsibilities;
	private String highestLevelOfEducation;
	private String nameOfInstitution;
	private String graduationYear;
	private String technicalSkills;
	private String softSkills;
	private String certificationName;
	private String issuingInstitution;
	private String yearObtained;
	private String coverLetter;
	private String resume;
	private String sourceOfInformation;
	private String previouslyApplied;
	private String previouslyAppliedDetails;
	private String noticePeriod;
	private String dateAvailableToStartWork;
	private String candidatesAvailabilityForInterviews;
	private String consent;
	private String agreement;

}
