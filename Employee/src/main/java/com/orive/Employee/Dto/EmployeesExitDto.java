package com.orive.Employee.Dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesExitDto {
	
    private Long employeeExitId;
    private String employeeName;
    private String username;
	private LocalDate exitDate;
	private String typeOfExit;
	private String exitInterview;
	private String inactivateEmployeeAccount;
	private String description;

}