package com.orive.Payroll.Entity;


import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

import com.orive.Payroll.Config.AesEncryptor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Builder
@ToString
@Entity
@Table(name = "pay_slip_generate")
public class PaySlipGenerateEntity {
	
	    @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence")
        @GenericGenerator(name = "custom-sequence", strategy = "com.orive.Payroll.Entity.PaySlipGenerateCustomIdGenerator")
	    private String paySlipGenerateId;
    
	    @Column(name = "username")
	    private String username;
	    
	    @Column(name = "employee_name")
	    private String employeeName;
	    
	    @Column(name = "designation")
	    private String designation;
	    
	    @Column(name = "department")
	    private String department;
	    
	    @Column(name = "working_days")
	    private Double workingDays;
	    
	    @Column(name = "present_days")
	    private Double presentDays;
	    
	    @Column(name = "present_basic_salary")
	    private Double presentBasicSalary;
	    
	    @Column(name = "over_time_in_hrs")
	    private Double overTimeInHrs;
	    
	    @Column(name = "over_time_salary")
	    private Double overTimeSalary;
	    
	    @Column(name = "basic_salary")
	    private Double basicSalary;
	    
	    @Column(name = "houserent_allowance")
	    private Double houserentAllowance;
	    
	    @Column(name = "conveyance_allowance")
	    private Double conveyanceAllowance;
	    
	    @Column(name = "medical_allowance")
	    private Double medicalAllowance;
	    
	    @Column(name = "educational_allowance")
	    private Double educationalAllowance;
	    
	    @Column(name = "special_allowance")
	    private Double specialAllowance;
	    
	    @Column(name = "other_allowance")
	    private Double otherAllowance;
	    
	    @Column(name = "travelling_allowance")
	    private Double travellingAllowance;
	    
	    @Column(name = "dearness_allowance")
	    private Double dearnessAllowance;
	    
	    @Column(name = "gross_salary")
	    private Double grossSalary;
	    
	    @Column(name = "employee_deduction_provident_fund")
	    private Double employeeDeductionProvidentFund;
	    
	    @Column(name = "employee_deduction_esic")
	    private Double employeeDeductionEsic;

	    @Column(name = "employee_deduction_professional_tax")
	    private Double employeeDeductionProfessionalTax;
	    
	    @Column(name = "tds")
	    private Double tds;
	    
	    @Column(name = "net_salary")
	    private Double netSalary;
	    
	    @Column(name = "employeer_contribution_provident_fund")
	    private Double employeerContributionProvidentFund;
	    
	    @Column(name = "employeer_contribution_esic")
	    private Double employeerContributionEsic;
	    
	    @Column(name = "gratuity")
	    private Double gratuity;
	    
	    @Column(name = "gratuity_year")
	    private Double gratuityYear;

	    @Column(name = "employeer_contribution_variable_pay")
	    private Double employeerContributionVariablePay;
	    
	    @Column(name = "no_of_children")
	    private Double noOfChildren;

	    @Column(name = "company_preferred_educational_allowance")
	    private Double companyPreferredEducationalAllowance;

	    @Column(name = "bonus")
	    private Double bonus;
	    
	    @Column(name = "payroll_template")
	    private String payrollTemplate;

	    @Column(name = "state")
	    private String state;

	    @Column(name = "created_date")
	    private LocalDate createdDate;

	    // This method will be called before the entity is persisted
	    @PrePersist
	    public void calculateAllowances() {
	    	calculatePresentBasicSalary();
	    	calculateOverTimeSalary();
	        calculateHouserentAllowance();
	        calculateEducationalAllowance();
	        calculateGrossSalary();
	        calculateDeductions();
	        calculateEmployerContributionProvidentFund();
	        calculateEmployeeContributionEsic();
	        calculateGratuity();
	        calculateBonus();
	        
	        
	        
	    }

	    @PreUpdate
	    public void updateAllowances() {
	        // You can add logic here if you want to update allowances before an update
	    }
	    
	    //Calculate Total Present Basic Salary
	    private void calculatePresentBasicSalary() {
	        if (this.basicSalary != null && this.workingDays != null && this.presentDays != null) {
	            this.presentBasicSalary = (this.basicSalary / this.workingDays) * this.presentDays;
	        } else {
	            throw new IllegalStateException("BasicSalary, workingDays, and presentDays must be set for presentBasicSalary calculation");
	        }
	    }
	    
