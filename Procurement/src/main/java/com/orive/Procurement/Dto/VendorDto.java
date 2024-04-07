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
public class VendorDto {
 
	private Long vendorId;
	private String vendorName;
	private Long mobileNo;
	private String emailAddress;
	private String address;
	private String country;
	private String city;
	private int zipCode;
	private double previousBalance;
	private String status;
}
