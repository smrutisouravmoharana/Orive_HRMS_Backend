package com.orive.Employee.Dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

import com.orive.Employee.Entity.CompanyStationaryEntity;

import jakarta.persistence.Column;
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
public class CompanyStationaryDto {

    private Long CompanyStationaryId;
	private String itemName;
	private Long modelNumber;
	private String modelName;
	private String employeeName;
	private String employeeRole;
	private LocalDate assignDate;
	private LocalDate returnDate;
	private double itemCost;
}
