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
@Table(name = "deposit")
public class DepositEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long depositId;
	
	@Column(name = "employee_full_name")
	private String employeeFullName;
	
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "employee_position")
	private String employeePosition;
	
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
	
	@Column(name = "deposit_amount")
	private double depositAmount;
	
	@Column(name = "date_of_deposit")
	private String dateOfDeposit;
	
	@Column(name = "purpose_of_deposit")
	private String purposeOfDeposit;
	
	@Column(name = "deposit_type")
	private String depositType;
	
	@Column(name = "other_deposit_amount")
	private double otherDepositAmount;
	
	@Column(name = "other_date_of_deposit")
	private String otherDateOfDeposit;
	
	@Column(name = "other_purpose_of_deposit")
	private String otherPurposeOfDeposit;
	
	@Column(name = "withdrawal_amount")
	private double withdrawalAmount;
	
	@Column(name = "date_of_return")
	private String dateOfReturn;
	
	@Column(name = "reason_of_return")
	private String reasonOfReturn;
	
	@Column(name = "comments")
	private String comments;
}
