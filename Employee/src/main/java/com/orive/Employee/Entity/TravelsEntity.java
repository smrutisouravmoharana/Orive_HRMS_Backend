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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "travels")
public class TravelsEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelId;
	
	@Column(name = "employee_name")
	@Convert(converter = AesEncryptor.class)
	private String employeeName;
	
	@Column(name = "username")
	@Convert(converter = AesEncryptor.class)
	private String username;
	
	@Column(name = "start_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate startDate;
	
	@Column(name = "end_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate endDate;
	
	@Column(name = "purpose_of_visit")
	@Convert(converter = AesEncryptor.class)
	private String purposeOfVisit;
	
	@Column(name = "place_of_visit")
	@Convert(converter = AesEncryptor.class)
	private String placeOfVisit;
	
	@Column(name = "travel_mode")
	@Convert(converter = AesEncryptor.class)
	private String travelMode;
	
	@Column(name = "arrangement_type")
	@Convert(converter = AesEncryptor.class)
	private String arrangementType;
	
	@Column(name = "expected_travel_budget")
	@Convert(converter = AesEncryptor.class)
	private double expectedTravelBudget;
	
	@Column(name = "actual_travel_budget")
	@Convert(converter = AesEncryptor.class)
	private double actualTravelBudget;
	
	@Column(name = "description", length = 2000)
	@Convert(converter = AesEncryptor.class)
	private String description;
	
	@Column(name = "created_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate createdDate;
}
