package com.orive.Employee.Entity;

import java.time.LocalDate;

import com.orive.Employee.Configuration.AesEncryptor;

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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "warnings")
public class WarningsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long warningsId;
	
	@Column(name = "employee_name")
	@Convert(converter = AesEncryptor.class)
	private String employeeName;
	
	@Column(name = "username")
	@Convert(converter = AesEncryptor.class)
	private String username;
	
	@Column(name = "warning_type")
	@Convert(converter = AesEncryptor.class)
	private String warningType;
	
	@Column(name = "subject")
	@Convert(converter = AesEncryptor.class)
	private String subject;
	
	@Column(name = "warning_by_employee")
	@Convert(converter = AesEncryptor.class)
	private String warningByEmployee;
	
	@Column(name = "warning_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate warningDate;
	
	@Column(name = "description", length = 3000)
	@Convert(converter = AesEncryptor.class)
	private String description;
}