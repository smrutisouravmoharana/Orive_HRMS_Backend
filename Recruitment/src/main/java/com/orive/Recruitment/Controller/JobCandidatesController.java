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

import com.orive.Recruitment.Dto.JobCandidatesDto;
import com.orive.Recruitment.Service.JobCandidatesService;




@RestController
@RequestMapping(value = "jobcandidates")
@CrossOrigin(origins = "*")
public class JobCandidatesController {
	
	private static final Logger logger = LoggerFactory.getLogger(JobCandidatesController.class);
	
	 @Autowired
	    private JobCandidatesService jobCandidatesService;
	 
	// Create a new JobCandidates
		  @PostMapping("/create/jobcandidates")
		  public ResponseEntity<JobCandidatesDto> createJobCandidates(@RequestBody JobCandidatesDto jobCandidatesDto) {
			  JobCandidatesDto createdJobCandidates = jobCandidatesService.createJobCandidates(jobCandidatesDto);
		      logger.info("Created JobCandidates with id: {}", createdJobCandidates.getCandidatesFullName());
		      return new ResponseEntity<>(createdJobCandidates, HttpStatus.CREATED);
		  }

		  
		  // Get all JobCandidates  
		  @GetMapping("/get/jobcandidates")
		  public ResponseEntity<List<JobCandidatesDto>> getAllJobCandidates() {
		      List<JobCandidatesDto> jobCandidates = jobCandidatesService.getAllJobCandidates();
		      logger.info("Retrieved {} JobCandidates from the database", jobCandidates.size());
		      return new ResponseEntity<>(jobCandidates, HttpStatus.OK);
		  }

		  // Get JobCandidates by ID
		  @GetMapping("/get/{jobCandidatesId}")
		  public ResponseEntity<JobCandidatesDto> getJobCandidatesId(@PathVariable Long jobCandidatesId) {
		      Optional<JobCandidatesDto> jobCandidates = jobCandidatesService.getJobCandidatesId(jobCandidatesId);
		      if (jobCandidates.isPresent()) {
		          logger.info("Retrieved JobCandidates with ID: {}", jobCandidatesId);
		          return new ResponseEntity<>(jobCandidates.get(), HttpStatus.OK);
		      } else {
		          logger.warn("JobCandidates with ID {} not found", jobCandidatesId);
		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		  }

		  // Update JobCandidates by ID
		  @PutMapping("/update/{jobCandidatesId}")
		  public ResponseEntity<JobCandidatesDto> updateJobCandidates(@PathVariable Long jobCandidatesId, @RequestBody JobCandidatesDto updatedJobCandidatesDto) {
			  JobCandidatesDto updatedJobCandidates = jobCandidatesService.updateJobCandidates(jobCandidatesId, updatedJobCandidatesDto);
		      if (updatedJobCandidates != null) {
		          logger.info("Updated JobCandidates with ID: {}", jobCandidatesId);
		          return new ResponseEntity<>(updatedJobCandidates, HttpStatus.OK);
		      } else {
		          logger.warn("JobCandidates with ID {} not found for update", updatedJobCandidates);
		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		  }
		  
		  // Delete JobCandidates by ID
		  @DeleteMapping("/delete/{jobCandidatesId}")
		  public ResponseEntity<Void> deleteJobCandidates(@PathVariable Long jobCandidatesId) {
			   jobCandidatesService.deleteJobCandidates(jobCandidatesId);
		      logger.info("Deleted JobCandidates with ID: {}", jobCandidatesId);
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
			    
			    @GetMapping("/count/jobcandidates")
			    public long countJobCandidates()
			    {
			    	return jobCandidatesService.countJobCandidates();
			    }
}
