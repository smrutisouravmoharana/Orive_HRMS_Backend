package com.orive.Accounts.Entity;

import com.orive.Accounts.Config.AesEncryptor;

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
@Table(name = "subtype")
public class SubTypeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subTypeId;
	
	@Column(name = "subType")
	@Convert(converter = AesEncryptor.class)
	private String subType;
	
	@Column(name = "accountName")
	@Convert(converter = AesEncryptor.class)
	private String accountName;
	
//	@Column(name = "createDate")
//	private String createDate;
}
