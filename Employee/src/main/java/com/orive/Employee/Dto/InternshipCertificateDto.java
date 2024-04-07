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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class InternshipCertificateDto {

    private Long internshipCertificateId;
	private String referrenceNo;
	private String employeeName;
	private String registrationNo;
	private LocalDate date;
	private LocalDate issuesDate;
	private String internsName;
	private LocalDate startDate;
	private LocalDate endDate;
	private String productName;
	private String directorName;
}
