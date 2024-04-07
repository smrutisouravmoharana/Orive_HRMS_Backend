package com.orive.Transactions.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepositDto {
	
    private Long depositId;
	private String employeeFullName;
	private String employeeId;
	private String department;
	private String employeePosition;
	private String bankName;
	private String accountHolderName;
	private String accountNumber;
	private String routingNumber;
	private String accountType;
	private double depositAmount;
	private String dateOfDeposit;
	private String purposeOfDeposit;
	private String depositType;
	private double otherDepositAmount;
	private String otherDateOfDeposit;
	private String otherPurposeOfDeposit;
	private double withdrawalAmount;
	private String dateOfReturn;
	private String reasonOfReturn;
	private String comments;

}
