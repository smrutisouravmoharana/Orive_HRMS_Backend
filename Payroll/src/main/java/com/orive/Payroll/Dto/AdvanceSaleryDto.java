package com.orive.Payroll.Dto;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
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
public class AdvanceSaleryDto {

	private String advanceSaleryId;
	private LocalDate createdDate;
	private String employeeName;
	private double salary;
	private double advanceAmount;
	private double salaryDue;
	private LocalDate monthAndYear;
}
