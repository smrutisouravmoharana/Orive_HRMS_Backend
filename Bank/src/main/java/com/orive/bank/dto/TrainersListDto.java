package com.orive.bank.dto;



import jakarta.persistence.Column;
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
public class TrainersListDto {
	
	private String trainersListId;
	private String trainersFullName;
	private String emailAddress;
	private Long phoneNo;
	private String technicalSkills;
	private String softSkills;
	private String industries;
	private String certifications;
	private String trainingProgramsOffered;
	private String preferredTrainingAudienece;
	private String trainingLanguages;
	private String availability;
	private String previousClients;
	private String trainingMaterialsProvided;
	private String additionalNotes;
	private String status;
}
