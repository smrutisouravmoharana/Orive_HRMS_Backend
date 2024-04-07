package com.orive.TimeSheet.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.orive.TimeSheet.Dto.AttendanceDto;
import com.orive.TimeSheet.Dto.HolidaysDto;
import com.orive.TimeSheet.Entity.AttendanceEntity;
import com.orive.TimeSheet.Entity.HolidaysEntity;
import com.orive.TimeSheet.ExcelToDataBase.Help.ExcelHelper;
import com.orive.TimeSheet.Repository.AttendanceRepository;
import com.orive.TimeSheet.Util.UploadDocUtil;

@Service
public class AttendanceService {
	
	private static final Logger logger = LoggerFactory.getLogger(AttendanceService.class);
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
	public AttendanceDto createsAttendances(AttendanceDto attendanceDto) {
	    String username = attendanceDto.getUsername();
	    LocalDate date = attendanceDto.getDate();

	    // Check if entry already exists for the same username and date
	    if (attendanceRepository.existsByUsernameAndDate(username, date)) {
	        logger.warn("Attendance for Username: {} and Date: {} already exists.", username, date);
	        // You can handle this situation as needed, for example, throw an exception or return null
	        // For now, I'll return null as an example
	        return null;
	    }

	    AttendanceEntity attendanceEntity = convertToEntity(attendanceDto);
	    AttendanceEntity savedAttendances = attendanceRepository.save(attendanceEntity);
	    logger.info("Created Attendance with ID: {}", savedAttendances.getAttendanceId());
	    return convertToDTO(savedAttendances);
	}

	
	
//	
//	public String saveAttendanceEntity(
//			String employeeName,
//			LocalTime clockIn,
//			LocalTime clockOut,
//			Long late,
//			Long earlyLeaving,
//			Long overTime,
//			Long totalWork,
//			Long totalRest,
//			LocalDate date,
//			MultipartFile file) {
//		
//
//        try {
//        	byte[] compressedFile = ExcelHelper.compressExcel(file.getBytes()); // Use your Excel compression logic
//
//            AttendanceEntity docData = attendanceRepository.save(AttendanceEntity.builder()
//                    .employeeName(employeeName)
//                    .clockIn(clockIn)
//                    .clockOut(clockOut)
//                    .late(late)
//                    .earlyLeaving(earlyLeaving)
//                    .overTime(overTime)
//                    .totalWork(totalWork)
//                    .totalRest(totalRest)
//                    .date(date)
//                    .uploadDoc(compressedFile)
//                    .build());
//
//            if (docData != null) {
//                return "File uploaded successfully: " + file.getOriginalFilename();
//            }
//        } catch (IOException e) {
//            // Handle the IOException appropriately (e.g., log it, return an error message)
//            return "Error uploading file: " + e.getMessage();
//        }
//
//        return null;
//    }
//	
//	//Download excel file
//	public byte[] downloadExcel(Long attendanceId) {
//	    Optional<AttendanceEntity> dbDocData = attendanceRepository.findById(attendanceId);
//
//	    if (dbDocData.isPresent()) {
//	        return dbDocData.get().getUploadDoc();
//	    } else {
//	        // Handle the case where the attendance data is not found
//	        return null;
//	    }
//	}
    
    
    //upload excelsheet   
    public void save(MultipartFile file)
	{
		try {
		List<AttendanceEntity> attendanceEntities=ExcelHelper.convertExcelToListOfAttendance(file.getInputStream());
		this.attendanceRepository.saveAll(attendanceEntities);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<AttendanceEntity> getAllAttendancesEntities()
	{
		return this.attendanceRepository.findAll();
		
	}

    // Read
    public List<AttendanceDto> getAllAttendances() {
        List<AttendanceEntity> attendanceEntities = attendanceRepository.findAll();
        logger.info("Retrieved {} Attendance from the database", attendanceEntities.size());
        return attendanceEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by AttendanceId
    public Optional<AttendanceDto> getAttendanceById(String attendanceId) {
        Optional<AttendanceEntity> attendances = attendanceRepository.findById(attendanceId);
        if (attendances.isPresent()) {
            return Optional.of(convertToDTO(attendances.get()));
        } else {
            logger.warn("Attendance with ID {} not found", attendanceId);
            return Optional.empty();
        }
    }
    
    //get by employeeId
    public List<AttendanceDto> getEmployeeId(String username) {
        List<AttendanceEntity> employees = attendanceRepository.findByEmployeeId(username);

        if (employees.isEmpty()) {
            logger.warn("Attendance with ID {} not found", username);
            return Collections.emptyList();
        }

        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    } 
    
    
    
 // Update list by id
    public AttendanceDto updateAttendances(String attendanceId, AttendanceDto attendanceDto) {
        Optional<AttendanceEntity> existingAttendanceOptional = attendanceRepository.findById(attendanceId);
        if (existingAttendanceOptional.isPresent()) {
        	AttendanceEntity existingAttendance = existingAttendanceOptional.get();
//            existingAttendance.setClockIn(attendanceDto.getClockIn());
            existingAttendance.setClockOut(attendanceDto.getClockOut());
            existingAttendance.setDate(attendanceDto.getDate());
            existingAttendance.setClockOutLocation(attendanceDto.getClockOutLocation());
        	modelMapper.map(attendanceDto, existingAttendanceOptional);
            AttendanceEntity updatedAttendance= attendanceRepository.save(existingAttendance);
            logger.info("Updated Attendance with ID: {}", updatedAttendance.getAttendanceId());
            return convertToDTO(updatedAttendance);
        } else {
            logger.warn("Attendance with ID {} not found for update", attendanceId);
            return null;
        }
    }
    
    
    
    // Update list by Name And Date
    public AttendanceDto updateAttendancesByEmployeeNameAndDate(String employeeName, LocalDate date, AttendanceDto attendanceDto) {
        Optional<AttendanceEntity> existingAttendanceOptional = attendanceRepository.findByEmployeeNameAndDate(employeeName,date);
        if (existingAttendanceOptional.isPresent()) {
        	AttendanceEntity existingAttendance = existingAttendanceOptional.get();
//            existingAttendance.setClockIn(attendanceDto.getClockIn());
            existingAttendance.setClockOut(attendanceDto.getClockOut());
            existingAttendance.setDate(attendanceDto.getDate());
            existingAttendance.setClockOutLocation(attendanceDto.getClockOutLocation());
        	modelMapper.map(attendanceDto, existingAttendanceOptional);
            AttendanceEntity updatedAttendance= attendanceRepository.save(existingAttendance);
            logger.info("Updated Attendance with name and date: {}", updatedAttendance.getAttendanceId());
            return convertToDTO(updatedAttendance);
        } else {
            logger.warn("Attendance with name and date {} not found for update", employeeName);
            return null;
        }
    }
    
    
    // Update list by EmployeeId And Date
    public AttendanceDto updateAttendancesByEmployeeIdAndDate(String username, LocalDate date, AttendanceDto attendanceDto) {
        Optional<AttendanceEntity> existingAttendanceOptional = attendanceRepository.findByEmployeeIdAndDate(username,date);
        if (existingAttendanceOptional.isPresent()) {
        	AttendanceEntity existingAttendance = existingAttendanceOptional.get();
//            existingAttendance.setClockIn(attendanceDto.getClockIn());
            existingAttendance.setClockOut(attendanceDto.getClockOut());
            existingAttendance.setDate(attendanceDto.getDate());
            existingAttendance.setClockOutLocation(attendanceDto.getClockOutLocation());
        	modelMapper.map(attendanceDto, existingAttendanceOptional);
            AttendanceEntity updatedAttendance= attendanceRepository.save(existingAttendance);
            logger.info("Updated Attendance with name and date: {}", updatedAttendance.getAttendanceId());
            return convertToDTO(updatedAttendance);
        } else {
            logger.warn("Attendance with name and date {} not found for update", username);
            return null;
        }
    }
    
    
    // Delete
    public void deleteAttendances(String attendanceId) {
    	attendanceRepository.deleteById(attendanceId);
        logger.info("Deleted Attendance with ID: {}", attendanceId);
    }

    //count the total Attendance
    public long countAttendances()
	 {
		 return attendanceRepository.count();
	 }
    
    //count the total Attendance of employees present today
    public long countPresentEmployeesToday()
	 {
		 return attendanceRepository.countPresentEmployeesToday();
	 }
    
    //count the total overtime for particular month and date fetch by employeeId
    public String getTotalOvertimeInMonth(String username, int year, int month) {
        LocalDate startOfMonth = LocalDate.of(year, month, 1);
        LocalDate endOfMonth = startOfMonth.plusMonths(1).minusDays(1);

        List<AttendanceEntity> attendanceList = attendanceRepository.findByEmployeeIdAndDateBetween(username, startOfMonth, endOfMonth);

        long totalOvertimeMinutes = attendanceList.stream()
                .filter(attendance -> attendance.getOverTime() != null)
                .mapToLong(attendance -> {
                    String[] overtimeParts = attendance.getOverTime().split("h ");
                    long hours = Long.parseLong(overtimeParts[0]);
                    long minutes = Long.parseLong(overtimeParts[1].replace("min", ""));
                    return hours * 60 + minutes;
                })
                .sum();

        long totalOvertimeHours = totalOvertimeMinutes / 60;
        long totalOvertimeMinutesRemainder = totalOvertimeMinutes % 60;

        return String.format("%dh %02dmin", totalOvertimeHours, totalOvertimeMinutesRemainder);
    }
    
    //count total login times in a month
    public int getNumberOfLoginDaysForMonth(int month, int year, String username) {
        List<LocalDate> loginDates = attendanceRepository.getDistinctLoginDatesForMonth(month, year, username);
        return loginDates.size();
    }                    
      
	// Helper method to convert AttendanceDTo to AttendanceEntity
    private AttendanceEntity convertToEntity(AttendanceDto attendanceDto)
    {
    	return modelMapper.map(attendanceDto, AttendanceEntity.class);
    }

    // Helper method to convert AttendanceEntity entity to AttendanceDTo
    private AttendanceDto convertToDTO(AttendanceEntity attendanceEntity) {
        return modelMapper.map(attendanceEntity, AttendanceDto.class);
    } 

}