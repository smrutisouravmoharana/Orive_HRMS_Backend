package com.orive.bank.entities;



import org.hibernate.annotations.GenericGenerator;

import com.orive.bank.configuration.AesEncryptor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Table(name = "events")
public class EventEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence")
    @GenericGenerator(name = "custom-sequence", strategy = "com.orive.bank.entities.EventCustomIdGenerator")
	private String eventId;
	
	@Column(name = "date")
	@Convert(converter = AesEncryptor.class)
	private String date;
	
	@Column(name = "title")
	@Convert(converter = AesEncryptor.class)
	private String title;
	
	@Column(name = "class_name")
	@Convert(converter = AesEncryptor.class)
	private String className;
	
//	@Column(name = "status")
//	private String status;
	
//	@Enumerated(EnumType.STRING)
//	@Column(name = "status")
//	private Status status;
}
