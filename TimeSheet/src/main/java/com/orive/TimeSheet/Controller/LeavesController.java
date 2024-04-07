package com.orive.TimeSheet.Controller;

import java.util.List;
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

import com.orive.TimeSheet.Dto.LeaveDto;
import com.orive.TimeSheet.Entity.LeavesEntity;
import com.orive.TimeSheet.Service.LeavesService;
//import org.springframework.security.access.prepost.PreAuthorize;




@RestController
@RequestMapping(value = "leaves")
@CrossOrigin(origins = "*")
public class LeavesController {

	private static final Logger logger = LoggerFactory.getLogger(LeavesController.class);

    @Autowired
    private LeavesService leavesService;

  
  	// Create a new Leaves
      @PostMapping("/create/leaves")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<LeaveDto> createLeaves(@RequestBody LeaveDto leaveDto) {
    	  LeaveDto createdLeaves = leavesService.createLeaves(leaveDto);
          logger.info("Created Leaves with name: {}", createdLeaves.getEmployeeName());
          return new ResponseEntity<>(createdLeaves, HttpStatus.CREATED);
      }

      // Get all Leaves     
      @GetMapping("/get/leaves")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<List<LeaveDto>> getAllLeaves() {
          List<LeaveDto> leaves = leavesService.getAllLeaves();
          logger.info("Retrieved {} Leaves from the database", leaves.size());
          return new ResponseEntity<>(leaves, HttpStatus.OK);
      }

      // Get Leaves by ID
      @GetMapping("/get/{leaveId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<LeaveDto> getLeavesById(@PathVariable String leaveId) {
          Optional<LeaveDto> leave = leavesService.getLeavesById(leaveId);
          if (leave.isPresent()) {
              logger.info("Retrieved Leaves with ID: {}", leaveId);
              return new ResponseEntity<>(leave.get(), HttpStatus.OK);
          } else {
              logger.warn("Leaves with ID {} not found", leaveId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }

      
  	// Get Employee by ID
	  @GetMapping("/employee/get/{username}")
	//@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
	    public ResponseEntity<List<LeaveDto>> getLeavesByEmployeeId(@PathVariable String username) {
	        List<LeaveDto> leaves = leavesService.getLeavesByEmployeeId(username);

	        if (leaves.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        } else {
	            return ResponseEntity.ok(leaves);
	        }
	    }
      
      // Update Leaves by ID
      @PutMapping("/update/{leaveId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<LeaveDto> updateLeaves(@PathVariable String leaveId, @RequestBody LeaveDto updatedLeaveDto) {
    	  LeaveDto updatedLeave = leavesService.updateLeaves(leaveId, updatedLeaveDto);
          if (updatedLeave != null) {
              logger.info("Updated Leaves with ID: {}", leaveId);
              return new ResponseEntity<>(updatedLeave, HttpStatus.OK);
          } else {
              logger.warn("Leaves with ID {} not found for update", leaveId);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }
         
      
   // Update projects by EmployeeId
      @PutMapping("/update/employee/{username}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<List<LeavesEntity>> updateLeavesByEmployeeId( @PathVariable String username,@RequestBody List<LeavesEntity> updatedLeaves) {

          List<LeavesEntity> updatedLeaveEntities = leavesService.updateLeavesByEmployeeId(username, updatedLeaves);

          if (!updatedLeaveEntities.isEmpty()) {
        	  logger.info("Updated Leaves with employeeId: {}", username);
              return new ResponseEntity<>(updatedLeaveEntities, HttpStatus.OK);
          } else {
        	  logger.warn("Leaves with employeeId {} not found for update", username);
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }
      
      // Delete Leaves by ID
      @DeleteMapping("/delete/{leaveId}")
   // @PreAuthorize("hasRole('client_HR')")
      public ResponseEntity<Void> deleteLeaves(@PathVariable String leaveId) {
    	  leavesService.deleteLeaves(leaveId);
          logger.info("Deleted Leaves with ID: {}", leaveId);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  	    
  	    @GetMapping("/count/leaves")
  	// @PreAuthorize("hasRole('client_HR')")
  	    public long countLeaves()
  	    {
  	    	return leavesService.countLeaves();
  	    }
}
