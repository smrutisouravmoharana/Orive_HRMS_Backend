package com.orive.Sale.Entity;

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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "revenuelisting")
public class RevenueListingEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long revenueListingId;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "hsn")
	private Long hsn;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@Column(name = "unit")
	private String unit;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "gst_in_number")
	private Double gstInNumber;
	
	@Column(name = "gst_in_percentage")
	private Double gstInPercentage;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "total_quantity")
	private Double totalQuantity;
	
	@Column(name = "total_gst_in_number")
	private Double totalGstInNumber;
	
	@Column(name = "total_amouunt")
	private Double totalAmouunt;
}
