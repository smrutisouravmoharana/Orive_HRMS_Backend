package com.orive.Procurement.Dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orive.Procurement.Config.AesEncryptor;
import com.orive.Procurement.Enum.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Lob;
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
public class PurchaseOrderDto {
	
    private Long purchaseOrderId;
	private String quotation;
	private String location;
	private String vendorName;
	private String address;
	private String notes;
	private String authorizedByName;
	private String title;
	private byte[] signatureAndStamp;
	private LocalDate date;
	private String status;
	private List<PurchaseOrderListDto> purchaseOrderListDtos=new ArrayList<>();
	private double total;
	private double grandTotal;
}
