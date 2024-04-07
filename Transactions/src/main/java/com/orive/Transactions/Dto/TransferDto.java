package com.orive.Transactions.Dto;

import jakarta.persistence.Column;
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
public class TransferDto {
	
    private Long transferId;
	private String employeeFullName;
	private String employeeId;
	private String currentDepartment;
	private String currentPosition;
	private String currentLocation;
	private String transferType;
	private String transferDate;
	private String newDepartment;
	private String newPosition;
	private String newLocation;
	private String reasonOfTransfer;
	private String currentManagersName;
	private String newmanagersName;
	private String hrRepresentivesName;
	private String employeeAcknowledgement;
}
