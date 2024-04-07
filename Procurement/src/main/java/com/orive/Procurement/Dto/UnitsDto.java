package com.orive.Procurement.Dto;

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
public class UnitsDto {
	
    private Long unitsId;
    private String unitName;
    private String status;

}
