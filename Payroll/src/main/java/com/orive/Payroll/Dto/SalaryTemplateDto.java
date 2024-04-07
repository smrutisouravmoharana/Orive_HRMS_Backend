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
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryTemplateDto {

    private String salaryTemplateId;
    private String username;
    private String employeeName;
    private String designation;
    private Double workingDays;
    private Double ctc;
    private Double annualIncome;
    private Double newAnnualIncome;
    private Double totalYearCtc;
    private Double basicSalary;
    private Double totalYearBasicSalary;
    private Double houserentAllowance;
    private Double totalYearHouserentAllowance;
    private Double conveyanceAllowance;
    private Double totalYearConveyanceAllowance;
    private Double medicalAllowance;
    private Double totalYearMedicalAllowance;
    private Double educationalAllowance;
    private Double totalYearEducationalAllowance;
    private Double specialAllowance;
    private Double totalYearSpecialAllowance;
    private Double otherAllowance;
    private Double totalYearOtherAllowance;
    private Double travellingAllowance;
    private Double totalYearTravellingAllowance;
    private Double dearnessAllowance;
    private Double totalYearDearnessAllowance;
    private Double grossSalary;
    private Double totalYearGrossSalary;
    private Double employeeDeductionProvidentFund;
    private Double totalYearEmployeeDeductionProvidentFund;
    private Double employeeDeductionEsic;
    private Double totalYearEmployeeDeductionEsic;
    private Double employeeDeductionProfessionalTax;
    private Double totalYearEmployeeDeductionProfessionalTax;
    private Double tds;
    private Double totalYearTds;
    private Double netSalary;
    private Double totalYearNetSalary;
    private Double employeerContributionProvidentFund;
    private Double totalYearEmployeerContributionProvidentFund; 
    private Double employeerContributionEsic;
    private Double totalYearEmployeerContributionEsic;
    private Double gratuity;
    private Double totalYearGratuity;
    private Double gratuityYear;
    private Double employeerContributionVariablePay;
    private Double totalYearEmployeerContributionVariablePay;
    private Double noOfChildren;
    private Double companyPreferredEducationalAllowance;
    private Double bonus;
    private Double totalYearBonus;
    private String payrollTemplate;
    private String state;
    private Double standardDeduction;
    private Double newStandardDeduction;
    private Double employeeProvidentFund;
    private Double publicProvidentFund;
    private Double equityLinkedSavings;
    private Double lifeInsurancePremiums;
    private Double others;
    private Double section80c;
    private Double nationalPensionScheme;
    private Double atalPensionScheme;
    private Double section80ccd1b;
    private Double savingAccountInterest;
    private Double section80tta;
    private Double selfMedicalInsurance;
    private Double parentsMedicalInsurance;
    private Double section80d;
    private Double homeLoanInterest;
    private Double section80eeOrSection24;
    private String employeeType;
    private Double houseRent;
    private Double section80gg;
    private Double educationalLoanInterest;
    private Double section80e;
    private Double charity;
    private Double section80g;
    private Double totalInvestments;
    private Double newTotalInvestments;
    private Double taxableIncome;
    private Double newTaxableIncome;
    private Double oldSlab5Percents;
    
    
    private Double newSlab5Percents;
    private Double oldSlab10Percents;
    
    private Double newSlab10Percents;
    private Double oldSlab15Percents;
    
    private Double newSlab15Percents;
    private Double oldSlab20Percents;
    
    private Double newSlab20Percents;
    private Double oldSlab30Percents;
    
    private Double newSlab30Percents;
    private Double oldSlumOfSlabs;
    
    private Double newSlumOfSlabs;
    private Double oldTaxRebate87A;
    
    private Double newTaxRebate87A;
    private Double oldTaxAfterTaxRebate;
    
    private Double newTaxAfterTaxRebate;
    private Double cess4Percents;
    
    private Double newCess4Percents;
    private Double totalIncomeTax;
    private Double newTotalIncomeTax;
    private Double oldTds;
    private Double newTds;
}