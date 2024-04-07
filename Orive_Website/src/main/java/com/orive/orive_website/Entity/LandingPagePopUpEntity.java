package com.orive.orive_website.Entity;

import com.orive.orive_website.Enum.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "landingpage_popup")
public class LandingPagePopUpEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long landingPagePopUpId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="select_one")
	private Status selectOne;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "organisation_name")
	private String organisationName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone_number")
	private Long phoneNumber;
	
	@Column(name = "select_service")
	private String selectService;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "city")
	private String city;

}
