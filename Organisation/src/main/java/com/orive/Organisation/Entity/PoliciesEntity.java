package com.orive.Organisation.Entity;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import com.orive.Organisation.Config.AesEncryptor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name = "policies")
public class PoliciesEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long policiesId;
	
	@Column(name = "company_name")
	@Convert(converter = AesEncryptor.class)
	private String companyName;
	
	@Column(name = "title")
	@Convert(converter = AesEncryptor.class)
	private String title;
	
	@Column(name = "description", length = 100000 )
	@Convert(converter = AesEncryptor.class)
	private String description;
	
	@Column(name = "created_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate createdDate;
	
	@Lob
	@Column(name = "upload_pdf", length = 100000)
	private byte[] uploadPdf;
	
//	@Column(name = "status")
//	private String status;
//	
//	@Column(name = "approved_by")
//	private String approvedBy;
}
