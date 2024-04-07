package com.orive.Procurement.Dto;

import com.orive.Procurement.Entity.GoodReceivedListEntity;

import jakarta.persistence.Column;
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
public class GoodReceivedListDto {
	
    private Long goodReceivedListId;
    private Long goodReceivedId;
	private String description;
	private String unitName;
	private int quantity;
	private double price;
	private double total;
	private double grandTotal;


}
