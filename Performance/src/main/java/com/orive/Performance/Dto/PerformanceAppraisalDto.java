package com.orive.Performance.Dto;

import java.time.LocalDate;

import com.orive.Performance.Config.AesEncryptor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class PerformanceAppraisalDto {
	
	private String performanceAppraisalId;
	private String employeeName;
	private String username;
	private String departmentName;
	private String position;
	private LocalDate appraisalPeriod;
	private double qualityOfWorkRating;
	private String qualityOfWorkComments;
	private double qualityOfWorkScore;
	private double jobKnowledgeRating;
	private String jobKnowledgeComments;
	private double jobKnowledgeScore;
	private double communicationSkillsRating;
//	private String communicationSkillsComments;
	private double communicationSkillsScore;
	private double teamworkAndCollaborationRating;
	private String teamworkAndCollaborationComments;
	private double teamworkAndCollaborationScore;
	private double initiativeAndCreativityRating;
	private String initiativeAndCreativityComments;
	private double initiativeAndCreativityScore;
	private double punctualityAndAttendanceRating;
	private String punctualityAndAttendanceComments;
	private double punctualityAndAttendanceScore;
	private double adaptabilityRating;
	private String adaptabilityComments;
	private double adaptabilityScore;
	private double overallRating;
	private String overallComments;
	private double overallScore;
	private String areasForImprovement;
	private String employeesSelfAssessment;
	private String goalsAchieved;
	private String developmentPlan;
	private String managersComments;	
}
