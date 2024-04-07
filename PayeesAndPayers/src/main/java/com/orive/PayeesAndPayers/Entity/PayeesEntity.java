package com.orive.PayeesAndPayers.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "payees")
public class PayeesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long payeesId;
	
	@Column(name = "payees_full_name")
	private String payeesFullName;
	
	@Column(name = "payees_phone_no")
	private String payeesPhoneNo;
	
	@Column(name = "payees_email_address")
	private String payeesEmailAddress;
	
	@Column(name = "payees_address")
	private String payeesAddress;
	
	@Column(name = "tax_identification_no")
	private String taxIdentificationNo;
	
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "job_title")
	private String jobTitle;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "hire_date")
	private LocalDate hireDate;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "tax_id")
	private String taxId;
	
	@Column(name = "products_provided")
	private String productsProvided;
	
	@Column(name = "payment_method")
	private String paymentMethod;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "account_holder_name")
	private String accountHolderName;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "routing_number")
	private String routingNumber;
	
	@Column(name = "tax_filing_status")
	private String taxFilingStatus;
	
	@Column(name = "federal_tax_with_holding_allowances")
	private int federalTaxWithHoldingAllowances;
	
	@Column(name = "state_tax_with_holding")
	private String stateTaxWithHolding;
	
	@Column(name = "health_insurance_enrollment")
	private String healthInsuranceEnrollment;
	
	@Column(name = "retirement_plan_enrollment")
	private String retirementPlanEnrollment;
	
	@Column(name = "other_benefits")
	private String otherBenefits;
	
	@Column(name = "emergency_contact_name")
	private String emergencyContactName;
	
	@Column(name = "emergency_contact_no")
	private String emergencyContactNo;
	
	@Column(name = "relationship_to_payee")
	private String relationshipToPayee;
}
