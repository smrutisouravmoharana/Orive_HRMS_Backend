package com.orive.Employee.Entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
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
@Entity
@Builder
@Table(name = "intern_joining_letter")
public class InternJoiningLetterEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long internJoiningLetterId;
	
	@Column(name = "reference_no")
	private String referenceNo;
	
	@Column(name = "form_release_date")
	private LocalDate formReleaseDate;
	
	@Column(name = "office_address")
	private String officeAddress;
	
	@Column(name = "candidate_name")
	private String candidateName;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "college_name")
	private String collegeName;
	
	@Column(name = "joining_date")
	private LocalDate joiningDate;
	
	@Column(name = "submission_date")
	private LocalDate submissionDate;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "employee_designation")
	private String employeeDesignation;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Column(name = "duration")
	private String duration;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "intern_stipend")
	private double internStipend;
	
	@Column(name = "weekly_come_office_for_days")
	private Long weeklyComeOfficeForDays;
	
	@Column(name = "reporting_to")
	private String reportingTo;
	
	@Column(name = "sign_bond_with_company_for_months")
	private String signBondWithCompanyForMonths;
	
//	// Method for auto generated  reference number
//			private static int lastRandomPart = 0;
//
//			@PrePersist
//			public void generateReferenceNumber() {
//			    // Generate reference number logic
//			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-");
//			    String currentDate = dateFormat.format(new Date());
//			    String randomPart = String.format("%03d", getNextRandomPart()); // Adjust length as per requirement
//			    this.referenceNo = "REF-" + currentDate + randomPart;
//			}
//
//			private synchronized int getNextRandomPart() {
//			    // Increment lastRandomPart and return it as a formatted string
//			    lastRandomPart = (lastRandomPart % 999) + 1; // Reset to 1 if exceeds 999
//			    return lastRandomPart;
//			}

}
