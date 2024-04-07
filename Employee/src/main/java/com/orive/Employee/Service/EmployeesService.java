package com.orive.Employee.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.orive.Employee.Dto.EmployeesDto;
import com.orive.Employee.Entity.EmployeesEntity;
import com.orive.Employee.Enum.Status;
import com.orive.Employee.Exceptions.ResourceNotFoundException;
import com.orive.Employee.Repository.EmployeesRepository;
import com.orive.Employee.util.ImageUploadUtils;
import com.orive.Employee.util.PdfUploadUtils;




@Service
public class EmployeesService {

	 private static final Logger logger=LoggerFactory.getLogger(EmployeesService.class);
		
		@Autowired
		private EmployeesRepository employeesRepository;
		
		@Autowired
		private ModelMapper modelMapper;
		
		public String saveEmployeesEntity(
			    
				String employeeName,
				String username,
				String designationName,
				String email,
				Long phone,
				Long alternativePhone,
				String country,
				String city,
				int zipCode,
				String employeeRole,
				String companyType,
				String attendanceTime,
				String employeeType,
				LocalDate createdDate,
				Long accountNumber,
				String bankName,
				String ifscNumber,
				String branchName,
				double basicSalary,
				double transportAllowance,
				double grossSalary,
				Long tinNumber,
				double hraAllowances,
				double otherAllowances,
				double pfAllowances,
				double daAllowances,
				double medicalAllowances,
				double otherInsurance,
				double tax,
				String subDepartment,
				String position,
				String dutyType,
				LocalDate hireDate,
				LocalDate joiningDate,
				String rateType,
				int rateNumber,
				int monthlyWorkHours,
				String payFrequency,
				String medical,
				String family,
				String transportation,
				String others,
				String teamLeaderName,
				String reportingTo,
				LocalDate dateOfBirth,
				String gender,
				String maritalStatus,
				String workInCity,
				String cityOfResidence,
				String workPermit,
				MultipartFile filePhoto,
				String businessEmail,
				Long homePhone,
				Long cellPhone,
				String userEmailOrName,
				String password,
				MultipartFile fileDocument,
				String status) {
			// Retrieve a list of employees with the given username
	        List<EmployeesEntity> existingEmployees = employeesRepository.findEmployeeByUsername(username);

	        // Check if the list is not empty
	        if (!existingEmployees.isEmpty()) {
	            return "Error: An employee with the username '" + username + "' already exists";
	        }
			
			try {
				EmployeesEntity employeeData = employeesRepository.save(EmployeesEntity.builder()
						
						
						.employeeName(employeeName)
						.username(username)
						.designationName(designationName)
						.email(email)
						.phone(phone)
						.alternativePhone(alternativePhone)
						.country(country)
						.city(city)
						.zipCode(zipCode)
						.employeeRole(employeeRole)
						.companyType(companyType)
						.attendanceTime(attendanceTime)
						.employeeType(employeeType)
						.createdDate(createdDate)
						.accountNumber(accountNumber)
						.bankName(bankName)
						.ifscNumber(ifscNumber)
						.branchName(branchName)
						.basicSalary(basicSalary)
						.transportAllowance(transportAllowance)
						.grossSalary(grossSalary)
						.tinNumber(tinNumber)
						.hraAllowances(hraAllowances)
						.otherAllowances(otherAllowances)
						.pfAllowances(pfAllowances)
						.daAllowances(daAllowances)
						.medicalAllowances(medicalAllowances)
						.otherInsurance(otherInsurance)
						.tax(tax)
						.subDepartment(subDepartment)
						.position(position)
						.dutyType(dutyType)
						.hireDate(hireDate)
						.joiningDate(joiningDate)
						.rateType(rateType)
						.rateNumber(rateNumber)
						.monthlyWorkHours(monthlyWorkHours)
						.payFrequency(payFrequency)
						.medical(medical)
						.family(family)
						.transportation(transportation)
						.others(others)
						.teamLeaderName(teamLeaderName)
						.reportingTo(reportingTo)
						.dateOfBirth(dateOfBirth)
						.gender(gender)
						.maritalStatus(maritalStatus)
						.workInCity(workInCity)
						.cityOfResidence(cityOfResidence)
						.workPermit(workPermit)
						.uploadPhoto(ImageUploadUtils.compressImage(filePhoto.getBytes()))
						.businessEmail(businessEmail)
						.homePhone(homePhone)
						.cellPhone(cellPhone)
						.userEmailOrName(userEmailOrName)
						.password(password)
						.uploadDocument(fileDocument != null ? PdfUploadUtils.compressPdf(fileDocument.getBytes()) : null)
						.status(status)
						.build());
				
				 if (employeeData != null) {
			            return "File uploaded successfully: " + (fileDocument != null ? fileDocument.getOriginalFilename() : "No file attached");
			        }
				
			 } catch (Exception e) {
		            // Handle the IOException appropriately (e.g., log it, return an error message)
		            return "Error saving employee data: " + e.getMessage();
		        }

		        return "Unknown error occurred while saving employee data";
		    }
		
			
		
