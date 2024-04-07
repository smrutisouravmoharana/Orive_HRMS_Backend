package com.orive.Employee.Dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.orive.Employee.Enum.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
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
public class EmployeesDto {
	
    private Long employeeId;
	private String employeeName;
	private String username;
	private String designationName;
	private String email;
	private Long phone;
	private Long alternativePhone;
	private String country;
	private String city;
	private int zipCode;
	private String employeeRole;
	private String companyType;
	private String attendanceTime;
	private String employeeType;
	private LocalDate createdDate;
	private Long accountNumber;
	private String bankName;
	private String departmentName;
	private String ifscNumber;
	private String branchName;
	private double basicSalary;
	private double transportAllowance;
	private double grossSalary;
	private Long tinNumber;
	private double hraAllowances;
	private double otherAllowances;
	private double pfAllowances;
	private double daAllowances;
	private double medicalAllowances;
	private double otherInsurance;
	private double tax;
	private String subDepartment;
	private String position;
	private String dutyType;
	private LocalDate hireDate;
	private LocalDate joiningDate;
	private String rateType;
	private int rateNumber;
	private int monthlyWorkHours;
	private String payFrequency;
	private String medical;
	private String family;
	private String transportation;
	private String others;
	private String teamLeaderName;
	private String reportingTo;
	private LocalDate dateOfBirth;
	private String gender;
	private String maritalStatus;
	private String workInCity;
	private String cityOfResidence;
	private String workPermit;
	private byte[] uploadPhoto;
	private String businessEmail;
	private Long homePhone;
	private Long cellPhone;
	private String userEmailOrName;
	private String password;
	private byte[] uploadDocument;
	private String status;
}
