package com.orive.Employee.Dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InternJoiningLetterDto {

	private Long internJoiningLetterId;
	private String referenceNo;
	private LocalDate formReleaseDate;
	private String officeAddress;
	private String candidateName;
	private String username;
	private String collegeName;
	private LocalDate joiningDate;
	private LocalDate submissionDate;
	private String subject;
	private String employeeDesignation;
	private String department;
	private LocalDate startDate;
	private LocalDate endDate;
	private String duration;
	private String location;
	private double internStipend;
	private Long weeklyComeOfficeForDays;
	private String reportingTo;
	private String signBondWithCompanyForMonths;
}
