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
public class SaleDto {

	
	private String saleId;
	private Double developerCost;
	private Double researchAndDevlopment;
	private Double customerSupportAndTechnicalAssitance;
	private Double serverMaintance;
	private Double customerSegment;
	private Double distributionChannel;
	private Double thirdPartySoftwareComponent;
	private Double perUserPrice;
	private Double totalNumberOfUser;
	private Double totalUserCost;
	private Double directSalesThroughWebsite;
	private Double salesTeam;
	private Double totalPrice;
	private Double gstPrice;
	private Double totalCost;
	private String status;
}

//package com.orive.Sale.Dto;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.hibernate.annotations.GenericGenerator;
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
//public class SaleDto {
//
//	private String saleId;
//	private String productName;
//	private String hsnOrSac;
//	private Double developerCost;
//	private Double researchAndDevlopment;
//	private Double customerSupportAndTechnicalAssitance;
//	private Double serverMaintance;
//	private Double customerSegment;
//	private Double distributionChannel;
//	private Double thirdPartySoftwareComponent;
//	private Double perUserPrice;
//	private Double totalNumberOfUser;
//	private Double totalUserCost;
//	private Double directSalesThroughWebsite;
//	private Double salesTeam;
//	private Double totalPrice;
//	private String gstInPercentage;
//	private Double gstPrice;
//	private Double totalCost;
//	private String status;
//}
