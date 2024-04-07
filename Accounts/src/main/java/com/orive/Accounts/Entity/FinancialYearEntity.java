package com.orive.Accounts.Entity;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import com.orive.Accounts.Config.AesEncryptor;

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
@Table(name = "financial_year")
public class FinancialYearEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long financialYearId;
	
	@Column(name = "financial_year")
	@Convert(converter = AesEncryptor.class)
	private String financialYear;
	
	@Column(name = "financial_year_start_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate financialYearStartDate;
	
	@Column(name = "financial_year_end_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate financialYearEndDate;
}
