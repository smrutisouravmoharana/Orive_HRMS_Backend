package com.orive.Employee.Entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "experience_letter")
public class ExperienceLetterEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long experienceLetterId;
	
	@Column(name = "form_release_date")
	private LocalDate formReleaseDate;
	
	@Column(name = "referrence_number")
	private String referrenceNumber;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "employee_name")
	private String employeeName;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "work_based_on")
	private String workBasedOn;
	
	@Column(name = "joining_date")
	private LocalDate joiningDate;
	
	@Column(name = "leaves_date")
	private LocalDate leavesDate;
	
	@Column(name = "serving_period")
	private String  servingPeriod;
	
	
	
//	// Method for auto generated  reference number
//		private static int lastRandomPart = 0;
//
//		@PrePersist
//		public void generateReferenceNumber() {
//		    // Generate reference number logic
//		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-");
//		    String currentDate = dateFormat.format(new Date());
//		    String randomPart = String.format("%03d", getNextRandomPart()); // Adjust length as per requirement
//		    this.referrenceNumber = "REF-" + currentDate + randomPart;
//		}
//
//		private synchronized int getNextRandomPart() {
//		    // Increment lastRandomPart and return it as a formatted string
//		    lastRandomPart = (lastRandomPart % 999) + 1; // Reset to 1 if exceeds 999
//		    return lastRandomPart;
//		}

}
