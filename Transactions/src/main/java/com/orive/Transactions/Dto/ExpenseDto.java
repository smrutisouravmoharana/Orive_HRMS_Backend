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
public class ExpenseDto {
	
    private Long expenseId;
	private String employeeFullName;
	private String employeeId;
	private String department;
	private String employeePosition;
	private String dateOfExpenseReport;
	private String expenseDate;
	private String expenseCategory;
	private String description;
	private double amount;
	private String receiptAttached;
	private double totalExpense;
	private String paymentMethod;
	private String bankName;
	private String accountHolderName;
	private String accountNumber;
	private String routingNumber;
	private String accountType;
	private String supervisorApproval;
	private String financeApproval;
	private String date;
}
