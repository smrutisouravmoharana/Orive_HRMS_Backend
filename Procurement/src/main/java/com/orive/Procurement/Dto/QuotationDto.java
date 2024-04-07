package com.orive.Procurement.Dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.orive.Procurement.Entity.QuotationEntity;
import com.orive.Procurement.Enum.Status;

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
public class QuotationDto {

    private Long quotationId;
	private String nameOfCompany;
	private String address;
	private Long pinOrEquivalent;
	private LocalDate expectedDateOfDelivery;
	private String placeOfDelivery;
	private byte[] signatureAndStamp;
	private LocalDate date;
	private String status;
	private List<QuotationListDto> quotationListDtos=new ArrayList<>();
}
