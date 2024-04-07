package com.orive.Transactions.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transfer")
public class TransferEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transferId;
	
	@Column(name = "employee_full_name")
	private String employeeFullName;
	
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "current_department")
	private String currentDepartment;
	
	@Column(name = "current_position")
	private String currentPosition;
	
	@Column(name = "current_location")
	private String currentLocation;
	
	@Column(name = "transfer_type")
	private String transferType;
	
	@Column(name = "transfer_date")
	private String transferDate;
	
	@Column(name = "new_department")
	private String newDepartment;
	
	@Column(name = "new_position")
	private String newPosition;
	
	@Column(name = "new_location")
	private String newLocation;
	
	@Column(name = "reason_of_Transfer")
	private String reasonOfTransfer;
	
	@Column(name = "current_managers_name")
	private String currentManagersName;
	
	@Column(name = "new_managers_name")
	private String newmanagersName;
	
	@Column(name = "hr_representives_name")
	private String hrRepresentivesName;
	
	@Column(name = "employee_acknowledgement")
	private String employeeAcknowledgement;
}
