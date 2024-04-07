package com.orive.Procurement.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orive.Procurement.Config.AesEncryptor;
import com.orive.Procurement.Enum.Status;

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
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
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
@Table(name = "good_received")
public class GoodReceivedEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long goodReceivedId;
	
	@Column(name = "purchase_order")
	@Convert(converter = AesEncryptor.class)
	private String purchaseOrder;
	
	@Column(name = "payment_source")
	@Convert(converter = AesEncryptor.class)
	private String paymentSource;
	
	@Column(name = "vendor_name")
	@Convert(converter = AesEncryptor.class)
	private String vendorName;
	
	@Column(name = "date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate date;
	
	@Column(name = "received_by_name")
	@Convert(converter = AesEncryptor.class)
	private String receivedByName;
	
	@Column(name = "title")
	@Convert(converter = AesEncryptor.class)
	private String title;
	
	@Column(name = "status")
	private String status;
	
//	@Enumerated(EnumType.STRING)
//	@Column(name = "status")
//	private Status status;
	
	@Lob
	@Column(name = "signature_and_stamp", length = 100000)
	private byte[] signatureAndStamp;
	
	@Transient
	private List<GoodReceivedListEntity> goodReceivedListEntities= new ArrayList<>();

//	@OneToMany(targetEntity = GoodReceivedListEntity.class,cascade = CascadeType.ALL)
//	@JoinColumn(name = "goodReceived_list_fk",referencedColumnName = "goodReceivedId")
//	private List<GoodReceivedListEntity> goodReceivedListEntities;

}
