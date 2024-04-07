package com.orive.project.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.orive.project.Configruration.AesEncryptor;
import com.orive.project.Enum.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
@Entity
@Table(name = "projects")
public class ProjectEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence")
	private Long projectsId;
	
	@Column(name = "project_title")
	@Convert(converter = AesEncryptor.class)
	private String projectTitle;
	
	@Column(name = "project_name")
	@Convert(converter = AesEncryptor.class)
	private String projectName;
	
	@Column(name = "manager_employee_id")
	@Convert(converter = AesEncryptor.class)
	private Long managerEmployeeId;
	
	@Column(name = "client_name")
	@Convert(converter = AesEncryptor.class)
	private String clientName;
	
	@Column(name = "company_name")
	@Convert(converter = AesEncryptor.class)
	private String companyName;
	
	@Column(name = "start_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate startDate;
	
	@Column(name = "end_date" )
	@Convert(converter = AesEncryptor.class)
	private LocalDate endDate;
	
	@Column(name = "priority")
	@Convert(converter = AesEncryptor.class)
	private String priority;
	
	@Column(name = "budget")
	@Convert(converter = AesEncryptor.class)
	private double budget;
	
	@Column(name = "project_managers")
	@Convert(converter = AesEncryptor.class)
	private String projectManagers;
	
	@Column(name = "summary", length = 2000)
	@Convert(converter = AesEncryptor.class)
	private String summary;
	
	@Column(name = "description", length = 2000)
	@Convert(converter = AesEncryptor.class)
	private String description;
	
	@Column(name = "work_update_sheet")
	@Convert(converter = AesEncryptor.class)
	private String workUpdateSheet;
	
	@Column(name = "status")
	private String status;
	
//	@Enumerated(EnumType.STRING)
//	@Column(name = "status")
//	private Status status;
	
	@OneToMany(targetEntity = EmployeeProjectManagementEntity.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "projects_employeeProjectManagement_fk",referencedColumnName = "projectsId")
    private List<EmployeeProjectManagementEntity> employeeProjectManagementEntities;
}
