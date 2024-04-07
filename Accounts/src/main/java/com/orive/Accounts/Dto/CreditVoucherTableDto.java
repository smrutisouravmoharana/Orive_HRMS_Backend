package com.orive.Accounts.Dto;

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
public class CreditVoucherTableDto {

	private Long creditVoucherTableId;
	private String accountName;
	private String subType;
	private String ledgerComment;
	private double amount;
}