		//Download Logo
		public byte[] downloadImage(String username) {
		    List<EmployeesEntity> employeeList = employeesRepository.findEmployeeByUsername(username);

		    if (!employeeList.isEmpty()) {
		        EmployeesEntity dbImageData = employeeList.get(0);
		        return ImageUploadUtils.decompressImage(dbImageData.getUploadPhoto());
		    } else {
		        // Handle the case where the employee with the specified ID is not found
		        // You may choose to throw an exception or return a default image
		        return null;
		    }
		}

		
		

		
		
		//Download pdf	
		public byte[] downloadPdf(String username) {
		    List<EmployeesEntity> employeeList = employeesRepository.findEmployeeByUsername(username);

		    if (!employeeList.isEmpty()) {
		        EmployeesEntity dbPdfData = employeeList.get(0);
		        return PdfUploadUtils.decompressPdf(dbPdfData.getUploadDocument());
		    } else {
		        // Handle the case where the employee with the specified ID is not found
		        // You may choose to throw an exception or return a default PDF
		        return null;
		    }
		}
			
		

	  // Read
	    public List<EmployeesDto> getAllEmployees() {
	        List<EmployeesEntity> employeesEntities = employeesRepository.findAll();
	        logger.info("Retrieved {} Employees from the database", employeesEntities.size());
	        return employeesEntities.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }
	    
//	    //get by employeeSerialNo
//	    public Optional<EmployeesDto> getEmployeeSerialNo(Long employeeSerialNo) {
//	        Optional<EmployeesEntity> employee = employeesRepository.findById(employeeSerialNo);
//	        if (employee.isPresent()) {
//	            return Optional.of(convertToDTO(employee.get()));
//	        } else {
//	            logger.warn("Employees with ID {} not found", employeeSerialNo);
//	            return Optional.empty();
//	        }
//	    }
	    
