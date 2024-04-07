package com.orive.Procurement.Dto;



import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PurchaseOrderListDto {
	
	private Long purchaseOrderListId;
	private Long purchaseOrderId;
	private String description;
	private String unitName;
	private int quantity;
	private double price;
	

}
