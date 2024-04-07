package com.orive.TimeSheet.Entity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.orive.TimeSheet.Configuration.AesEncryptor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@Table(name = "attendance")
public class AttendanceEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-sequence")
    @GenericGenerator(name = "custom-sequence", strategy = "com.orive.TimeSheet.Entity.AttendanceCustomIdGenerator")
	private String attendanceId;
	
	@Column(name = "office_clock_in")
	@Convert(converter = AesEncryptor.class)
	private String officeClockIn;
	
	@Column(name = "office_clock_out")
	@Convert(converter = AesEncryptor.class)
	private String officeClockOut;
	
	@Column(name = "employee_name")
	@Convert(converter = AesEncryptor.class)
	private String employeeName;
	
	@Column(name = "username")
	//@Convert(converter = AesEncryptor.class)
	private String username;
	
	@Column(name = "clock_in")
	@Convert(converter = AesEncryptor.class)
	private String clockIn;
	
	@Column(name = "clock_out")
	@Convert(converter = AesEncryptor.class)
	private String clockOut;
	
	@Column(name = "late")
	//@Convert(converter = AesEncryptor.class)
	private String late;
	
	@Column(name = "early_leaving")
	//@Convert(converter = AesEncryptor.class)
	private String earlyLeaving;
	
	@Column(name = "over_time")
	//@Convert(converter = AesEncryptor.class)
	private String overTime;
	
	@Column(name = "total_work")
	//@Convert(converter = AesEncryptor.class)
	private String totalWork;
	
	@Column(name ="total_rest")
	//@Convert(converter = AesEncryptor.class)
	private String totalRest;
	
	@Column(name = "date")
	//@Convert(converter = AesEncryptor.class)
	private LocalDate date;
	
	@Column(name = "clock_in_location")
	//@Convert(converter = AesEncryptor.class)
	private String clockInLocation;
	
	@Column(name = "clock_out_location")
	//@Convert(converter = AesEncryptor.class)
	private String clockOutLocation;
	
