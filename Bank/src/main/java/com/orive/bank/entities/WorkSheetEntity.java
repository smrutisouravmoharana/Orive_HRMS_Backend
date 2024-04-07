package com.orive.bank.entities;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

import com.orive.bank.configuration.AesEncryptor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "worksheet")
public class WorkSheetEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence")
    @GenericGenerator(name = "custom-sequence", strategy = "com.orive.bank.entities.WorkSheetCustomIdGenerator")
	private String workSheetId;
	
	@Column(name = "work_sheet_title")
	@Convert(converter = AesEncryptor.class)
	private String workSheetTitle;
	
	@Column(name = "start_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate startDate;
	
	@Column(name = "end_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate endDate;
	
	@Column(name = "estimate_hour")
	@Convert(converter = AesEncryptor.class)
	private double estimateHour;
	
	@Column(name = "project_name")
	@Convert(converter = AesEncryptor.class)
	private String projectName;
	
	@Column(name = "employee_name")
	@Convert(converter = AesEncryptor.class)
	private String employeeName;
	
	@Column(name = "username")
	@Convert(converter = AesEncryptor.class)
	private String username;
	
	@Column(name = "assigned_to")
	@Convert(converter = AesEncryptor.class)
	private String assignedTo;
	
	@Column(name = "description")
	@Convert(converter = AesEncryptor.class)
	private String description;
	
	@Column(name = "task_name")
	@Convert(converter = AesEncryptor.class)
	private String taskName;
	
	@Column(name = "challange_part")
	@Convert(converter = AesEncryptor.class)
	private String challangePart;
	
	@Column(name = "work_progress")
	@Convert(converter = AesEncryptor.class)
	private String workProgress;
	
	@Column(name = "created_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate createdDate;

	@Column(name="status")
	private String status;
	
//	@Enumerated(EnumType.STRING)
//	@Column(name="status")
//	private Status status;
}
