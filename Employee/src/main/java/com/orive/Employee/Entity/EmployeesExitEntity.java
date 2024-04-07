package com.orive.Employee.Entity;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

import com.orive.Employee.Configuration.AesEncryptor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Table(name = "employees_exit")
public class EmployeesExitEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long employeeExitId;
	
	@Column(name = "employee_name")
	@Convert(converter = AesEncryptor.class)
	private String employeeName;
	
	@Column(name = "username")
	@Convert(converter = AesEncryptor.class)
	private String username;
	
	@Column(name = "exit_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate exitDate;
	
	@Column(name = "type_of_exit")
	@Convert(converter = AesEncryptor.class)
	private String typeOfExit;
	
	@Column(name = "exit_interview")
	@Convert(converter = AesEncryptor.class)
	private String exitInterview;
	
	@Column(name = "inactivate_employee_account")
	@Convert(converter = AesEncryptor.class)
	private String inactivateEmployeeAccount;
	
	@Column(name = "description")
	@Convert(converter = AesEncryptor.class)
	private String description;
}