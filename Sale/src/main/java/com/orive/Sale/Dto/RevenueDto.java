package com.orive.Sale.Dto;

import java.time.LocalDate;
import java.util.List;

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
public class RevenueDto {

    private Long revenueId;
	private Double productPricePerUnit;
	private Double noOfClients;
	private Double grossRevenue;
	private LocalDate saleDate;
	private Double returns;
	private Double discounts;
	private Double allowances;
	private Double netRevenue;
	private String moneyAddedBankName;
	private String status;
	private String productName;
	
}

//package com.orive.Sale.Dto;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Builder
//public class RevenueDto {
//	
//	private Long revenueId;
//	private Double productPricePerUnit;
//	private Double noOfClients;
//	private Double grossRevenue;
//	private LocalDate saleDate;
//	private Double returns;
//	private Double discounts;
//	private Double allowances;
//	private Double netRevenue;
//	private String moneyAddedBankName;
//	private String moneyAddedBankNumber;
//	private String moneyAddedBankIfscCode;
//	private String status;
//	private String billtoAddress;
//	private String billtoGstin;
//	private String billtostate;
//	private String shiptoAddress;
//	private String placeOfSupply;
//	private String invoiceNumber;
//	private LocalDate date;
//	private String time;
//	private String productName;
//	private String hsnOrSac;
//	private String unit;
//	private String gstInPercentage;
//	private Double gstPrice;
//	private Double totalCost;
//	private Double sgst;
//	private Double cgst;
//	private Double igst;
//	private Double sgstInPercentage;
//	private Double cgstInPercentage;
//	private Double igstInPercentage;
//	private Double receivedAmount;
//	private Double balanceAmount;
//	private String invoiceAmountInWords;
//	private String paymentMode;
//	private Double grandTotal;
//	
//}
