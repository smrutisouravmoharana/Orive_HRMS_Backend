package com.orive.orive_website.DTO;

import com.orive.orive_website.Enum.Status;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class LandingPagePopUpDto {
	
    private Long landingPagePopUpId;
	private Status selectOne;
	private String name;
	private String designation;
	private String organisationName;
	private String email;
	private Long phoneNumber;
	private String selectService;
	private String message;
	private String city;
}
