package com.orive.Accounts.Entity;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import com.orive.Accounts.Config.AesEncryptor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "opening_balance")
public class OpeningBalanceEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long openingBalanceId;
	
	@Column(name = "financial_year")
	@Convert(converter = AesEncryptor.class)
	private String financialYear;
	
	@Column(name = "date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate date;
	
	@OneToMany(targetEntity = OpeningBalanceTableEntity.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "openingbalance_table_fk",referencedColumnName = "openingBalanceId")
	private List<OpeningBalanceTableEntity> openingBalanceTableEntities;
	
	@Column(name = "total_debit")
	@Convert(converter = AesEncryptor.class)
	private double totalDebit;
	
	@Column(name = "total_credit")
	@Convert(converter = AesEncryptor.class)
	private double totalCredit;
}
