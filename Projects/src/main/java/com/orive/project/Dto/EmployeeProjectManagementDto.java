package com.orive.project.Dto;

import java.time.LocalDate;
import java.util.List;

import com.orive.project.Entity.EmployeeProjectManagementEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
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
public class EmployeeProjectManagementDto {

	
    private Long employeeProjectManagementId;
    private String username;
	private String projectName;
	private String employeeName;
	private String taskAssignedFor;
	private String typeTheTaskHere;
   
}
