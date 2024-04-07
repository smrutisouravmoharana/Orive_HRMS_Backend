package com.orive.TimeSheet.Dto;

import java.time.LocalDate;

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
public class OfficeShiftsDto {

	private String officeShiftsId;
	private LocalDate createdDate;
	private String day;
	private String officeClockIn;
	private String officeClockOut;
}