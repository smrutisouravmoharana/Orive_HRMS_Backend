package com.orive.Accounts.Dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import com.orive.Accounts.Entity.OpeningBalanceTableEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OpeningBalanceDTo {

    private Long openingBalanceId;
	private String financialYear;
	private LocalDate date;
	private List<OpeningBalanceTableDto> openingBalanceTableEntities;
	private double totalDebit;
	private double totalCredit;
}
