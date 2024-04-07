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
@Table(name = "holidays")
public class HolidaysEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence")
    @GenericGenerator(name = "custom-sequence", strategy = "com.orive.TimeSheet.Entity.HolidaysCustomIdGenerator")
	private String holidaysId;
	
	@Column(name = "event_name")
	@Convert(converter = AesEncryptor.class)
	private String eventName;
	
	@Column(name = "start_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate startDate;
	
	@Column(name = "end_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate endDate;
	
	@Column(name = "description")
	@Convert(converter = AesEncryptor.class)
	private String description;
}
