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
public class JobInterviewDto {

    private Long jobInterviewId;
	private String jobTitle;
	private String interviewDate;
	private String candidates;
	private String placeOfInterview;
	private String interviewTime;
	private String employeeName;
	private String jobInterviewDescription;
}
