package com.orive.Employee.Entity;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

import com.orive.Employee.Configuration.AesEncryptor;

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
@Table(name = "awards")
public class AwardsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long awardId;
	
	@Column(name = "username")
	@Convert(converter = AesEncryptor.class)
	private String username;
	
	@Column(name = "award_name")
	@Convert(converter = AesEncryptor.class)
	private String awardName;
	
	@Column(name = "award_description")
	@Convert(converter = AesEncryptor.class)
	private String awardDescription;
	
	@Column(name = "gift_item")
	@Convert(converter = AesEncryptor.class)
	private String giftItem;
	
	@Column(name = "date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate date;
	
	@Column(name = "employee_name")
	@Convert(converter = AesEncryptor.class)
	private String employeeName;
	
	@Column(name = "email")
	@Convert(converter = AesEncryptor.class)
	private String email;
	
	@Column(name = "award_by")
	@Convert(converter = AesEncryptor.class)
	private String awardBy;
}
