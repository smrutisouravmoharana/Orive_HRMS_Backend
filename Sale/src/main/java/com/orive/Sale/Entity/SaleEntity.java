package com.orive.Sale.Entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.orive.Sale.Config.AesEncryptor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "sale")
public class SaleEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence")
    @GenericGenerator(name = "custom-sequence", strategy = "com.orive.Sale.Entity.CustomIdGenerator")
	private String saleId;
	
	@Column(name = "developer_cost")
	private Double developerCost;
	
	@Column(name = "research_and_devlopment")
	private Double researchAndDevlopment;
	
	@Column(name = "customer_support_and_technical_assitance")
	private Double customerSupportAndTechnicalAssitance;
	
	@Column(name = "server_maintance")
	private Double serverMaintance;
	
	@Column(name = "customer_segment")
	private Double customerSegment;
	
	@Column(name = "distribution_channel")
	private Double distributionChannel;
	
	@Column(name = "third_party_software_component")
	private Double thirdPartySoftwareComponent;
	
	@Column(name = "per_user_price")
	private Double perUserPrice;
	
	@Column(name = "total_number_of_user")
	private Double totalNumberOfUser;
	
	@Column(name = "total_user_cost")
	private Double totalUserCost;
	
	@Column(name = "direct_sales_through_website")
	private Double directSalesThroughWebsite;
	
	@Column(name = "sales_team")
	private Double salesTeam;
	
	@Column(name = "total_price")
	private Double totalPrice;
	
	@Column(name = "gst_price")
	private Double gstPrice;
	
	@Column(name = "total_cost")
	private Double totalCost;
	
	@Column(name = "status")
	private String status;
}







//package com.orive.Sale.Entity;
//
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.List;
//
//import org.hibernate.annotations.GenericGenerator;
//
//import com.orive.Sale.Config.AesEncryptor;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Convert;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToMany;
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
//@Table(name = "sale")
//public class SaleEntity {
//
//	@Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence")
//    @GenericGenerator(name = "custom-sequence", strategy = "com.orive.Sale.Entity.CustomIdGenerator")
//	private String saleId;
//	
//	@Column(name = "product_name")
//	private String productName;
//	
//	@Column(name = "hsn_or_sac")
//	private String hsnOrSac;
//	
//	@Column(name = "developer_cost")
//	private Double developerCost;
//	
//	@Column(name = "research_and_devlopment")
//	private Double researchAndDevlopment;
//	
//	@Column(name = "customer_support_and_technical_assitance")
//	private Double customerSupportAndTechnicalAssitance;
//	
//	@Column(name = "server_maintance")
//	private Double serverMaintance;
//	
//	@Column(name = "customer_segment")
//	private Double customerSegment;
//	
//	@Column(name = "distribution_channel")
//	private Double distributionChannel;
//	
//	@Column(name = "third_party_software_component")
//	private Double thirdPartySoftwareComponent;
//	
//	@Column(name = "per_user_price")
//	private Double perUserPrice;
//	
//	@Column(name = "total_number_of_user")
//	private Double totalNumberOfUser;
//	
//	@Column(name = "total_user_cost")
//	private Double totalUserCost;
//	
//	@Column(name = "direct_sales_through_website")
//	private Double directSalesThroughWebsite;
//	
//	@Column(name = "sales_team")
//	private Double salesTeam;
//	
//	@Column(name = "total_price")
//	private Double totalPrice;
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
//	@Column(name = "status")
//	private String status;
//}
