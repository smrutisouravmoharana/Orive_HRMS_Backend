package com.orive.Payroll.Dto;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Builder
@ToString

public class PaySlipGenerateDto {
	
    private String paySlipGenerateId;
    private String username;
    private String employeeName;
    private String designation;
    private String department;
    private Double workingDays;
    private Double presentDays;
    private Double presentBasicSalary;
    private Double overTimeInHrs;
    private Double overTimeSalary;
    private Double basicSalary;
    private Double houserentAllowance;
    private Double conveyanceAllowance;
    private Double medicalAllowance;
    private Double educationalAllowance;
    private Double specialAllowance;
    private Double otherAllowance;
    private Double travellingAllowance;
    private Double dearnessAllowance;
    private Double grossSalary;
    private Double employeeDeductionProvidentFund;
    private Double employeeDeductionEsic;
    private Double employeeDeductionProfessionalTax;
    private Double tds;
    private Double netSalary;
    private Double employeerContributionProvidentFund;
    private Double employeerContributionEsic;
    private Double gratuity;
    private Double gratuityYear;
    private Double employeerContributionVariablePay;
    private Double noOfChildren;
    private Double companyPreferredEducationalAllowance;
    private Double bonus;
    private String payrollTemplate;
    private String state;
    private LocalDate createdDate;

}