package com.orive.PayeesAndPayers.Dto;

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
public class PayeesDto {
	
    private Long payeesId;
	private String payeesFullName;
	private String payeesPhoneNo;
	private String payeesEmailAddress;
	private String payeesAddress;
	private String taxIdentificationNo;
	private String employeeId;
	private String jobTitle;
	private String department;
	private LocalDate hireDate;
	private String companyName;
	private String taxId;
	private String productsProvided;
	private String paymentMethod;
	private String bankName;
	private String accountHolderName;
	private String accountNumber;
	private String routingNumber;
	private String taxFilingStatus;
	private int federalTaxWithHoldingAllowances;
	private String stateTaxWithHolding;
	private String healthInsuranceEnrollment;
	private String retirementPlanEnrollment;
	private String otherBenefits;
	private String emergencyContactName;
	private String emergencyContactNo;
	private String relationshipToPayee;
}
