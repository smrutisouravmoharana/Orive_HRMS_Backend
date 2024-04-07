package com.orive.Recruitment.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobPostDto {

    private Long jobPostId;
	private String jobTitle;
	private String jobType;
	private String designation;
	private String status;
	private double noOfPositions;
	private String dateOfClosing;
	private String gender;
	private String minimunExperience;
	private String shortDescription;
	private String longDescription;
}
