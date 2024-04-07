package com.orive.Payroll.Entity;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "salary_template")
public class SalaryTemplateEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence")
    @GenericGenerator(name = "custom-sequence", strategy = "com.orive.Payroll.Entity.SalaryTemplateCustomIdGenerator")
    private String salaryTemplateId;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "employee_name")
    private String employeeName;
    
    @Column(name = "designation")
    private String designation;
    
    @Column(name = "working_days")
    private Double workingDays;
    
    @Column(name = "ctc")
    private Double ctc;
    
    @Column(name = "annual_income")
    private Double annualIncome;
    
    @Column(name = "new_annual_income")
    private Double newAnnualIncome;
    
    @Column(name = "total_year_ctc")
    private Double totalYearCtc;

    @Column(name = "basic_salary")
    private Double basicSalary;
    
    @Column(name = "total_year_basic_salary")
    private Double totalYearBasicSalary;

    @Column(name = "houserent_allowance")
    private Double houserentAllowance;
    
    @Column(name = "total_year_houserent_allowance")
    private Double totalYearHouserentAllowance;

    @Column(name = "conveyance_allowance")
    private Double conveyanceAllowance;
    
    @Column(name = "total_year_conveyance_allowance")
    private Double totalYearConveyanceAllowance;

    @Column(name = "medical_allowance")
    private Double medicalAllowance;
    
    @Column(name = "total_year_medical_allowance")
    private Double totalYearMedicalAllowance;

    @Column(name = "educational_allowance")
    private Double educationalAllowance;
    
    @Column(name = "total_year_educational_allowance")
    private Double totalYearEducationalAllowance;

    @Column(name = "special_allowance")
    private Double specialAllowance;
    
    @Column(name = "total_year_special_allowance")
    private Double totalYearSpecialAllowance;

    @Column(name = "other_allowance")
    private Double otherAllowance;
    
    @Column(name = "total_year_other_allowance")
    private Double totalYearOtherAllowance;

    @Column(name = "travelling_allowance")
    private Double travellingAllowance;
    
    @Column(name = "total_year_travelling_allowance")
    private Double totalYearTravellingAllowance;

    @Column(name = "dearness_allowance")
    private Double dearnessAllowance;
    
    @Column(name = "total_year_dearness_allowance")
    private Double totalYearDearnessAllowance;

    @Column(name = "gross_salary")
    private Double grossSalary;
    
    @Column(name = "total_year_gross_salary")
    private Double totalYearGrossSalary;

    @Column(name = "employee_deduction_provident_fund")
    private Double employeeDeductionProvidentFund;
    
    @Column(name = "total_year_employee_deduction_provident_fund")
    private Double totalYearEmployeeDeductionProvidentFund;

    @Column(name = "employee_deduction_esic")
    private Double employeeDeductionEsic;
    
    @Column(name = "total_year_employee_deduction_esic")
    private Double totalYearEmployeeDeductionEsic;

    @Column(name = "employee_deduction_professional_tax")
    private Double employeeDeductionProfessionalTax;
    
    @Column(name = "total_year_employee_deduction_professional_tax")
    private Double totalYearEmployeeDeductionProfessionalTax;

    @Column(name = "tds")
    private Double tds;
    
    @Column(name = "total_year_tds")
    private Double totalYearTds;

    @Column(name = "net_salary")
    private Double netSalary;
    
    @Column(name = "total_year_net_salary")
    private Double totalYearNetSalary;

    @Column(name = "employeer_contribution_provident_fund")
    private Double employeerContributionProvidentFund;
    
    @Column(name = "total_year_employeer_contribution_provident_fund")
    private Double totalYearEmployeerContributionProvidentFund; 

    @Column(name = "employeer_contribution_esic")
    private Double employeerContributionEsic;
    
    @Column(name = "total_year_employeer_contribution_esic")
    private Double totalYearEmployeerContributionEsic;

    @Column(name = "gratuity")
    private Double gratuity;
    
    @Column(name = "total_year_gratuity")
    private Double totalYearGratuity;

    @Column(name = "gratuity_year")
    private Double gratuityYear;

    @Column(name = "employeer_contribution_variable_pay")
    private Double employeerContributionVariablePay;
    
    @Column(name = "total_year_employeer_contribution_variable_pay")
    private Double totalYearEmployeerContributionVariablePay;

    @Column(name = "no_of_children")
    private Double noOfChildren;

    @Column(name = "company_preferred_educational_allowance")
    private Double companyPreferredEducationalAllowance;

    @Column(name = "bonus")
    private Double bonus;
    
    @Column(name = "total_year_bonus")
    private Double totalYearBonus;

    @Column(name = "payroll_template")
    private String payrollTemplate;

    @Column(name = "state")
    private String state;
    
    @Column(name = "standard_deduction")
    private Double standardDeduction;
    
    @Column(name = "new_standard_deduction")
    private Double newStandardDeduction;
    
    @Column(name = "employee_provident_fund")
    private Double employeeProvidentFund;
    
    @Column(name = "public_provident_fund")
    private Double publicProvidentFund;
    
    @Column(name = "equity_linked_savings")
    private Double equityLinkedSavings;
    
    @Column(name = "life_insurance_premiums")
    private Double lifeInsurancePremiums;
    
    @Column(name = "others")
    private Double others;
    
    @Column(name = "section_80c")
    private Double section80c;
    
    @Column(name = "national_pension_scheme")
    private Double nationalPensionScheme;
    
    @Column(name = "atal_pension_scheme")
    private Double atalPensionScheme;
    
    @Column(name = "section80ccd1b")
    private Double section80ccd1b;
    
    @Column(name = "saving_account_interest")
    private Double savingAccountInterest;
    
    @Column(name = "section80tta")
    private Double section80tta;
    
    @Column(name = "self_medical_insurance")
    private Double selfMedicalInsurance;
    
    @Column(name = "parents_medical_insurance")
    private Double parentsMedicalInsurance;
    
    @Column(name = "section80d")
    private Double section80d;
    
    @Column(name = "home_loan_interest")
    private Double homeLoanInterest;
    
    @Column(name = "section80ee_or_section24")
    private Double section80eeOrSection24;
    
    
    private String employeeType;
    
    @Column(name = "house_rent")
    private Double houseRent;
    
    @Column(name = "section80gg")
    private Double section80gg;
    
    @Column(name = "educational_loan_interest")
    private Double educationalLoanInterest;
    
    @Column(name = "section80e")
    private Double section80e;
    
    @Column(name = "charity")
    private Double charity;
    
    @Column(name = "section80g")
    private Double section80g;
    
    @Column(name = "total_investments")
    private Double totalInvestments;
    
    @Column(name = "new_total_investments")
    private Double newTotalInvestments;
    
    @Column(name = "taxable_income")
    private Double taxableIncome;
    
    @Column(name = "new_taxable_income")
    private Double newTaxableIncome;
    
    @Column(name = "old_slab5percents")
    private Double oldSlab5Percents;
    
    
    private Double newSlab5Percents;
    
    @Column(name = "old_slab10percents")
    private Double oldSlab10Percents;
    
    private Double newSlab10Percents;
    
    @Column(name = "old_slab15percents")
    private Double oldSlab15Percents;
    
    private Double newSlab15Percents;
    
    @Column(name = "old_slab20percents")
    private Double oldSlab20Percents;
    
    private Double newSlab20Percents;
    
    @Column(name = "old_slab30percents")
    private Double oldSlab30Percents;
    
    private Double newSlab30Percents;
    
    @Column(name = "old_slum_of_slabs")
    private Double oldSlumOfSlabs;
    
    private Double newSlumOfSlabs;
    
    @Column(name = "old_tax_rebate87a")
    private Double oldTaxRebate87A;
    
    private Double newTaxRebate87A;
    
    @Column(name = "old_tax_after_tax_rebate")
    private Double oldTaxAfterTaxRebate;
    
    private Double newTaxAfterTaxRebate;
    
    @Column(name = "cess4percents")
    private Double cess4Percents;
    
    private Double newCess4Percents;
    
    @Column(name = "total_income_tax")
    private Double totalIncomeTax;
       
    private Double newTotalIncomeTax;
    
    @Column(name = "old_tds")
    private Double oldTds;
    
    @Column(name = "new_tds")
    private Double newTds;  

    @Column(name = "created_date")
    private LocalDate createdDate;

    
    
    // This method will be called before the entity is persisted
    @PrePersist
    public void calculateAllowances() {
        calculateHouserentAllowance();
        calculateEducationalAllowance();
        calculateGrossSalary();
        calculateDeductions();
        calculateEmployerContributionProvidentFund();
        calculateEmployeeContributionEsic();
        calculateGratuity();
        calculateBonus();
        calculateCTC();
        calculateTotalYearCTC();
        calculateTotalYearBasicSalary();
        calculateTotalYearHouserentAllowance();
        calculateTotalYearConveyanceAllowance();
        calculateTotalYearMedicalAllowance();
        calculateTotalYearMedicalAllowance();
        calculateTotalYearEducationalAllowance();
        calculateTotalYearSpecialAllowance();
        calculateTotalYearOtherAllowance();
        calculateTotalYearTravellingAllowance();
        calculateTotalYearDearnessAllowance();
        calculateTotalYearGrossSalary();
        calculateTotalYearEmployeerContributionProvidentFund();
        calculateTotalYearEmployeerContributionEsic();
        calculateTotalYearGratuity();
        calculateTotalYearBonus();
        calculateTotalYearEmployeerContributionVariablePay();
        setStandardDeduction();
        setNewStandardDeduction();
        calculateSection80c();
        calculateSection80ccd1b();
        calculateSection80tta();
        calculateSection80d();
        calculateSection80eeorSection24();
        calculateSection80gg();
        calculateSection80e();
        calculateSection80g();
        calculateTotalInvestment();
        calculateNewTotalInvestments();
        calculateTaxableIncome();
        calculateNewTaxableIncome();
        calculateOldSlab5Percents();
        calculateNewSlab5Percents();
        calculateOldSlab10Percents();
        calculateNewSlab10Percents();
        calculateOldSlab15percentage();
        calculateNewSlab15Percents();
        calculateOldSlab20percentage();
        calculateNewSlab20Percents();
        calculateOldSlab30percentage();
        calculateNewSlab30percentage();
        calculateSumOfSlabs();
        calculateNewSumOfSlabs();
        calculateOldTaxRebate87A();
        calculateNewTaxRebate87A();
        calculateOldTaxAfterTaxRebate();
        calculateNewTaxAfterTaxRebate();
        calculateCess4Percents();
        calculateNewCess4Percents();
        calculateTotalIncomeTax();
        calculateNewTotalIncomeTax();
        calculateOldTds();
        calculateNewTds();
        
    }

    @PreUpdate
    public void updateAllowances() {
        // You can add logic here if you want to update allowances before an update
    }
    
 // Calculate Total Yearly basic salary
    private void calculateTotalYearBasicSalary() {
        if (this.basicSalary != null) {
            this.totalYearBasicSalary = this.basicSalary * 12;
        } else {
            throw new IllegalStateException("basicSalary cannot be null for total yearly basicSalary calculation");
        }
    }

    // Calculate HouseRent Allowance
    private void calculateHouserentAllowance() {
        if (this.basicSalary != null) {
            this.houserentAllowance = this.basicSalary * 0.4;
        } else {
            throw new IllegalStateException("Basic Salary cannot be null for houserent allowance calculation");
        }
    }
    
 // Calculate Total Yearly House Rent Allowance
    private void calculateTotalYearHouserentAllowance() {
        if (this.houserentAllowance != null) {
            this.totalYearHouserentAllowance = this.houserentAllowance * 12;
        } else {
            throw new IllegalStateException("houserentAllowance cannot be null for total yearly houserentAllowance calculation");
        }
    }
    
 // Calculate Total Yearly House Rent Allowance
    private void calculateTotalYearConveyanceAllowance() {
        if (this.conveyanceAllowance != null) {
            this.totalYearConveyanceAllowance = this.conveyanceAllowance * 12;
        } else {
            throw new IllegalStateException("conveyanceAllowance cannot be null for total yearly conveyanceAllowance calculation");
        }
    }
    
 // Calculate Total Yearly medicalAllowance 
    private void calculateTotalYearMedicalAllowance() {
        if (this.medicalAllowance != null) {
            this.totalYearMedicalAllowance = this.medicalAllowance * 12;
        } else {
            throw new IllegalStateException("educationalAllowance cannot be null for total yearly educationalAllowance calculation");
        }
    }

    // Calculate Educational Allowance
    private void calculateEducationalAllowance() {
        if (this.noOfChildren != null && this.companyPreferredEducationalAllowance != null) {
            this.educationalAllowance = this.noOfChildren * this.companyPreferredEducationalAllowance;
        } else {
            throw new IllegalStateException(
                    "Both noOfChildren and companyPreferredEducationalAllowance must be set for educational allowance calculation");
        }
    }
    
 // Calculate Total Yearly educationalAllowance 
    private void calculateTotalYearEducationalAllowance() {
        if (this.educationalAllowance != null) {
            this.totalYearEducationalAllowance = this.educationalAllowance * 12;
        } else {
            throw new IllegalStateException("educationalAllowance cannot be null for total yearly educationalAllowance calculation");
        }
    }
    
 // Calculate Total Yearly specialAllowance 
    private void calculateTotalYearSpecialAllowance() {
        if (this.specialAllowance != null) {
            this.totalYearSpecialAllowance = this.specialAllowance * 12;
        } else {
            throw new IllegalStateException("specialAllowance cannot be null for total yearly specialAllowance calculation");
        }
    }
    
    
 // Calculate Total Yearly otherAllowance 
    private void calculateTotalYearOtherAllowance() {
        if (this.otherAllowance != null) {
            this.totalYearOtherAllowance = this.otherAllowance * 12;
        } else {
            throw new IllegalStateException("otherAllowance cannot be null for total yearly otherAllowance calculation");
        }
    }
    
    
 // Calculate Total Yearly travellingAllowance 
    private void calculateTotalYearTravellingAllowance() {
        if (this.travellingAllowance != null) {
            this.totalYearTravellingAllowance = this.travellingAllowance * 12;
        } else {
            throw new IllegalStateException("travellingAllowance cannot be null for total yearly travellingAllowance calculation");
        }
    }
    
    
 // Calculate Total Yearly dearnessAllowance 
    private void calculateTotalYearDearnessAllowance() {
        if (this.dearnessAllowance != null) {
            this.totalYearDearnessAllowance = this.dearnessAllowance * 12;
        } else {
            throw new IllegalStateException("dearnessAllowance cannot be null for total yearly dearnessAllowance calculation");
        }
    }


    
    // Calculate Gross Salary
    private void calculateGrossSalary() {
        this.grossSalary = basicSalary
                + (houserentAllowance != null ? houserentAllowance : 0)
                + (conveyanceAllowance != null ? conveyanceAllowance : 0)
                + (medicalAllowance != null ? medicalAllowance : 0)
                + (educationalAllowance != null ? educationalAllowance : 0)
                + (specialAllowance != null ? specialAllowance : 0)
                + (otherAllowance != null ? otherAllowance : 0)
                + (dearnessAllowance != null ? dearnessAllowance : 0)
                + (travellingAllowance != null ? travellingAllowance : 0);
    }
    
 // Calculate Total Yearly grossSalary 
    private void calculateTotalYearGrossSalary() {
        if (this.grossSalary != null) {
            this.totalYearGrossSalary = this.grossSalary * 12;
        } else {
            throw new IllegalStateException("grossSalary cannot be null for total yearly grossSalary calculation");
        }
    }

    // Calculate Employee Provident Fund
    private void calculateDeductions() {
        calculateEmployeeDeductionProvidentFund();
        calculateEmployeeDeductionEsic();
        calculateEmployeeDeductionProfessionalTax();
        calculateNetSalary();
        calculateTotalYearEmployeeDeductionProvidentFund();
        calculateTotalYearEmployeeDeductionEsic();
        calculateTotalYearEmployeeDeductionProfessionalTax();
        calculateTotalYearTds();
        calculateTotalYearNetSalary();

        // Add other deduction calculations here...
    }

    private void calculateEmployeeDeductionProvidentFund() {
        if (this.basicSalary != null) {
            this.employeeDeductionProvidentFund = this.basicSalary * 0.12;
        } else {
            throw new IllegalStateException("Basic Salary cannot be null for provident fund deduction calculation");
        }
    }
    
 // Calculate Total Yearly grossSalary 
    private void calculateTotalYearEmployeeDeductionProvidentFund() {
        if (this.employeeDeductionProvidentFund != null) {
            this.totalYearEmployeeDeductionProvidentFund = this.employeeDeductionProvidentFund * 12;
        } else {
            throw new IllegalStateException("employeeDeductionProvidentFund cannot be null for total yearly employeeDeductionProvidentFund calculation");
        }
    }


    private void calculateEmployeeDeductionEsic() {
        if (this.grossSalary != null) {
            // Assuming ESIC deduction is 0.75% of gross salary if gross salary is less than 21000
            // You can adjust the condition and formula based on your specific requirements
            if (this.grossSalary < 21000) {
                this.employeeDeductionEsic = this.grossSalary * 0.0075;
            } else {
                // Handle the case when gross salary is greater than or equal to 21000
                this.employeeDeductionEsic = 0.0; // Adjust this based on your specific logic
            }
        } else {
            throw new IllegalStateException("Gross Salary cannot be null for ESIC deduction calculation");
        }
    }
    
 // Calculate Total Yearly employeeDeductionEsic 
    private void calculateTotalYearEmployeeDeductionEsic() {
        if (this.employeeDeductionEsic != null) {
            this.totalYearEmployeeDeductionEsic = this.employeeDeductionEsic * 12;
        } else {
            throw new IllegalStateException("totalYearEmployeeDeductionEsic cannot be null for total yearly employeeDeductionEsic calculation");
        }
    }

    private void calculateEmployeeDeductionProfessionalTax() {
        if ("ODISHA".equalsIgnoreCase(this.state) && this.grossSalary != null && this.grossSalary > 20000) {
            this.employeeDeductionProfessionalTax = 200.0;
        } else {
            this.employeeDeductionProfessionalTax = 0.0; // No professional tax if conditions are not met
        }
    }
    
 // Calculate Total Yearly employeeDeductionProfessionalTax 
    private void calculateTotalYearEmployeeDeductionProfessionalTax() {
        if (this.employeeDeductionProfessionalTax != null) {
            this.totalYearEmployeeDeductionProfessionalTax = this.employeeDeductionProfessionalTax * 12;
        } else {
            throw new IllegalStateException("employeeDeductionProfessionalTax cannot be null for total yearly employeeDeductionProfessionalTax calculation");
        }
    }
    
    // Calculate Total Yearly tds 
    private void calculateTotalYearTds() {
        if (this.tds != null) {
            this.totalYearTds = this.tds * 12;
        } else {
            throw new IllegalStateException("tds cannot be null for total yearly tds calculation");
        }
    }
    

    private void calculateNetSalary() {
        this.netSalary = this.grossSalary
                - ((this.employeeDeductionProvidentFund != null ? this.employeeDeductionProvidentFund : 0)
                + (this.employeeDeductionEsic != null ? this.employeeDeductionEsic : 0)
                + (this.employeeDeductionProfessionalTax != null ? this.employeeDeductionProfessionalTax : 0)
                + (this.tds != null ? this.tds : 0));
    }
    
 // Calculate Total Yearly tds 
    private void calculateTotalYearNetSalary() {
        if (this.netSalary != null) {
            this.totalYearNetSalary = this.netSalary * 12;
        } else {
            throw new IllegalStateException("netSalary cannot be null for total yearly netSalary calculation");
        }
    }
    
    // Calculate Employer Contribution Provident Fund
    private void calculateEmployerContributionProvidentFund() {
        if (this.basicSalary != null) {
            this.employeerContributionProvidentFund = this.basicSalary * 0.13;
        } else {
            throw new IllegalStateException("Basic Salary cannot be null for employer contribution to provident fund calculation");
        }
    }
    
 // Calculate Total Yearly employeerContributionProvidentFund 
    private void calculateTotalYearEmployeerContributionProvidentFund() {
        if (this.employeerContributionProvidentFund != null) {
            this.totalYearEmployeerContributionProvidentFund = this.employeerContributionProvidentFund * 12;
        } else {
            throw new IllegalStateException("employeerContributionProvidentFund cannot be null for total yearly employeerContributionProvidentFund calculation");
        }
    }
    
 // Calculate Employee Contribution ESIC
    private void calculateEmployeeContributionEsic() {
        if (this.grossSalary != null) {
            // Assuming employee contribution to ESIC is 3.25% of gross salary if gross salary is less than 21000
            // You can adjust the condition and formula based on your specific requirements
            if (this.grossSalary < 21000) {
                this.employeerContributionEsic = this.grossSalary * 0.0325;
            } else {
                // Handle the case when gross salary is greater than or equal to 21000
                this.employeerContributionEsic = 0.0; // Adjust this based on your specific logic
            }
        } else {
            throw new IllegalStateException("Gross Salary cannot be null for employee contribution to ESIC calculation");
        }
    }
    
 // Calculate Total Yearly employeerContributionEsic 
    private void calculateTotalYearEmployeerContributionEsic() {
        if (this.employeerContributionEsic != null) {
            this.totalYearEmployeerContributionEsic = this.employeerContributionEsic * 12;
        } else {
            throw new IllegalStateException("employeerContributionEsic cannot be null for total yearly employeerContributionEsic calculation");
        }
    }
    
 // Calculate Gratuity
    private void calculateGratuity() {
        if (this.basicSalary != null && this.gratuityYear != null) {
            this.gratuity = ((this.basicSalary / 26) * 15 * this.gratuityYear) / 12;
        } else {
            throw new IllegalStateException(
                    "Both basicSalary and gratuityYear must be set for gratuity calculation");
        }
    }
    
 // Calculate Total Yearly gratuity 
    private void calculateTotalYearGratuity() {
        if (this.gratuity != null) {
            this.totalYearGratuity = this.gratuity * 12;
        } else {
            throw new IllegalStateException("gratuity cannot be null for total yearly gratuity calculation");
        }
    }
    
    // Calculate Bonus
    private void calculateBonus() {
        if (this.basicSalary != null) {
            this.bonus = this.basicSalary * 0.0833;
        } else {
            throw new IllegalStateException("Basic Salary cannot be null for bonus calculation");
        }
    }
    
 // Calculate Total Yearly bonus 
    private void calculateTotalYearBonus() {
        if (this.bonus != null) {
            this.totalYearBonus = this.bonus * 12;
        } else {
            throw new IllegalStateException("bonus cannot be null for total yearly bonus calculation");
        }
    }
    
 // Calculate Total Yearly employeerContributionVariablePay 
    private void calculateTotalYearEmployeerContributionVariablePay() {
        if (this.employeerContributionVariablePay != null) {
            this.totalYearEmployeerContributionVariablePay = this.employeerContributionVariablePay * 12;
        } else {
            throw new IllegalStateException("employeerContributionVariablePay cannot be null for total yearly employeerContributionVariablePay calculation");
        }
    }
    
 // Calculate CTC
    private void calculateCTC() {
        this.ctc = this.grossSalary
                + (this.employeerContributionProvidentFund != null ? this.employeerContributionProvidentFund : 0)
                + (this.employeerContributionEsic != null ? this.employeerContributionEsic : 0)
                + (this.gratuity != null ? this.gratuity : 0)
                + (this.bonus != null ? this.bonus : 0)
                + (this.employeerContributionVariablePay != null ? this.employeerContributionVariablePay : 0);
    }
    
    // Calculate Total Yearly CTC
    private void calculateTotalYearCTC() {
        if (this.ctc != null) {
            this.totalYearCtc = this.ctc * 12;
        } else {
            throw new IllegalStateException("CTC cannot be null for total yearly CTC calculation");
        }
    }
    
    
 // Method to set standardDeduction to a fixed value of 50000
    private void setStandardDeduction() {
        this.standardDeduction = 50000.0;
    }
    
 // Method to set standardDeduction to a fixed value of 50000
    private void setNewStandardDeduction() {
        this.newStandardDeduction = 50000.0;
    }
    
 // Method to calculate section80c based on conditions
    private void calculateSection80c() {
        // Summing up the eligible amounts
        double totalEligibleAmount = employeeProvidentFund+publicProvidentFund + equityLinkedSavings + lifeInsurancePremiums + others;

        // Setting section80c based on conditions
        if (totalEligibleAmount <= 150000) {
            this.section80c = totalEligibleAmount;
        } else {
            this.section80c = 150000.0;
        }
    }
    
    // Method to calculate section80ccd1b based on conditions
    private void calculateSection80ccd1b() {
        // Summing up the eligible amounts
        double totalEligibleAmount = nationalPensionScheme + atalPensionScheme;

        // Setting section80c based on conditions
        if (totalEligibleAmount <= 50000) {
            this.section80ccd1b = totalEligibleAmount;
        } else {
            this.section80ccd1b = 50000.0;
        }
    }
    
    // Method to calculate section80tta based on conditions
    private void calculateSection80tta() {
        // Summing up the eligible amounts
        double totalEligibleAmount = savingAccountInterest;

        // Setting section80c based on conditions
        if (totalEligibleAmount <= 10000) {
            this.section80tta = totalEligibleAmount;
        } else {
            this.section80tta = 10000.0;
        }
    }
    
    // Method to calculate section80d based on conditions
    private void calculateSection80d() {
        // Summing up the eligible amounts
        double totalEligibleAmount = selfMedicalInsurance + parentsMedicalInsurance;

        // Setting section80c based on conditions
        if (totalEligibleAmount <= 55000) {
            this.section80d = totalEligibleAmount;
        } else {
            this.section80d = 55000.0;
        }
    }
    
 // Method to calculate section80eeOrSection24 based on conditions
    private void calculateSection80eeorSection24() {
        // Summing up the eligible amounts
        double totalEligibleAmount = homeLoanInterest;

        // Setting section80c based on conditions
        if (totalEligibleAmount <= 250000) {
            this.section80eeOrSection24 = totalEligibleAmount;
        } else {
            this.section80eeOrSection24 = 250000.0;
        }
    }
    
    private void calculateSection80gg() {
        // Summing up the eligible amounts
        double totalEligibleAmount = 0.0;
        
        // Determine the total eligible amount based on employeetype
        if (employeeType.equals("employee")) {
            totalEligibleAmount = houseRent;
        } else if (employeeType.equals("nonemployee")) {
            totalEligibleAmount = houseRent;
        }

        // Setting section80gg based on conditions
        if (totalEligibleAmount <= 60000) {
            this.section80gg = totalEligibleAmount;
        } else {
            this.section80gg = 60000.0;
        }
    }
    
 // Method to calculate section80e
    private void calculateSection80e() {
        // Setting section80e as the educational loan interest
        this.section80e = educationalLoanInterest;
    }
    
 // Method to calculate section80g
    private void calculateSection80g() {
        // Setting section80e as the educational loan interest
        this.section80g = charity;
    }
    
    // Method to calculate totalInvestment without any limit
    private Double calculateTotalInvestment() {
        // Initialize totalInvestment
        this.totalInvestments = 0.0;

        // Add section80c, section80ccd1b, section80tta, section80d, section80eeOrSection24, section80gg,
        // section80e, and section80g to totalInvestment
        if (this.section80c != null) {
        	this.totalInvestments += this.section80c;
        }
        if (this.section80ccd1b != null) {
        	this.totalInvestments += this.section80ccd1b;
        }
        if (this.section80tta != null) {
        	this.totalInvestments += this.section80tta;
        }
        if (this.section80d != null) {
        	this.totalInvestments += this.section80d;
        }
        if (this.section80eeOrSection24 != null) {
        	this.totalInvestments += this.section80eeOrSection24;
        }
        if (this.section80gg != null) {
        	this.totalInvestments += this.section80gg;
        }
        if (this.section80e != null) {
        	this.totalInvestments += this.section80e;
        }
        if (this.section80g != null) {
        	this.totalInvestments += this.section80g;
        }

        return totalInvestments;
    }
    
    private void calculateNewTotalInvestments() {
        // Initialize newTotalInvestments
        this.newTotalInvestments = 0.0;

        // Add all section values to newTotalInvestments
        if (this.section80c != null) {
            this.newTotalInvestments += this.section80c;
        }
        if (this.section80ccd1b != null) {
            this.newTotalInvestments += this.section80ccd1b;
        }
        if (this.section80tta != null) {
            this.newTotalInvestments += this.section80tta;
        }
        if (this.section80d != null) {
            this.newTotalInvestments += this.section80d;
        }
        if (this.section80eeOrSection24 != null) {
            this.newTotalInvestments += this.section80eeOrSection24;
        }
        if (this.section80gg != null) {
            this.newTotalInvestments += this.section80gg;
        }
        if (this.section80e != null) {
            this.newTotalInvestments += this.section80e;
        }
        if (this.section80g != null) {
            this.newTotalInvestments += this.section80g;
        }
    }

    
    
    private void calculateTaxableIncome() {
        this.taxableIncome = this.annualIncome - this.newStandardDeduction - this.totalInvestments;
    }
    
    private void calculateNewTaxableIncome() {
        this.newTaxableIncome = this.newAnnualIncome - this.newStandardDeduction - this.newTotalInvestments;
    }
    
 // Method to calculate oldSlab5percentage
    private void calculateOldSlab5Percents() {
        if (taxableIncome != null && taxableIncome > 250000 && taxableIncome <= 500000) {
            this.oldSlab5Percents = (taxableIncome - 250000) * 0.05;
        } else if (taxableIncome != null && taxableIncome > 500000) {
            this.oldSlab5Percents = (500000 - 250000) * 0.05;
        } else {
            this.oldSlab5Percents = 0.0; // or handle the case when taxableIncome is not within the specified range
        }
    }
    
 // Method to calculate oldSlab5percentage
    private void calculateNewSlab5Percents() {
        if (newTaxableIncome != null && newTaxableIncome > 300000 && newTaxableIncome <= 600000) {
            this.newSlab5Percents = (newTaxableIncome - 300000) * 0.05;
        } else if (newTaxableIncome != null && newTaxableIncome > 600000) {
            this.newSlab5Percents = (600000 - 300000) * 0.05;
        } else {
            this.newSlab5Percents = 0.0; // or handle the case when taxableIncome is not within the specified range
        }
    }
    
    
    private void calculateOldSlab10Percents() {
        this.oldSlab10Percents = 0.00;
    } 
    
 // Method to calculate oldSlab5percentage
    private void calculateNewSlab10Percents() {
        if (newTaxableIncome != null && newTaxableIncome > 600000 && newTaxableIncome <= 900000) {
            this.newSlab10Percents = (newTaxableIncome - 600000) * 0.10;
        } else if (newTaxableIncome != null && newTaxableIncome > 900000) {
            this.newSlab10Percents = (900000 - 600000) * 0.10;
        } else {
            this.newSlab10Percents = 0.0; // or handle the case when taxableIncome is not within the specified range
        }
    }
    // Method to calculate oldSlab10percentage
    private void calculateOldSlab15percentage() {
    	this.oldSlab15Percents = 0.00;
    }
    
 // Method to calculate oldSlab5percentage
    private void calculateNewSlab15Percents() {
        if (newTaxableIncome != null && newTaxableIncome > 900000 && newTaxableIncome <= 1200000) {
            this.newSlab15Percents = (newTaxableIncome - 900000) * 0.15;
        } else if (newTaxableIncome != null && newTaxableIncome > 1200000) {
            this.newSlab15Percents = (1200000 - 900000) * 0.15;
        } else {
            this.newSlab15Percents = 0.0; // or handle the case when taxableIncome is not within the specified range
        }
    }
    
    // Method to calculate oldSlab5percentage
    private void calculateOldSlab20percentage() {
    	 if (taxableIncome != null && taxableIncome > 500000 && taxableIncome <= 1000000) {
             this.oldSlab20Percents = (taxableIncome - 500000) * 0.20;
         } else if (taxableIncome != null && taxableIncome > 1000000) {
             this.oldSlab20Percents = (1000000 - 500000) * 0.20;
         } else {
             this.oldSlab20Percents = 0.0; // or handle the case when taxableIncome is not within the specified range
         }
    }
    
 // Method to calculate oldSlab5percentage
    private void calculateNewSlab20Percents() {
        if (newTaxableIncome != null && newTaxableIncome > 1200000 && newTaxableIncome <= 1500000) {
            this.newSlab20Percents = (newTaxableIncome - 1200000) * 0.20;
        } else if (newTaxableIncome != null && newTaxableIncome > 1500000) {
            this.newSlab20Percents = (1500000 - 1200000) * 0.20;
        } else {
            this.newSlab20Percents = 0.0; // or handle the case when taxableIncome is not within the specified range
        }
    }
    
 // Method to calculate oldSlab5percentage
    private void calculateOldSlab30percentage() {
    	if (taxableIncome != null && taxableIncome > 1000000) {
            this.oldSlab30Percents = (taxableIncome - 1000000) * 0.30;
        }  else {
            this.oldSlab30Percents = 0.0; // or handle the case when taxableIncome is not within the specified range
        }
    }
    
 // Method to calculate oldSlab5percentage
    private void calculateNewSlab30percentage() {
    	if (newTaxableIncome != null && newTaxableIncome > 1500000) {
            this.newSlab30Percents = (newTaxableIncome - 1500000) * 0.30;
        }  else {
            this.newSlab30Percents = 0.0; // or handle the case when taxableIncome is not within the specified range
        }
    }
    
 // Method to calculate sumOfSlabs
    private Double calculateSumOfSlabs() {
        // Initialize sumOfSlabs
        this.oldSlumOfSlabs = 0.0;

        // Summing up the values of all slabs
        if (this.oldSlab5Percents != null) {
            this.oldSlumOfSlabs += this.oldSlab5Percents;
        }
        if (this.oldSlab10Percents != null) {
            this.oldSlumOfSlabs += this.oldSlab10Percents;
        }
        if (this.oldSlab15Percents != null) {
            this.oldSlumOfSlabs += this.oldSlab15Percents;
        }
        if (this.oldSlab20Percents != null) {
            this.oldSlumOfSlabs += this.oldSlab20Percents;
        }
        if (this.oldSlab30Percents != null) {
            this.oldSlumOfSlabs += this.oldSlab30Percents;
        }

        return oldSlumOfSlabs;
    }
    
    // Method to calculate sumOfSlabs
    private Double calculateNewSumOfSlabs() {
        // Initialize sumOfSlabs
        this.newSlumOfSlabs = 0.0;

        // Summing up the values of all slabs
        if (this.newSlab5Percents != null) {
            this.newSlumOfSlabs += this.newSlab5Percents;
        }
        if (this.newSlab10Percents != null) {
            this.newSlumOfSlabs += this.newSlab10Percents;
        }
        if (this.newSlab15Percents != null) {
            this.newSlumOfSlabs += this.newSlab15Percents;
        }
        if (this.newSlab20Percents != null) {
            this.newSlumOfSlabs += this.newSlab20Percents;
        }
        if (this.newSlab30Percents != null) {
            this.newSlumOfSlabs += this.newSlab30Percents;
        }

        return newSlumOfSlabs;
    }
    
    private void calculateOldTaxRebate87A() {
        if (this.taxableIncome <= 500000) {
            this.oldTaxRebate87A = 12500.0;
        } else {
            this.oldTaxRebate87A = 0.0;
        }
    }
    private void calculateNewTaxRebate87A() {
        if (this.newTaxableIncome <= 700000) {
            this.newTaxRebate87A = 12500.0;
        } else {
            this.newTaxRebate87A = 0.0;
        }
    }
    
    private void calculateOldTaxAfterTaxRebate() {
        if (this.oldSlumOfSlabs <= 12500) {
            this.oldTaxAfterTaxRebate = 0.0;
        } else {
            this.oldTaxAfterTaxRebate = this.oldSlumOfSlabs - this.oldTaxRebate87A;
        }
    }
    
    
    private void calculateNewTaxAfterTaxRebate() {
        if (this.newSlumOfSlabs <= 12500) {
            this.newTaxAfterTaxRebate = 0.0;
        } else {
            this.newTaxAfterTaxRebate = this.newSlumOfSlabs - this.newTaxRebate87A;
        }
    }

    
    private void calculateCess4Percents() {
        this.cess4Percents = this.oldTaxAfterTaxRebate * 0.04;
    }
    
    private void calculateNewCess4Percents() {
        this.newCess4Percents = this.newTaxAfterTaxRebate * 0.04;
    }
    
    private void calculateTotalIncomeTax() {
        this.totalIncomeTax = this.oldTaxAfterTaxRebate + this.cess4Percents;
    }
    
    private void calculateNewTotalIncomeTax() {
        this.newTotalIncomeTax = this.newTaxAfterTaxRebate + this.newCess4Percents;
    }

    private void calculateOldTds() {
        this.oldTds = this.totalIncomeTax / 12;
    }
    
    private void calculateNewTds() {
        this.newTds = this.newTotalIncomeTax / 12;
    }


}
