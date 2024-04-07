package com.orive.Accounts.Dto;

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
public class JournalVoucherTableDto {

    private Long journalVoucherTableId;
	private String accountName;
	private String subType;
	private String ledgerComment;
	private double debit;
	private double credit;
	private String reverseAccountHead;
}
