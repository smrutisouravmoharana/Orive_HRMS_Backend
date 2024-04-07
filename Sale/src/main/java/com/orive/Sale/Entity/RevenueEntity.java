package com.orive.Sale.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@Table(name = "revenue")
public class RevenueEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long revenueId;
	
	@Column(name = "product_price_per_unit")
	private Double productPricePerUnit;
	
	@Column(name = "no_of_clients")
	private Double noOfClients;
	
	@Column(name = "gross_revenue")
	private Double grossRevenue;
	
	@Column(name = "sale_date")
	private LocalDate saleDate;
	
	@Column(name = "returns")
	private Double returns;
	
	@Column(name = "discounts")
	private Double discounts;
	
	@Column(name = "allowances")
	private Double allowances;
	
	@Column(name = "net_revenue")
	private Double netRevenue;
	
	@Column(name = "money_added_bank_name")
	private String moneyAddedBankName;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "bill_to")
	private String billTo;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "gstin")
	private String gstin;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "ship_to")
	private String shipTo;
	
	@Column(name = "place_of_supply")
	private String placeOfSupply;
	
	@Column(name = "invoice_no")
	private Long invoiceNo;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "local_time")
	private String localTime;
	
	@Column(name = "tax_type")
	private String taxType;
	
	@Column(name = "taxable_amount")
	private Double taxableAmount;
	
	@Column(name = "rate")
	private Double rate;
	
	@Column(name = "tax_amount")
	private Double taxAmount;
	
	@Column(name = "sub_total")
	private Double subTotal;
	
	@Column(name = "total")
	private Double total;
	
	@Column(name = "received")
	private Double received;
	
	@Column(name = "balance")
	private Double balance;
	
	@Column(name = "invoice_amount_in_words")
	private String invoiceAmountInWords;
	
	@Column(name = "payment_mode")
	private String paymentMode;
	
    @Column(name = "terms_and_conditions")
	private String termsAndConditions;
	
    @Column(name = "bank_name")
	private String bankName;
	
    @Column(name = "bank_account_no")
	private Long bankAccountNo;
	
    @Column(name = "bank_ifsc_code")
	private String bankIfscCode;	
        
    @OneToMany(targetEntity =  RevenueListingEntity.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "revenue_revenuelist_fk",referencedColumnName = "revenueId")
	private List<RevenueListingEntity> revenueListingEntities;
	
	
	 @PrePersist
	    private void beforePersist() {
	        calculateGrossRevenue();
	        calculateNetRevenue();
	    }

	    @PreUpdate
	    private void beforeUpdate() {
	        calculateGrossRevenue();
	        calculateNetRevenue();
	    }

	    private void calculateGrossRevenue() {
	        if (productPricePerUnit != null && noOfClients != null) {
	            this.grossRevenue = productPricePerUnit * noOfClients;
	       
	        }
	    }
	    
	    private void calculateNetRevenue() {
	        if (this.grossRevenue != null && returns != null && discounts != null && allowances != null) {
	            netRevenue = this.grossRevenue - (returns + discounts + allowances);
	        }
	    }
}






//package com.orive.Sale.Entity;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.PrePersist;
//import jakarta.persistence.PreUpdate;
//import jakarta.persistence.Table;
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
//@Entity
//@Table(name = "revenue")
//public class RevenueEntity {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long revenueId;
//	
//	@Column(name = "product_price_per_unit")
//	private Double productPricePerUnit;
//	
//	@Column(name = "no_of_clients")
//	private Double noOfClients;
//	
//	@Column(name = "gross_revenue")
//	private Double grossRevenue;
//	
//	@Column(name = "sale_date")
//	private LocalDate saleDate;
//	
//	@Column(name = "returns")
//	private Double returns;
//	
//	@Column(name = "discounts")
//	private Double discounts;
//	
//	@Column(name = "allowances")
//	private Double allowances;
//	
//	@Column(name = "net_revenue")
//	private Double netRevenue;
//	
//	@Column(name = "money_added_bank_name")
//	private String moneyAddedBankName;
//	
//	@Column(name = "moneyAddedBankNumber")
//	private String moneyAddedBankNumber;
//	
//	@Column(name = "moneyAddedBankIfscCode")
//	private String moneyAddedBankIfscCode;
//	
//	@Column(name = "status")
//	private String status;
//	
//	@Column(name = "billto_adress")
//	private String billtoAddress;
//	
//	@Column(name = "billto_gstin")
//	private String billtoGstin;
//	
//	@Column(name = "billtostate")
//	private String billtostate;
//	
//	@Column(name = "shipto_address")
//	private String shiptoAddress;
//	
//	@Column(name = "place_of_supply")
//	private String placeOfSupply;
//	
//	@Column(name = "invoice_number")
//	private String invoiceNumber;
//	
//	@Column(name = "date")
//	private LocalDate date;
//	
//	@Column(name = "time")
//	private String time;
//	
//	@Column(name = "product_name")
//	private String productName;
//	
//	@Column(name = "hsn_or_sac")
//	private String hsnOrSac;
//	
//	@Column(name = "unit")
//	private String unit;
//	
//	@Column(name = "gst_in_percentage")
//	private String gstInPercentage;
//	
//	@Column(name = "gst_price")
//	private Double gstPrice;
//	
//	@Column(name = "total_cost")
//	private Double totalCost;
//	
//	@Column(name = "sgst")
//	private Double sgst;
//	
//	@Column(name = "cgst")
//	private Double cgst;
//	
//	@Column(name = "igst")
//	private Double igst;
//	
//	@Column(name = "sgst_in_percentage")
//	private Double sgstInPercentage;
//	
//	@Column(name = "cgst_in_percentage")
//	private Double cgstInPercentage;
//	
//	@Column(name = "igst_in_percentage")
//	private Double igstInPercentage;
//	
//	@Column(name = "received_amount")
//	private Double receivedAmount;
//	
//	@Column(name = "balance_amount")
//	private Double balanceAmount;
//	
//	@Column(name = "invoice_amount_in_words")
//	private String invoiceAmountInWords;
//	
//	@Column(name = "payment_mode")
//	private String paymentMode;
//	
//	@Column(name = "grand_total")
//	private Double grandTotal;
//	
//	
//	
//	 @PrePersist
//	    private void beforePersist() {
//	        calculateGrossRevenue();
//	        calculateNetRevenue();
//	    }
//
//	    @PreUpdate
//	    private void beforeUpdate() {
//	        calculateGrossRevenue();
//	        calculateNetRevenue();
//	    }
//
//	    private void calculateGrossRevenue() {
//	        if (productPricePerUnit != null && noOfClients != null) {
//	            this.grossRevenue = productPricePerUnit * noOfClients;
//	       
//	        }
//	    }
//	    
//	    private void calculateNetRevenue() {
//	        if (this.grossRevenue != null && returns != null && discounts != null && allowances != null) {
//	            netRevenue = this.grossRevenue - (returns + discounts + allowances);
//	        }
//	    }
//}
