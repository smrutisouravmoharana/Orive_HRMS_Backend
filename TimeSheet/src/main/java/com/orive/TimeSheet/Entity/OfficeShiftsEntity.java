package com.orive.TimeSheet.Entity;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.orive.TimeSheet.Configuration.AesEncryptor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "officeshift")
public class OfficeShiftsEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence")
    @GenericGenerator(name = "custom-sequence", strategy = "com.orive.TimeSheet.Entity.OfficeShiftsCustomIdGenerator")
	private String officeShiftsId;
	
	@Column(name = "created_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate createdDate;
	
	@Column(name = "day")
	@Convert(converter = AesEncryptor.class)
	private String day;
	
	@Column(name = "office_clock_in")
	@Convert(converter = AesEncryptor.class)
	private String officeClockIn;
	
	@Column(name = "office_clock_out")
	@Convert(converter = AesEncryptor.class)
	private String officeClockOut;
}