package com.orive.Sale.Entity;

import org.hibernate.annotations.GenericGenerator;

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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "client")
public class ClientEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence")
    @GenericGenerator(name = "custom-sequence", strategy = "com.orive.Sale.Entity.ClientCustomIdGenerator")
	private String clientId;
	
	@Column(name = "client_name")
	private String clientName;
	
	@Column(name = "client_contact_number")
	private Long clientContactNumber;
	
	@Column(name = "client_email_address")
	private String clientEmailAddress;
	
	@Column(name = "physical_address")
	private String physicalAddress;
	
	@Column(name = "relevant_contact_details")
	private String relevantContactDetails;
	
	@Column(name = "client_position")
	private String clientPosition;
	
	@Column(name = "product_details_purchased_by_client")
	private String productDetailsPurchasedByClient;
	
	@Column(name = "purchase_date")
	private String purchaseDate;
	
	@Column(name = "product_quantities")
	private String productQuantities;
	
	@Column(name = "preferences")
	private String preferences;
	
	@Column(name = "billing_address")
	private String billingAddress;
	
	@Column(name = "payment_method")
	private String paymentMethod;
	
	@Column(name = "billing_contact_information")
	private String billingContactInformation;
	
	@Column(name = "communication_history")
	private String communicationHistory;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "status")
	private String status;

}
