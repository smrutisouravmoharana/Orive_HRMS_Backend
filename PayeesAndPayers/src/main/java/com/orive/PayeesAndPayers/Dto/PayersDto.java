package com.orive.PayeesAndPayers.Dto;

import java.time.LocalDate;

import org.modelmapper.internal.bytebuddy.asm.Advice.Local;

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
public class PayersDto {
	
    private Long payersId;
	private String employeeFullName;
	private Long employeeId;
	private String employeeEmailAddress;
	private String payersAccountNumber;
	private double amount;
	private String currency;
	private String paymentType;
	private String payerAccount;
	private String payerWalletAddress;
	private String transactionId;
	private LocalDate transactionDate;
	private String transactionStatus;
	private String purposeOfPayment;
	private String transactionNotes;
	private String securityToken;
	private String twoFactorAuthentication;
}
