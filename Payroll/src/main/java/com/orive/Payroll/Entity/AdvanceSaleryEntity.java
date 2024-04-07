package com.orive.Payroll.Entity;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.orive.Payroll.Config.AesEncryptor;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "advance_salery")
public class AdvanceSaleryEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence")
    @GenericGenerator(name = "custom-sequence", strategy = "com.orive.Payroll.Entity.AdvanceSaleryCustomIdGenerator")
	private String advanceSaleryId;
	
	@Column(name = "created_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate createdDate;
	
	@Column(name = "employee_name")
	@Convert(converter = AesEncryptor.class)
	private String employeeName;
	
	@Column(name = "salary")
	@Convert(converter = AesEncryptor.class)
	private double salary;
	
	@Column(name = "advance_amount")
	@Convert(converter = AesEncryptor.class)
	private double advanceAmount;
	
	@Column(name = "salary_due")
	@Convert(converter = AesEncryptor.class)
	private double salaryDue;
	
	@Column(name = "month_and_year")
	@Convert(converter = AesEncryptor.class)
	private LocalDate monthAndYear;
}
