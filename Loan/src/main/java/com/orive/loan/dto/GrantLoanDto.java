package com.orive.loan.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

import com.orive.loan.Enum.Status;

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
public class GrantLoanDto {
	
	private String grantLoanId;
	private String employeeName;
	private String username;
	private String permittedBy;
	private String loanDetails;
	private LocalDate approveDate;
	private LocalDate repaymentForm;
	private double amount;
	private double interestPersentage;
	private double installmentPeriod;
	private double repaymentTotal;
	private double installment;
	private String accountType;
	private int installmentCleared;
	private double totalPaymentCleared;
	private String status;
}
