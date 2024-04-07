package com.orive.Procurement.Dto;

import com.orive.Procurement.Entity.QuotationListEntity;

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
public class QuotationListDto {

    private Long quotationListId;
    private Long quotationId;
	private String descriptionOfMaterials;
	private String unitName;
	private Long quantity;
	private double price;
	private double total;
	private double grandTotal;
}
