package com.orive.Accounts.Dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import com.orive.Accounts.Entity.CreditVoucherTableEntity;

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
public class CreditVoucherDto {

	private Long creditVoucherId;
	private String voucherType;
	private String debitAccountHead;
	private LocalDate date;
	private String remark;
	private List<CreditVoucherTableDto> creditVoucherTableEntities;
	private double total;
}
