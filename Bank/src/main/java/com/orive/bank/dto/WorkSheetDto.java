package com.orive.bank.dto;

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
public class WorkSheetDto {

    private String workSheetId;
	private String workSheetTitle;
	private LocalDate startDate;
	private LocalDate endDate;
	private double estimateHour;
	private String projectName;
	private String employeeName;
	private String username;
	private String assignedTo;
	private String description;
	private String taskName;
	private String challangePart;
	private String workProgress;
	private LocalDate createdDate;
	private String status;
}
