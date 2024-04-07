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
public class WarningsDto {
	
	private Long warningsId;
	private String employeeName;
	private String username;
	private String warningType;
	private String subject;
	private String warningByEmployee;
	private LocalDate warningDate;
	private String description;

}