//	@Lob
//	@Column(name = "upload_doc",  length = 100000)
//	private byte[] uploadDoc;
	
	
	 @PrePersist
	    public void prePersist() {
	        // Perform any logic or calculations before the entity is persisted (inserted into the database)
		    
	        calculateTotalWork(); // Call the method to calculate total work duration
	        setOverTime(calculateOvertime());
	        setLate(calculateLate());
	        setEarlyLeaving(calculateEarlyLeaving());
	    }

	    @PreUpdate
	    public void preUpdate() {
	        // Perform any logic or calculations before the entity is updated in the database
	        calculateTotalWork(); // Call the method to calculate total work duration
	        setOverTime(calculateOvertime());
	        setLate(calculateLate());
	        setEarlyLeaving(calculateEarlyLeaving());
	    }
	    
	    

	    private void calculateTotalWork() {
	        if (clockIn != null && clockOut != null) {
	            // Default values for clockIn and clockOut
	            LocalTime startTime = LocalTime.parse("00:00:00");
	            LocalTime endTime = LocalTime.parse("00:00:00");

	            // Parse the clock-in and clock-out times if available
	            if (!clockIn.isEmpty() && !clockIn.equals("00:00:00")) {
	                startTime = LocalTime.parse(clockIn);
	            }
	            if (!clockOut.isEmpty() && !clockOut.equals("00:00:00")) {
	                endTime = LocalTime.parse(clockOut);
	            }
	            
	            // Check if clockOut is "00:00:00"
	            if (endTime.equals(LocalTime.parse("00:00:00"))) {
	                // Update the totalWork field with zero if clockOut is "00:00:00"
	                setTotalWork("0h 00min");
	            } else {
	                // Calculate total work duration
	                Duration totalWorkDuration = Duration.between(startTime, endTime);

	                // Convert total work duration to hours and minutes
	                long totalMinutes = totalWorkDuration.toMinutes();
	                long hours = totalMinutes / 60;
	                long minutes = totalMinutes % 60;

	                // Update the totalWork field with the formatted result
	                setTotalWork(String.format("%dh %02dmin", hours, minutes));
	            }
	        }
	    }


	    
	    private String calculateOvertime() {
	        if (clockOut != null && officeClockOut != null) {
	            LocalTime endTime = LocalTime.parse(clockOut);
	            LocalTime officeEndTime = LocalTime.parse(officeClockOut);

	            // Check if the employee clocked out after the office clock-out time
	            if (endTime.isAfter(officeEndTime)) {
	                Duration overtimeDuration = Duration.between(endTime, officeEndTime);

	                // Convert overtime duration to hours and minutes
	                long overtimeMinutes = overtimeDuration.toMinutes();
	                long overtimeHours = Math.abs(overtimeMinutes / 60); // Use Math.abs to get absolute value
	                long overtimeMinutesRemainder = Math.abs(overtimeMinutes % 60);

	                // Return the formatted result
	                return String.format("%dh %02dmin", overtimeHours, overtimeMinutesRemainder);
	            } else {
	                // If clockOut is earlier than officeClockOut, return "0h 00min"
	                return "0h 00min";
	            }
	        }

	        return null; // Return null if clockOut or officeClockOut is not available
	    }

	    
	    private String calculateLate() {
	        if (clockIn != null && officeClockIn != null) {
	            // Parse the clock-in times
	            LocalTime startTime = LocalTime.parse(clockIn);
	            LocalTime officeStartTime = LocalTime.parse(officeClockIn);

	            // Add a condition to check if clockIn is earlier than officeClockIn
	            if (startTime.isBefore(officeStartTime)) {
	                return "00:00:00";
	            }

	            // Calculate late as the difference between clock-in time and office clock-in time
	            Duration lateDuration = Duration.between(officeStartTime, startTime);

	            // Convert late duration to hours and minutes
	            long lateMinutes = lateDuration.toMinutes();
	            long lateHours = lateMinutes / 60;
	            long lateMinutesRemainder = lateMinutes % 60;

	            // Return the formatted result
	            return String.format("%dh %02dmin", lateHours, lateMinutesRemainder);
	        }
	        
	        return null; // Return null if clockIn or officeClockIn is not available
	    }

	    
	    private String calculateEarlyLeaving() {
	        // Default values for clockOut and officeClockOut
	        LocalTime endTime = LocalTime.parse("00:00:00");
	        LocalTime officeEndTime = LocalTime.parse("00:00:00");

	        // Parse the clock-out times if available
	        if (clockOut != null && !clockOut.isEmpty() && !clockOut.equals("00:00:00")) {
	            endTime = LocalTime.parse(clockOut);
	        }

	        // Add a condition to return "00:00:00" if clockOut is "00:00:00"
	        if (endTime.equals(LocalTime.parse("00:00:00"))) {
	            return "00:00:00";
	        }

	        if (officeClockOut != null && !officeClockOut.isEmpty() && !officeClockOut.equals("00:00:00")) {
	            officeEndTime = LocalTime.parse(officeClockOut);
	        }

	        // Check if clockOut is earlier than officeClockOut
	        if (endTime.isBefore(officeEndTime)) {
	            // Calculate early leaving as the difference between office clock-out time and clock-out time
	            Duration earlyLeavingDuration = Duration.between(endTime, officeEndTime);

	            // Convert early leaving duration to hours and minutes
	            long earlyLeavingMinutes = earlyLeavingDuration.toMinutes();
	            long earlyLeavingHours = earlyLeavingMinutes / 60;
	            long earlyLeavingMinutesRemainder = earlyLeavingMinutes % 60;

	            // Return the formatted result
	            return String.format("%dh %02dmin", earlyLeavingHours, earlyLeavingMinutesRemainder);
	        }

	        // Return "0h 00min" if clockOut is not earlier than officeClockOut or if they are the same
	        return "0h 00min";
	    }



}