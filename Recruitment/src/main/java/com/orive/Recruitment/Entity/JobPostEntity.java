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
@Table(name = "job_post")
public class JobPostEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobPostId;
	
	@Column(name = "job_title")
	private String jobTitle;
	
	@Column(name = "job_type")
	private String jobType;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "no_of_positions")
	private double noOfPositions;
	
	@Column(name = "date_of_closing")
	private String dateOfClosing;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "minimun_experience")
	private String minimunExperience;
	
	@Column(name = "short_description")
	private String shortDescription;
	
	@Column(name = "long_description")
	private String longDescription;
}
