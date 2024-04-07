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

import com.orive.Recruitment.Dto.JobInterviewDto;
import com.orive.Recruitment.Service.JobInterviewService;


@RestController
@RequestMapping(value = "jobinterview")
@CrossOrigin(origins = "*")
public class JobInterviewController {
	
	private static final Logger logger = LoggerFactory.getLogger(JobInterviewController.class);
	
	
	 @Autowired
	    private JobInterviewService jobInterviewService;
	 
	 
	// Create a new JobInterview
	  @PostMapping("/create/jobinterview")
	  public ResponseEntity<JobInterviewDto> createJobInterviews(@RequestBody JobInterviewDto jobInterviewDto) {
		  JobInterviewDto createdJobInterview = jobInterviewService.createJobInterview(jobInterviewDto);
	      logger.info("Created JobInterview with id: {}", createdJobInterview.getJobTitle());
	      return new ResponseEntity<>(createdJobInterview, HttpStatus.CREATED);
	  }

	  
	  // Get all JobInterview
	  @GetMapping("/get/jobinterview")
	  public ResponseEntity<List<JobInterviewDto>> getAllJobInterview() {
	      List<JobInterviewDto> jobInterview = jobInterviewService.getAllJobInterview();
	      logger.info("Retrieved {} JobInterview from the database", jobInterview.size());
	      return new ResponseEntity<>(jobInterview, HttpStatus.OK);
	  }

	  // Get JobInterview by ID
	  @GetMapping("/get/{jobInterviewId}")
	  public ResponseEntity<JobInterviewDto> getJobInterviewId(@PathVariable Long jobInterviewId) {
	      Optional<JobInterviewDto> jobInterview = jobInterviewService.getJobInterviewId(jobInterviewId);
	      if (jobInterview.isPresent()) {
	          logger.info("Retrieved JobInterview with ID: {}", jobInterviewId);
	          return new ResponseEntity<>(jobInterview.get(), HttpStatus.OK);
	      } else {
	          logger.warn("JobInterview with ID {} not found", jobInterviewId);
	          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	  }

	  // Update JobInterview by ID
	  @PutMapping("/update/{jobInterviewId}")
	  public ResponseEntity<JobInterviewDto> updateJobInterview(@PathVariable Long jobInterviewId, @RequestBody JobInterviewDto updatedJobInterviewDto) {
		  JobInterviewDto updatedJobInterview = jobInterviewService.updateJobInterview(jobInterviewId, updatedJobInterviewDto);
	      if (updatedJobInterview != null) {
	          logger.info("Updated JobInterview with ID: {}", jobInterviewId);
	          return new ResponseEntity<>(updatedJobInterview, HttpStatus.OK);
	      } else {
	          logger.warn("JobInterview with ID {} not found for update", updatedJobInterview);
	          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	  }
	  
	  // Delete JobInterview by ID
	  @DeleteMapping("/delete/{jobInterviewId}")
	  public ResponseEntity<Void> deleteJobInterview(@PathVariable Long jobInterviewId) {
		   jobInterviewService.deleteJobInterview(jobInterviewId);
	      logger.info("Deleted JobInterview with ID: {}", jobInterviewId);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
		    
		    @GetMapping("/count/jobinterview")
		    public long countJobInterview()
		    {
		    	return jobInterviewService.countJobInterview();
		    }


}
