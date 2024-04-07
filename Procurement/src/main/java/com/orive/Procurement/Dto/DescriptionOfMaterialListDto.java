package com.orive.Procurement.Dto;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class DescriptionOfMaterialListDto {
	
	private Long descriptionOfMaterialId;
	
	private String descriptionOfMaterialOrGoodsOrService;
	
	private String unitName;
	
	private Double quantity;

}
