package com.orive.Organisation.Entity;



import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.orive.Organisation.Config.AesEncryptor;

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
@Table(name = "announcements")
public class AnnoucementEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long announcementsId;
	
	@Column(name = "title")
	@Convert(converter = AesEncryptor.class)
	private String title;
	
	@Column(name = "start_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate startDate;
	
	@Column(name = "end_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate endDate;
	
	@Column(name = "company_name")
	@Convert(converter = AesEncryptor.class)
	private String companyName;
	
	@Column(name = "location_name")
	@Convert(converter = AesEncryptor.class)
	private String locationName;
	
	@Column(name = "department_name")
	@Convert(converter = AesEncryptor.class)
	private String departmentName;
	
	@Column(name = "summary")
	@Convert(converter = AesEncryptor.class)
	private String summary;
		
	@Column(name = "description",length = 100000)
	@Convert(converter = AesEncryptor.class)
	private String description;
	
	@Column(name = "created_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate createdDate;
	
//	@Column(name = "status")
//	private String status;
//	
//	@Column(name = "approved_by")
//	private String approvedBy;
}
