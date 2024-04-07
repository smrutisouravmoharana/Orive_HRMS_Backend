package com.orive.Employee.Dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

import com.orive.Employee.Entity.PromotionsEntity;

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
public class PromotionsDto {
	
    private Long promotionsId;
	private String employeeName;
	private String username;
	private String email;
	private String promotionTitle;
	private Integer salaryHike;
	private LocalDate promotionDate;
	private String hrName;
	private String description;
}
