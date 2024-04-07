package com.orive.Procurement.Entity;

import java.time.LocalDate;

import com.orive.Procurement.Config.AesEncryptor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "quotation_list")
public class QuotationListEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long quotationListId;
	
	//@Convert(converter = AesEncryptor.class)
	private Long quotationId;
	
	@Column(name = "description_of_materials")
	@Convert(converter = AesEncryptor.class)
	private String descriptionOfMaterials;
	
	@Column(name = "unit_name")
	@Convert(converter = AesEncryptor.class)
	private String unitName;
	
	@Column(name = "quantity")
	@Convert(converter = AesEncryptor.class)
	private Long quantity;
	
	@Column(name = "price")
	@Convert(converter = AesEncryptor.class)
	private double price;
	
	@Column(name = "total")
	@Convert(converter = AesEncryptor.class)
	private double total;
	
	@Column(name = "grand_total")
	@Convert(converter = AesEncryptor.class)
	private double grandTotal;
}
