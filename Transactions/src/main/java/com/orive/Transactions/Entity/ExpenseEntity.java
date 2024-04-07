package com.orive.Transactions.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "expense")
public class ExpenseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long expenseId;
	
	@Column(name = "employee_full_name")
	private String employeeFullName;
	
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "employee_position")
	private String employeePosition;
	
	@Column(name = "date_of_expense_report")
	private String dateOfExpenseReport;
	
	@Column(name = "expense_date")
	private String expenseDate;
	
	@Column(name = "expense_category")
	private String expenseCategory;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "receipt_attached")
	private String receiptAttached;
	
	@Column(name = "total_expense")
	private double totalExpense;
	
	@Column(name = "payment_method")
	private String paymentMethod;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "account_holder_name")
	private String accountHolderName;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "routing_number")
	private String routingNumber;
	
	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "supervisor_approval")
	private String supervisorApproval;
	
	@Column(name = "finance_approval")
	private String financeApproval;
	
	@Column(name = "date")
	private String date;
}
