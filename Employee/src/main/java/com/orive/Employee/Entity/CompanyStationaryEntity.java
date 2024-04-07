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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "company_stationary")
public class CompanyStationaryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CompanyStationaryId;
	
	@Column(name = "item_name")
	@Convert(converter = AesEncryptor.class)
	private String itemName;
	
	@Column(name = "model_number")
	@Convert(converter = AesEncryptor.class)
	private Long modelNumber;
	
	@Column(name = "model_name")
	@Convert(converter = AesEncryptor.class)
	private String modelName;
	
	@Column(name = "employee_name")
	@Convert(converter = AesEncryptor.class)
	private String employeeName;
	
	@Column(name = "employee_role")
	@Convert(converter = AesEncryptor.class)
	private String employeeRole;
	
	@Column(name = "assign_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate assignDate;
	
	@Column(name = "return_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate returnDate;
	
	@Column(name = "item_cost")
	@Convert(converter = AesEncryptor.class)
	private double itemCost;
}
