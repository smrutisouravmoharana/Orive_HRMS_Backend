package com.orive.TimeSheet.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.orive.TimeSheet.Dto.AttendanceDto;
import com.orive.TimeSheet.Dto.HolidaysDto;
import com.orive.TimeSheet.Entity.AttendanceEntity;
import com.orive.TimeSheet.ExcelToDataBase.Help.ExcelHelper;
import com.orive.TimeSheet.Service.AttendanceService;
//import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping(value = "attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {
	
	private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);
	
	@Autowired
	private AttendanceService attendanceService;
	
	 @PostMapping("/create")
	 // @PreAuthorize("hasRole('client_HR')")
	    public ResponseEntity<AttendanceDto> createAttendance(@RequestBody AttendanceDto attendanceDto) {
	        logger.info("Received request to create attendance for Username: {} and Date: {}", attendanceDto.getUsername(), attendanceDto.getDate());

	        AttendanceDto createdAttendance = attendanceService.createsAttendances(attendanceDto);
	        if (createdAttendance != null) {
	            logger.info("Attendance created successfully with ID: {}", createdAttendance.getAttendanceId());
	            return new ResponseEntity<>(createdAttendance, HttpStatus.CREATED);
	        } else {
	            logger.warn("Attendance already exists for Username: {} and Date: {}", attendanceDto.getUsername(), attendanceDto.getDate());
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }
	
	
//	 @PostMapping("/upload/attendance")
 // @PreAuthorize("hasRole('client_HR')")
//	    public ResponseEntity<String> uploadExcel(
//	            @RequestParam String employeeName,
//	            @RequestParam LocalTime clockIn,
//	            @RequestParam LocalTime clockOut,
//	            @RequestParam Long late,
//	            @RequestParam Long earlyLeaving,
//	            @RequestParam Long overTime,
//	            @RequestParam Long totalWork,
//	            @RequestParam Long totalRest,
//	            @RequestParam LocalDate date,
//	            @RequestParam("uploadDoc") MultipartFile file) {
//	        try {
//	            String result = attendanceService.saveAttendanceEntity(
//	                    employeeName, clockIn, clockOut, late, earlyLeaving, overTime, totalWork, totalRest, date, file);
//	            return new ResponseEntity<>(result, HttpStatus.OK);
//	        } catch (Exception e) {
//	            return new ResponseEntity<>("Error uploading Excel file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	        }
//	    }
//
//	    @GetMapping("/download/excel/{attendanceId}")
 // @PreAuthorize("hasRole('client_HR')")
//	    public ResponseEntity<byte[]> downloadExcel(@RequestParam Long attendanceId) {
//	        byte[] excelData = attendanceService.downloadExcel(attendanceId);
//
//	        if (excelData != null) {
//	            HttpHeaders headers = new HttpHeaders();
//	            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//	            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(attendanceId + "_attendance.xlsx").build());
//	            return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
//	        } else {
//	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	        }
//	    }
//	
//	
    
    
    
//Get excelsheet   
    @PostMapping("/product/upload")
 // @PreAuthorize("hasRole('client_HR')")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file)
	{
		if(ExcelHelper.chechExcelFormat(file))
		{
			//true
			this.attendanceService.save(file);
			return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to database"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please upload excel file ");
	}
	

    //get all product
	@GetMapping("/get/product")
	// @PreAuthorize("hasRole('client_HR')")
	public List<AttendanceEntity> getAllAttendanceEntities()
	{
		return this.attendanceService.getAllAttendancesEntities();
	}

    

    // Get all Attendance   
    @GetMapping("/get/attendance")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<AttendanceDto>> getAllAttendance() {
        List<AttendanceDto> attendance = attendanceService.getAllAttendances();
        logger.info("Retrieved {} Attendance from the database", attendance.size());
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    // Get Attendance by ID
    @GetMapping("/get/{attendanceId}")
  //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
    public ResponseEntity<AttendanceDto> getAttendanceById(@PathVariable String attendanceId) {
        Optional<AttendanceDto> attendance = attendanceService.getAttendanceById(attendanceId);
        if (attendance.isPresent()) {
            logger.info("Retrieved Attendance with ID: {}", attendanceId);
            return new ResponseEntity<>(attendance.get(), HttpStatus.OK);
        } else {
            logger.warn("Attendance with ID {} not found", attendanceId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
 // Get Employee by ID
	  @GetMapping("/get/username/{username}")
	//@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")	 
	    public ResponseEntity<List<AttendanceDto>> getAttendanceByEmployeeId(@PathVariable String username) {
	        List<AttendanceDto> attendance = attendanceService.getEmployeeId(username);
	        if (attendance.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        } else {
	            return ResponseEntity.ok(attendance);
	        }
	    }
    

    // Update Attendance by ID
    @PutMapping("/update/{attendanceId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<AttendanceDto> updateAttendance(@PathVariable String attendanceId, @RequestBody AttendanceDto updatedAttendanceDto) {
    	AttendanceDto updatedAttendance = attendanceService.updateAttendances(attendanceId, updatedAttendanceDto);
        if (updatedAttendance != null) {
            logger.info("Updated Attendance with ID: {}", attendanceId);
            return new ResponseEntity<>(updatedAttendance, HttpStatus.OK);
        } else {
            logger.warn("Attendance with ID {} not found for update", attendanceId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    // Update Attendance by Name And Date
    @PutMapping("/update/{employeeName}/{date}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<AttendanceDto> updateAttendanceByEmployeeNameAndDate(@PathVariable String employeeName, @PathVariable LocalDate date, @RequestBody AttendanceDto updatedAttendanceDto) {
    	AttendanceDto updatedAttendance = attendanceService.updateAttendancesByEmployeeNameAndDate(employeeName, date, updatedAttendanceDto);
        if (updatedAttendance != null) {
            logger.info("Updated Attendance with name and date: {}", employeeName,date);
            return new ResponseEntity<>(updatedAttendance, HttpStatus.OK);
        } else {
            logger.warn("Attendance with name and date {} not found for update", employeeName,date);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
 // Update Attendance by Id And Date
    @PutMapping("/update/Id/{username}/{date}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<AttendanceDto> updateAttendanceByEmployeeIdAndDate(@PathVariable String username, @PathVariable LocalDate date, @RequestBody AttendanceDto updatedAttendanceDto) {
    	AttendanceDto updatedAttendance = attendanceService.updateAttendancesByEmployeeIdAndDate(username, date, updatedAttendanceDto);
        if (updatedAttendance != null) {
            logger.info("Updated Attendance with name and date: {}", username,date);
            return new ResponseEntity<>(updatedAttendance, HttpStatus.OK);
        } else {
            logger.warn("Attendance with name and date {} not found for update", username,date);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    // Delete Attendance by ID
    @DeleteMapping("/delete/{attendanceId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteAttendance(@PathVariable String attendanceId) {
  	  attendanceService.deleteAttendances(attendanceId);
        logger.info("Deleted Attendance with ID: {}", attendanceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
    //count the total Attendance
	    @GetMapping("/count/attendance")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countAttendance()
	    {
	    	return attendanceService.countAttendances();
	    }

	    //count the total Attendance of employees present today    
	    @GetMapping("/count/present/attendance")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countPresentEmployeesToday()
	    {
	    	return attendanceService.countPresentEmployeesToday();
	    }
	    
	    //count the totalOvertime on a particular month and year
	    @GetMapping("/total-overtime/{username}/{year}/{month}")
	 // @PreAuthorize("hasRole('client_HR')")
	    public ResponseEntity<String> getTotalOvertimeInMonth(
	            @PathVariable String username,
	            @PathVariable int year,
	            @PathVariable int month) {

	        String totalOvertime = attendanceService.getTotalOvertimeInMonth(username, year, month);
	        return ResponseEntity.ok(totalOvertime);
	    }

	    
	    //count the total times login in a month
	    @GetMapping("/login-days/{username}/{month}/{year}")
	 // @PreAuthorize("hasRole('client_HR')")
	    public int getNumberOfLoginDaysForMonth(
	            @PathVariable String username,
	            @PathVariable int month,
	            @PathVariable int year
	    ) {
	        return attendanceService.getNumberOfLoginDaysForMonth(month, year, username);
	    }
}