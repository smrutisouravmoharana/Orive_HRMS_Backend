package com.orive.Payroll.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.orive.Payroll.Dto.PaySlipGenerateDto;
import com.orive.Payroll.Service.PaySlipGenerateService;
//import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping(value = "payslipgenerate")
@CrossOrigin(origins = "*")
public class PaySlipGenerateController {

	private static final Logger logger = LoggerFactory.getLogger(PaySlipGenerateController.class);

    @Autowired
    private PaySlipGenerateService paySlipGenerateService;
 // Create a new AdvanceSalery
    @PostMapping("/create/paySlipGenerate")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<PaySlipGenerateDto> createAdvanceSalery(@RequestBody PaySlipGenerateDto advanceSaleryDto ) {
    	PaySlipGenerateDto createdadvanceSalery = paySlipGenerateService.createPaySlipGenerate(advanceSaleryDto);
        logger.info("Created payslipgenerate with name: {}", createdadvanceSalery.getEmployeeName());
        return new ResponseEntity<>(createdadvanceSalery, HttpStatus.CREATED);
    }

    // Get all AdvanceSalery   
    @GetMapping("/get/paySlipGenerate")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<PaySlipGenerateDto>> getAllAdvanceSalery() {
        List<PaySlipGenerateDto> advanceSalery = paySlipGenerateService.getAllPaySlipGenerate();
        logger.info("Retrieved {} payslipgenerate from the database", advanceSalery.size());
        return new ResponseEntity<>(advanceSalery, HttpStatus.OK);
    }

    // Get AdvanceSalery by ID
    @GetMapping("/get/{PaySlipGenerateId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<PaySlipGenerateDto> getAdvanceSaleryById(@PathVariable String PaySlipGenerateId) {
        Optional<PaySlipGenerateDto> advanceSalery = paySlipGenerateService.getPaySlipGenerateById(PaySlipGenerateId);
        if (advanceSalery.isPresent()) {
            logger.info("Retrieved payslipgenerate with ID: {}", PaySlipGenerateId);
            return new ResponseEntity<>(advanceSalery.get(), HttpStatus.OK);
        } else {
            logger.warn("payslipgenerate with ID {} not found", PaySlipGenerateId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
 // Get AdvanceSalery by Employee ID
    @GetMapping("/findpayslipgenerate/{username}")
  //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
    public ResponseEntity<List<PaySlipGenerateDto>> getPaySlipGenerateByUsername(@PathVariable String username) {
        logger.info("Getting payslipgenerate for username: {}", username);
        List<PaySlipGenerateDto> transfers = paySlipGenerateService.getPaySlipGenerateByUsername(username);
        logger.info("Found {} payslipgenerate for username: {}", transfers.size(), username);
        return ResponseEntity.ok(transfers);
    }

//    // Update AdvanceSalery by ID
//    @PutMapping("/update/{PaySlipGenerateId}")
 // @PreAuthorize("hasRole('client_HR')")
//    public ResponseEntity<PaySlipGenerateDto> updateAdvanceSalery(@PathVariable Long PaySlipGenerateId, @RequestBody PaySlipGenerateDto updatedAdvanceSaleryDto) {
//    	PaySlipGenerateDto updatedAdvanceSalery = paySlipGenerateService.update(PaySlipGenerateId, updatedAdvanceSaleryDto);
//        if (updatedAdvanceSalery != null) {
//            logger.info("Updated AdvanceSalery with ID: {}", PaySlipGenerateId);
//            return new ResponseEntity<>(updatedAdvanceSalery, HttpStatus.OK);
//        } else {
//            logger.warn("AdvanceSalery with ID {} not found for update", PaySlipGenerateId);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
    


    // Delete AdvanceSalery by ID
    @DeleteMapping("/delete/{PaySlipGenerateId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deletePaySlipGenerate(@PathVariable String PaySlipGenerateId) {
  	  paySlipGenerateService.deletePaySlipGenerate(PaySlipGenerateId);
        logger.info("Deleted payslipgenerate with ID: {}", PaySlipGenerateId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
 // Count AdvanceSalery 
	    @GetMapping("/count/paySlipGenerate")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countPaySlipGenerate()
	    {
	    	return paySlipGenerateService.countPaySlipGenerate();
	    }
	    
	 // Get total AdvanceSalery for departments in month
	    @GetMapping("/total-net-salary/{year}/{month}")
	    public Map<String, Double> getTotalNetSalaryForDepartmentsInMonth(@PathVariable int year, @PathVariable int month) {
	        return paySlipGenerateService.getTotalNetSalaryForDepartmentsInMonth(year, month);
	    }
}