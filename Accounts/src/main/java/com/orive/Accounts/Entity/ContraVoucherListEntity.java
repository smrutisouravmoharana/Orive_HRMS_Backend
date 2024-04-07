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
@Table(name = "contra_voucher_list")
public class ContraVoucherListEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contraVoucherListId;
	
	@Column(name = "account_name")
	@Convert(converter = AesEncryptor.class)
	private String accountName;
	
	@Column(name = "ledger_comment")
	@Convert(converter = AesEncryptor.class)
	private String ledgerComment;
	
	@Column(name = "debit")
	@Convert(converter = AesEncryptor.class)
	private double debit;
	
	@Column(name = "credit")
	@Convert(converter = AesEncryptor.class)
	private double credit;
}