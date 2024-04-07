package com.orive.Recruitment.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "job_interview")
public class JobInterviewEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobInterviewId;
	
	@Column(name = "job_title")
	private String jobTitle;
	
	@Column(name = "interview_date")
	private String interviewDate;
	
	@Column(name = "candidates")
	private String candidates;
	
	@Column(name = "place_of_interview")
	private String placeOfInterview;
	
	@Column(name = "interview_time")
	private String interviewTime;
	
	@Column(name = "employee_name")
	private String employeeName;
	
	@Column(name = "job_interview_description")
	private String jobInterviewDescription;
}
