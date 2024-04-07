package com.orive.Accounts.Dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import com.orive.Accounts.Entity.JournalVoucherTableEntity;

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
public class JournalVoucherDto {

    private Long journalVoucherId;
	private String journalVoucher;
	private String voucherType;
	private LocalDate date;
	private String remark;
	private List<JournalVoucherTableDto> journalVoucherTableEntities;
	private double totalCredit;
	private double totalDebit;
}
