package com.orive.Organisation.Dto;

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
public class DesignationDto {

	private Long designationId;
	private String departmentName;
	private String designationName;
	private LocalDate createdDate;
//	private String status;
//	private String approvedBy;
}
