package com.orive.Sale.Dto;

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
public class ClientDto {

	private String clientId;
	private String clientName;
	private Long clientContactNumber;
	private String clientEmailAddress;
	private String physicalAddress;
	private String relevantContactDetails;
	private String clientPosition;
	private String productDetailsPurchasedByClient;
	private String purchaseDate;
	private String productQuantities;
	private String preferences;
	private String billingAddress;
	private String paymentMethod;
	private String billingContactInformation;
	private String communicationHistory;
	private String comments;
	private String status;
}
