package com.orive.Accounts.Dto;

import com.orive.Accounts.Enum.Status;

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
public class AccountBalanceDto {
	
	private Long accountBalancesId;
	private String employeeName;
	private String username;
	private String departmentName;
	private String position;
	private double hsaBalance;
	private double fsaBalance;
	private double retirementAccountBalance;
	private double otherBenefitsAccountsBalance;
	private double expenseReimbursementAccountBalance;
	private String details;
	private int vacationDaysBalance;
	private int sickDaysBalance;
	private int personalDaysBalance;
	private int floatingHolidaysBalance;
	private String accountType;
	private double accountBalance;
	private String purpose;
	private String comments;
	private String status;
}
