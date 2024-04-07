package com.orive.TimeSheet.Dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
public class AttendanceDto {
 	
	
	private String attendanceId;
	private String officeClockIn;
	private String officeClockOut;
	private String employeeName;
	private String username;
	private String clockIn;
	private String clockOut;
	private String late;
	private String earlyLeaving;
	private String overTime;
	private String totalWork;
	private String totalRest;
	private LocalDate date;
	private String clockInLocation;
	private String clockOutLocation;
//	private byte[] uploadDoc;
}