	    //get by EmployeesName
	    public List<EmployeesEntity> getEmployeesByName(String employeeName) {
	        List<EmployeesEntity> employees = employeesRepository.findEmployeeByEmployeeName(employeeName);

	        if (employees.isEmpty()) {
	            logger.warn("No employees found with name: {}", employeeName);
	            throw new ResourceNotFoundException("No employees found with name: " + employeeName);
	        }
	        return employees;
	    }
	    
	    
	  //get by EmployeesId
	    public List<EmployeesEntity> getEmployeesByEmployeeId(String username) {
	        List<EmployeesEntity> employees = employeesRepository.findEmployeeByUsername(username);
	        if (employees.isEmpty()) {
	            logger.warn("No employees found with username: {}", username);
	            throw new ResourceNotFoundException("No employees found with username: " + username);
	        }
	        return employees;
	    }
	 
	   
	    // Fetch employee details by userEmailOrName and password
	    public EmployeesDto getEmployeeByCredentials(String userEmailOrName, String password) {
	        Optional<EmployeesEntity> employeeOptional = employeesRepository
	                .findByUserEmailOrNameAndPassword(userEmailOrName, password);

	        if (employeeOptional.isPresent()) {
	            EmployeesEntity employeeEntity = employeeOptional.get();
	            return convertToDTO(employeeEntity);
	        } else {
	        	logger.warn("Employees with Credentials {} not found", userEmailOrName, password);
	            return null;
	        }
	    }
	 
	    
	    
//	 // Update list by EmployeeSerialNo
//	    public EmployeesDto updateEmployeeSerialNo(Long employeeSerialNo, EmployeesDto employeesDto) {
//	        Optional<EmployeesEntity> existingEmployeesOptional = employeesRepository.findById(employeeSerialNo);
//	        if (existingEmployeesOptional.isPresent()) {
//	        	EmployeesEntity existingEmployees = existingEmployeesOptional.get();
//	        	existingEmployees.setEmployeeName(employeesDto.getEmployeeName());
//	        	existingEmployees.setPhone(employeesDto.getPhone());
//	        	existingEmployees.setAccountNumber(employeesDto.getAccountNumber());
//	        	existingEmployees.setBasicSalary(employeesDto.getBasicSalary());
//	        	existingEmployees.setTransportAllowance(employeesDto.getTransportAllowance());
//	        	existingEmployees.setHraAllowances(employeesDto.getHraAllowances());
//	        	existingEmployees.setOtherAllowances(employeesDto.getOtherAllowances());
//	        	existingEmployees.setPfAllowances(employeesDto.getPfAllowances());
//	        	existingEmployees.setDaAllowances(employeesDto.getDaAllowances());
//	        	existingEmployees.setMedicalAllowances(employeesDto.getMedicalAllowances());
//	        	existingEmployees.setOtherInsurance(employeesDto.getOtherInsurance());
//	        	existingEmployees.setTax(employeesDto.getTax());
//	        	existingEmployees.setStatus(employeesDto.getStatus());
//	        	modelMapper.map(employeesDto, existingEmployeesOptional);
//	            EmployeesEntity updatedEmployees = employeesRepository.save(existingEmployees);
//	            logger.info("Updated Employees with EmployeeSerialNo: {}", updatedEmployees.getEmployeeId());
//	            return convertToDTO(updatedEmployees);
//	        } else {
//	            logger.warn("Employees with EmployeeSerialNo {} not found for update", employeeSerialNo);
//	            return null;
//	        }
//	    }
	    
	    
 //Update list by EmployeeId
	    public EmployeesDto updateEmployees(String username, EmployeesDto employeesDto) {
	        List<EmployeesEntity> existingEmployeesList = employeesRepository.findEmployeeByUsername(username);

	        if (!existingEmployeesList.isEmpty()) {
	            EmployeesEntity existingEmployees = existingEmployeesList.get(0);

	            existingEmployees.setEmployeeName(employeesDto.getEmployeeName());
	            existingEmployees.setPhone(employeesDto.getPhone());
	            existingEmployees.setAccountNumber(employeesDto.getAccountNumber());
	            existingEmployees.setBasicSalary(employeesDto.getBasicSalary());
	            existingEmployees.setTransportAllowance(employeesDto.getTransportAllowance());
	            existingEmployees.setHraAllowances(employeesDto.getHraAllowances());
	            existingEmployees.setOtherAllowances(employeesDto.getOtherAllowances());
	            existingEmployees.setPfAllowances(employeesDto.getPfAllowances());
	            existingEmployees.setDaAllowances(employeesDto.getDaAllowances());
	            existingEmployees.setMedicalAllowances(employeesDto.getMedicalAllowances());
	            existingEmployees.setOtherInsurance(employeesDto.getOtherInsurance());
	            existingEmployees.setTax(employeesDto.getTax());
	            existingEmployees.setStatus(employeesDto.getStatus());

	            EmployeesEntity updatedEmployees = employeesRepository.save(existingEmployees);
	            logger.info("Updated Employees with username: {}", updatedEmployees.getUsername());
	            return convertToDTO(updatedEmployees);
	        } else {
	            logger.warn("Employees with username {} not found for update", username);
	            return null;
	        }
	    }

	    
//       // Delete Employee By Using EmployeeSerialNo
//	    public void deleteEmployeesBySerialNo(Long employeeSerialNo) {
//	    	employeesRepository.deleteById(employeeSerialNo);
//	        logger.info("Deleted Employees with ID: {}", employeeSerialNo);
//	    }
	    
	    
       // Delete Employee By Using EmployeeId
	    public void deleteEmployeeByEmployeeId(String username) {
	        List<EmployeesEntity> employeeList = employeesRepository.findEmployeeByUsername(username);
	        if (!employeeList.isEmpty()) {
	            EmployeesEntity employeeToDelete = employeeList.get(0);
	            employeesRepository.delete(employeeToDelete);
	            logger.info("Deleted Employee with username: {}", username);
	        } else {
	            logger.warn("Employee with username {} not found for delete", username);
	            // You may choose to throw an exception or handle it according to your application logic
	        }
	    }
	    
	    

	    //count the total Employees
	    public long countEmployees()
		 {
			 return employeesRepository.count();
		 }
	    
	    
	    //count the male from employee
	    
	    public long countEmployeesByMale()
		 {
			 return employeesRepository.countEmployeeByMale();
		 }
	    
	    
       //count the female from employee
	    
	    public long countEmployeesByFemale()
		 {
			 return employeesRepository.countEmployeeByFemale();
		 }
	    
	    //count how many roles
	    public long countDistinctEmployeeRoles() {
	        List<String> distinctRoles = employeesRepository.findDistinctEmployeeRoles();
	        return distinctRoles.size();
	    }
	    	    
		// Helper method to convert EmployeesDTo to EmployeesEntity
	    private EmployeesEntity convertToEntity(EmployeesDto employeesDto)
	    {
	    	return modelMapper.map(employeesDto, EmployeesEntity.class);
	    }

	    
	    // Helper method to convert EmployeesEntity to EmployeesDTo
	    private EmployeesDto convertToDTO(EmployeesEntity employeesEntity) {
	        return modelMapper.map(employeesEntity, EmployeesDto.class);
	    } 
}
