package com.orive.TimeSheet.Entity;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.orive.TimeSheet.Configuration.AesEncryptor;

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
@Table(name = "leaves")
public class LeavesEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence")
    @GenericGenerator(name = "custom-sequence", strategy = "com.orive.TimeSheet.Entity.LeavesCustomIdGenerator")
	private String leaveId;
	
	@Column(name = "username")
	@Convert(converter = AesEncryptor.class)
	private String username;
	
	@Column(name = "employee_name")
	@Convert(converter = AesEncryptor.class)
	private String employeeName;
	
	@Column(name = "leave_type")
	@Convert(converter = AesEncryptor.class)
	private String leaveType;
	
	@Column(name = "start_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate startDate;
	
	@Column(name = "end_date")
	@Convert(converter = AesEncryptor.class)
	private LocalDate endDate;
	
	@Column(name = "leave_reason")
	@Convert(converter = AesEncryptor.class)
	private String leaveReason;
	
	@Column(name = "applied_on")
	@Convert(converter = AesEncryptor.class)
	private LocalDate appliedOn;
	
	@Column(name = "approval")
	@Convert(converter = AesEncryptor.class)
	private String approval;	
}
