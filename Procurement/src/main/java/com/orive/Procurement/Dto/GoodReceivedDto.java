package com.orive.Procurement.Dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orive.Procurement.Entity.GoodReceivedEntity;
import com.orive.Procurement.Enum.Status;

import jakarta.persistence.Column;
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
public class GoodReceivedDto {

    private Long goodReceivedId;
	private String purchaseOrder;
	private String paymentSource;
	private String vendorName;
	private LocalDate date;
	private String receivedByName;
	private String title;
	private String status;
	private byte[] signatureAndStamp;
	private List<GoodReceivedListDto> goodReceivedListDtos=new ArrayList<>();
}
