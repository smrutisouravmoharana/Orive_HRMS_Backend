package com.orive.Employee.Dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
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
public class TransfersDto {

	private Long transferId;
	private String employeeName;
	private String username;
	private String email;
	private LocalDate transferDate;
	private String departmentName;
	private String locationName;
	private String description;
}