	 // Method to calculate overTimeSalary
	    private void calculateOverTimeSalary() {
	        if (this.basicSalary != null && this.overTimeInHrs != null) {
	            // Assuming overTimeSalary is calculated as ((basicSalary/26)/8)*overTimeInHrs
	            this.overTimeSalary = ((this.basicSalary / 26) / 8) * this.overTimeInHrs;
	        } else {
	            throw new IllegalStateException("BasicSalary and overTimeInHrs must be set for overTimeSalary calculation");
	        }
	    }
	    
	

	    // Calculate HouseRent Allowance
	    private void calculateHouserentAllowance() {
	        if (this.presentBasicSalary != null) {
	            this.houserentAllowance = this.presentBasicSalary * 0.4;
	        } else {
	            throw new IllegalStateException("Basic Salary cannot be null for houserent allowance calculation");
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
	    
	    // Calculate Gross Salary
	    private void calculateGrossSalary() {
	        this.grossSalary = this.presentBasicSalary
	                + (houserentAllowance != null ? houserentAllowance : 0)
	                + (conveyanceAllowance != null ? conveyanceAllowance : 0)
	                + (medicalAllowance != null ? medicalAllowance : 0)
	                + (educationalAllowance != null ? educationalAllowance : 0)
	                + (specialAllowance != null ? specialAllowance : 0)
	                + (otherAllowance != null ? otherAllowance : 0)
	                + (dearnessAllowance != null ? dearnessAllowance : 0)
	                + (travellingAllowance != null ? travellingAllowance : 0)
	                + (overTimeSalary != null ? overTimeSalary : 0);
	    }
	    

	    // Calculate Employee Provident Fund
	    private void calculateDeductions() {
	        calculateEmployeeDeductionProvidentFund();
	        calculateEmployeeDeductionEsic();
	        calculateEmployeeDeductionProfessionalTax();
	        calculateNetSalary();
	       

	        // Add other deduction calculations here...
	    }

	    private void calculateEmployeeDeductionProvidentFund() {
	        if (this.presentBasicSalary != null) {
	            this.employeeDeductionProvidentFund = this.presentBasicSalary * 0.12;
	        } else {
	            throw new IllegalStateException("Basic Salary cannot be null for provident fund deduction calculation");
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
	    
	 

	    private void calculateEmployeeDeductionProfessionalTax() {
	        if ("ODISHA".equalsIgnoreCase(this.state) && this.grossSalary != null && this.grossSalary > 20000) {
	            this.employeeDeductionProfessionalTax = 200.0;
	        } else {
	            this.employeeDeductionProfessionalTax = 0.0; // No professional tax if conditions are not met
	        }
	    }
	    
	
	    
//	    // Calculate Total Yearly tds 
//	    private void calculateTotalYearTds() {
//	        if (this.tds != null) {
//	            this.totalYearTds = this.tds * 12;
//	        } else {
//	            throw new IllegalStateException("tds cannot be null for total yearly tds calculation");
//	        }
//	    }
	    

	    private void calculateNetSalary() {
	        this.netSalary = this.grossSalary
	                - ((this.employeeDeductionProvidentFund != null ? this.employeeDeductionProvidentFund : 0)
	                + (this.employeeDeductionEsic != null ? this.employeeDeductionEsic : 0)
	                + (this.employeeDeductionProfessionalTax != null ? this.employeeDeductionProfessionalTax : 0)
	                + (this.tds != null ? this.tds : 0));
	    }
	    
	
	    
	    // Calculate Employer Contribution Provident Fund
	    private void calculateEmployerContributionProvidentFund() {
	        if (this.presentBasicSalary != null) {
	            this.employeerContributionProvidentFund = this.presentBasicSalary * 0.13;
	        } else {
	            throw new IllegalStateException("Basic Salary cannot be null for employer contribution to provident fund calculation");
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
	    
	
	    
	 // Calculate Gratuity
	    private void calculateGratuity() {
	        if (this.presentBasicSalary != null && this.gratuityYear != null) {
	            this.gratuity = ((this.presentBasicSalary / 26) * 15 * this.gratuityYear) / 12;
	        } else {
	            throw new IllegalStateException(
	                    "Both basicSalary and gratuityYear must be set for gratuity calculation");
	        }
	    }
	    
	 
	    
	    // Calculate Bonus
	    private void calculateBonus() {
	        if (this.presentBasicSalary != null) {
	            this.bonus = this.presentBasicSalary * 0.0833;
	        } else {
	            throw new IllegalStateException("Basic Salary cannot be null for bonus calculation");
	        }
	    }
	        
	   
	}