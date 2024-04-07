package com.orive.Procurement.Dto;

import com.orive.Procurement.Entity.CompanyListEntity;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyListDto {

	private Long companyListId;
	private Long bidAnalysisId;
	private String company;
	private String description;
	private String reasonOfChoosing;
	private String remarks;
	private String unitName;
	private int quantity;
	private double price;
	private double total;
	private double grandTotal;
}
