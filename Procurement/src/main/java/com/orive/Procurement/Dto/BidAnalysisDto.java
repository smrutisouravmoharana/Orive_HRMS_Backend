package com.orive.Procurement.Dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orive.Procurement.Entity.CommitteeListEntity;
import com.orive.Procurement.Entity.CompanyListEntity;
import com.orive.Procurement.Enum.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
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
public class BidAnalysisDto {


	private Long bidAnalysisId;
	private String location;
	private LocalDate date;
	private String quotation;
	private byte[] attachment;
	private String status;
	private List<CommitteeListDto> committeeListDtos = new ArrayList<>();
	private List<CompanyListDto> companyListDtos= new ArrayList<>();
}
