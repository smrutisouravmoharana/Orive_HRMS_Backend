package com.orive.Accounts.Dto;

import com.orive.Accounts.Entity.ContraVoucherEntity;

import jakarta.persistence.Column;
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
public class ContraVoucherListDto {
		
    private Long contraVoucherListId;
	private String accountName;
	private String ledgerComment;
	private double debit;
	private double credit;
}

