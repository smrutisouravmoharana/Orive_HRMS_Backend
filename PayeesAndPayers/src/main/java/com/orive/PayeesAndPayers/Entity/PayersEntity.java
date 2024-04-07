package com.orive.PayeesAndPayers.Entity;



import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "payers")
public class PayersEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long payersId;
	
	@Column(name = "employee_full_name")
	private String employeeFullName;
	
	@Column(name = "employee_id")
	private Long employeeId;
	
	@Column(name = "employee_email_address")
	private String employeeEmailAddress;
	
	@Column(name = "payers_account_number")
	private String payersAccountNumber;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "payment_type")
	private String paymentType;
	
	@Column(name = "payer_account")
	private String payerAccount;
	
	@Column(name = "payer_wallet_address")
	private String payerWalletAddress;
	
	@Column(name = "transaction_id")
	private String transactionId;
	
	@Column(name = "transaction_date")
	private LocalDate transactionDate;
	
	@Column(name = "transaction_status")
	private String transactionStatus;
	
	@Column(name = "purpose_of_payment")
	private String purposeOfPayment;
	
	@Column(name = "transaction_notes")
	private String transactionNotes;
	
	@Column(name = "security_token")
	private String securityToken;
	
	@Column(name = "two_factor_authentication")
	private String twoFactorAuthentication;
}

