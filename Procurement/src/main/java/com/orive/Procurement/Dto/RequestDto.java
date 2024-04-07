package com.orive.Procurement.Dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.orive.Procurement.Enum.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class RequestDto {

	private Long requestId;
	
	private String requestingPerson;
	
	private String requestingDepartment;	
	
	private LocalDate expectedTimeToHaveTheGoodStarts;
	
	private LocalDate expectedTimeToHaveTheGoodEnds;
	
	private String reasonForRequesting;
	
	private LocalDate createdDate;
	
	private String status;
	
	private List<DescriptionOfMaterialListDto> descriptionOfMaterialEntities;
}
