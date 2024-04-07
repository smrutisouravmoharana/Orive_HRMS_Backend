package com.orive.Performance.Dto;


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
public class SelfAssessmentDto {

    private String selfAssessmentId;
	private double performanceGoalRating;
	private String performanceGoalComments;
	private double newSkillsRating;
	private String newSkillsComments;
	private double collaborationRating;
	private String collaborationComments;
	private double managedYourTimeRating;
	private String managedYourTimeComments;
	private double improvedCommunicationSkillRating;
	private String improvedCommunicationSkillComments;
}
