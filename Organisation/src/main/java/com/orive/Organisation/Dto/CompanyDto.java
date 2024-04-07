package com.orive.Organisation.Dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.orive.Organisation.Enum.Status;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
public class CompanyDto {

	
	private Long companyId;
	private String companyName;
	private String companyType;
	private String legalOrTradingName;
	private String address;
	private String registrationNumber;
	private Long contactNumber;
	private String email;
	private String website;
	private String city;
	private String state;
	private int zipCode;
	private String country;
	private String cin;
	private String gst;
	private String uan;
	private LocalDate createdDate;
	private byte[] file;
	private String status;
//	private List<LocationEntity> locationEntities=new ArrayList<>();
}
