package com.orive.Organisation.Dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

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
public class AnnoucementDto {

	private Long announcementsId;
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	private String companyName;
	private String locationName;
	private String departmentName;
	private String summary;
	private String description;
	private LocalDate createdDate;
//	private String status;
//	private String approvedBy;
}
