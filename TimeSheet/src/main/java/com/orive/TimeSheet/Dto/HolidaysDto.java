package com.orive.TimeSheet.Dto;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class HolidaysDto {

    private String holidaysId;
	private String eventName;
	private LocalDate startDate;
	private LocalDate endDate;
	private String description;
}
