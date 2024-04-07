package com.orive.Procurement.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.orive.Procurement.Config.AesEncryptor;
import com.orive.Procurement.Enum.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
@Table(name = "quotation")
public class QuotationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long quotationId;
	
	@Column(name = "name_of_company")
	@Convert(converter = AesEncryptor.class)
	private String nameOfCompany;
	
	@Column(name = "address")
	@Convert(converter = AesEncryptor.class)
	private String address;
	
	@Column(name = "pin_or_equivalent")
	@Convert(converter = AesEncryptor.class)
	private Long pinOrEquivalent;
	
	@Column(name = "expected_date_of_delivery")
	@Convert(converter = AesEncryptor.class)
	private LocalDate expectedDateOfDelivery;
	
	@Column(name = "place_of_delivery")
	@Convert(converter = AesEncryptor.class)
	private String placeOfDelivery;
	
	@Lob
	@Column(name = "signature_and_stamp", length = 100000)
	private byte[] signatureAndStamp;
	
	@Column(name = "date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate date;
	
	@Column(name = "status")
	private String status;
	
//	@Enumerated(EnumType.STRING)
//	@Column(name = "status")
//	private Status status;
	
	@Transient
	private List<QuotationListEntity> quotationListEntities=new ArrayList<>();
}
