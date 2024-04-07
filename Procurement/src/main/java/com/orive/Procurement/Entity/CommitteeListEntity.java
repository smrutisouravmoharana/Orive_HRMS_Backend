package com.orive.Procurement.Entity;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.orive.Procurement.Config.AesEncryptor;

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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "committeelist")
public class CommitteeListEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long committeeListId;
	
	//@Convert(converter = AesEncryptor.class)
	private Long bidAnalysisId;
	
	@Column(name = "name")
	@Convert(converter = AesEncryptor.class)
	private String name;
	
	@Lob
	@Column(name = "signature", length = 100000)
	private byte[] signature;
	
	@Column(name = "date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate date;

}
