package com.orive.Organisation.Entity;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.orive.Organisation.Config.AesEncryptor;

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
@Table(name = "expencelist")
public class ExpenseListEntity {	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long expenceListId;
	
	//@Convert(converter = AesEncryptor.class)
	@Column(name = "expence_id")
	private Long expenceId;
	
	@Column(name = "purchase_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate purchaseDate;
	
	@Column(name = "description")
	@Convert(converter = AesEncryptor.class)
	private String description;
	
	@Column(name = "purchased_by")
	@Convert(converter = AesEncryptor.class)
	private String purchasedBy;
	
	@Column(name = "amount")
	@Convert(converter = AesEncryptor.class)
	private double amount;	
}
