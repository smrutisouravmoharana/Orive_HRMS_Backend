package com.orive.Employee.Entity;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "transfers")
public class TransfersEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transferId;
	
	@Column(name = "employee_name")
	@Convert(converter = AesEncryptor.class)
	private String employeeName;
	
	@Column(name = "username")
	@Convert(converter = AesEncryptor.class)
	private String username;
	
	@Column(name = "email")
	@Convert(converter = AesEncryptor.class)
	private String email;
	
	@Column(name = "transfer_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate transferDate;
	
	@Column(name = "department_name")
	@Convert(converter = AesEncryptor.class)
	private String departmentName;
	
	@Column(name = "location_name")
	@Convert(converter = AesEncryptor.class)
	private String locationName;
	
	@Column(name = "description")
	@Convert(converter = AesEncryptor.class)
	private String description;
}
