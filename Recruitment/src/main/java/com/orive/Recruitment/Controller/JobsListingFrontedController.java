package com.orive.Recruitment.Controller;

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

import com.orive.Recruitment.Dto.JobsListingFrontendDto;
import com.orive.Recruitment.Service.JobsListingFrontendService;



@RestController
@RequestMapping(value = "jobslistingfronted")
@CrossOrigin(origins = "*")
public class JobsListingFrontedController {
	
	private static final Logger logger = LoggerFactory.getLogger(JobsListingFrontedController.class);
	
	
	 @Autowired
	    private JobsListingFrontendService jobsListingFrontendService;
	 
	 
	// Create a new JobsListingFrontend
	  @PostMapping("/create/jobslistingfronted")
	  public ResponseEntity<JobsListingFrontendDto> createJobsListingFrontend(@RequestBody JobsListingFrontendDto jobsListingFrontendDto) {
		  JobsListingFrontendDto createdJobsListingFrontend = jobsListingFrontendService.createJobsListingFrontend(jobsListingFrontendDto);
	      logger.info("Created JobsListingFrontend with id: {}", createdJobsListingFrontend.getJobTitle());
	      return new ResponseEntity<>(jobsListingFrontendDto, HttpStatus.CREATED);
	  }

	  
	  // Get all JobsListingFrontend
	  @GetMapping("/get/jobslistingfronted")
	  public ResponseEntity<List<JobsListingFrontendDto>> getAllJobsListingFrontend() {
	      List<JobsListingFrontendDto> jobsListingFrontend = jobsListingFrontendService.getAllJobsListingFrontend();
	      logger.info("Retrieved {} JobsListingFrontend from the database", jobsListingFrontend.size());
	      return new ResponseEntity<>(jobsListingFrontend, HttpStatus.OK);
	  }

	  // Get JobsListingFrontend by ID
	  @GetMapping("/get/{jobsListingFrontendId}")
	  public ResponseEntity<JobsListingFrontendDto> getJobsListingFrontendId(@PathVariable Long jobsListingFrontendId) {
	      Optional<JobsListingFrontendDto> jobsListingFrontend = jobsListingFrontendService.getJobsListingFrontendId(jobsListingFrontendId);
	      if (jobsListingFrontend.isPresent()) {
	          logger.info("Retrieved JobsListingFrontend with ID: {}", jobsListingFrontendId);
	          return new ResponseEntity<>(jobsListingFrontend.get(), HttpStatus.OK);
	      } else {
	          logger.warn("JobsListingFrontend with ID {} not found", jobsListingFrontendId);
	          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	  }

	  // Update JobsListingFrontend by ID
	  @PutMapping("/update/{jobsListingFrontendId}")
	  public ResponseEntity<JobsListingFrontendDto> updateJobsListingFrontend(@PathVariable Long jobsListingFrontendId, @RequestBody JobsListingFrontendDto updatedJobsListingFrontendDto) {
		  JobsListingFrontendDto updatedJobsListingFrontend = jobsListingFrontendService.updateJobsListingFrontend(jobsListingFrontendId, updatedJobsListingFrontendDto);
	      if (updatedJobsListingFrontend != null) {
	          logger.info("Updated JobsListingFrontend with ID: {}", jobsListingFrontendId);
	          return new ResponseEntity<>(updatedJobsListingFrontend, HttpStatus.OK);
	      } else {
	          logger.warn("JobsListingFrontend with ID {} not found for update", updatedJobsListingFrontend);
	          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	  }
	  
	  // Delete JobsListingFrontend by ID
	  @DeleteMapping("/delete/{jobsListingFrontendId}")
	  public ResponseEntity<Void> deleteJobsListingFrontend(@PathVariable Long jobsListingFrontendId) {
		   jobsListingFrontendService.deleteJobsListingFrontend(jobsListingFrontendId);
	      logger.info("Deleted JobsListingFrontend with ID: {}", jobsListingFrontendId);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
		    
		    @GetMapping("/count/jobslistingfronted")
		    public long countJobsListingFrontend()
		    {
		    	return jobsListingFrontendService.countJobsListingFrontend();
		    }


}
