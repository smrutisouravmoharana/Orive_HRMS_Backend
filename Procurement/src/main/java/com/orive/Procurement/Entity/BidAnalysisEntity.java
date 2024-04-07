package com.orive.Procurement.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.orive.Procurement.Config.AesEncryptor;
import com.orive.Procurement.Enum.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
@Table(name = "bidanalysis")
public class BidAnalysisEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bidAnalysisId;
	
	@Column(name = "location")
	@Convert(converter = AesEncryptor.class)
	private String location;

	@Column(name = "date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate date;
	
	@Column(name = "quotation")
	@Convert(converter = AesEncryptor.class)
	private String quotation;
	
	@Lob
	@Column(name = "attachment", length = 100000)
	private byte[] attachment;
	
	@Column(name = "status")
	private String status;
	
//	@Enumerated(EnumType.STRING)
//	@Column(name = "status")
//	private Status status;
	
	@Transient
	private List<CommitteeListEntity> committeeListEntities = new ArrayList<>();

	@Transient
	private List<CompanyListEntity> companyListEntities = new ArrayList<>();

	
//	@OneToMany(targetEntity = CommitteeListEntity.class,cascade = CascadeType.ALL)
//	@JoinColumn(name = "bidAnalysis_committee_fk",referencedColumnName = "bidAnalysisId")
//	private List<CommitteeListEntity> committeeEntities;
	
	
//	@OneToMany(targetEntity = CompanyListEntity.class,cascade = CascadeType.ALL)
//	@JoinColumn(name = "bidAnalysis_company_fk",referencedColumnName = "bidAnalysisId")
//	private List<CompanyListEntity> companyListEntities;
}
