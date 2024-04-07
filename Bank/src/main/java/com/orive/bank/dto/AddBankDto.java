package com.orive.bank.dto;

import com.orive.bank.Enum.Status;
import com.orive.bank.entities.AddBankEntity;

import jakarta.persistence.Column;
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
public class AddBankDto {
	
	private String addBankId;
	private String bankName;
	private String accountName;
	//private String phoneNumber;
	private Long accountNumber;
	private String branchName;
	private String accountType;
	private String status;
}
