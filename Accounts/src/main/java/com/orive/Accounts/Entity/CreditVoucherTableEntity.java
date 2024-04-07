package com.orive.Accounts.Entity;

import com.orive.Accounts.Config.AesEncryptor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "credit_voucher_table" )
public class CreditVoucherTableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long creditVoucherTableId;
	
	@Column(name = "account_name")
	@Convert(converter = AesEncryptor.class)
	private String accountName;
	
	@Column(name = "sub_type")
	@Convert(converter = AesEncryptor.class)
	private String subType;
	
	@Column(name = "ledger_comment")
	@Convert(converter = AesEncryptor.class)
	private String ledgerComment;
	
	@Column(name = "amount")
	@Convert(converter = AesEncryptor.class)
	private double amount;
}