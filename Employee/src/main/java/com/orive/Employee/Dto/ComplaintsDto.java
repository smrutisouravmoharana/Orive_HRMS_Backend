package com.orive.Employee.Dto;

import java.time.LocalDate;

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
public class ComplaintsDto {
		
	    private Long complaintsId;
	    private String employeeName;
		private String username;
		private String complaintTitle;
		private LocalDate complaintDate;
		private String complaintAgainst;
		private String description;

}