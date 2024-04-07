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

import com.orive.Recruitment.Dto.JobPostDto;
import com.orive.Recruitment.Service.JobPostService;


@RestController
@RequestMapping(value = "jobpost")
@CrossOrigin(origins = "*")
public class JobPostController {
	
	private static final Logger logger = LoggerFactory.getLogger(JobPostController.class);
	
	 @Autowired
	    private JobPostService jobPostService;
	 
	// Create a new JobPost
		  @PostMapping("/create/jobpost")
		  public ResponseEntity<JobPostDto> createJobPosts(@RequestBody JobPostDto jobPostDto) {
			  JobPostDto createdJobPost = jobPostService.createJobPost(jobPostDto);
		      logger.info("Created JobPost with id: {}", createdJobPost.getJobTitle());
		      return new ResponseEntity<>(createdJobPost, HttpStatus.CREATED);
		  }

		  
		  // Get all JobPost  
		  @GetMapping("/get/jobpost")
		  public ResponseEntity<List<JobPostDto>> getAllJobPosts() {
		      List<JobPostDto> jobPost = jobPostService.getAllJobPost();
		      logger.info("Retrieved {} JobPost from the database", jobPost.size());
		      return new ResponseEntity<>(jobPost, HttpStatus.OK);
		  }

		  // Get JobPost by ID
		  @GetMapping("/get/{jobPostId}")
		  public ResponseEntity<JobPostDto> getJobPostDtoId(@PathVariable Long jobPostId) {
		      Optional<JobPostDto> jobPost = jobPostService.getJobPostId(jobPostId);
		      if (jobPost.isPresent()) {
		          logger.info("Retrieved JobPost with ID: {}", jobPostId);
		          return new ResponseEntity<>(jobPost.get(), HttpStatus.OK);
		      } else {
		          logger.warn("JobPost with ID {} not found", jobPostId);
		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		  }

		  // Update JobPost by ID
		  @PutMapping("/update/{jobPostId}")
		  public ResponseEntity<JobPostDto> updateJobPost(@PathVariable Long jobPostId, @RequestBody JobPostDto updatedJobPostDto) {
			  JobPostDto updatedJobPost = jobPostService.updateJobPost(jobPostId, updatedJobPostDto);
		      if (updatedJobPost != null) {
		          logger.info("Updated JobPost with ID: {}", jobPostId);
		          return new ResponseEntity<>(updatedJobPost, HttpStatus.OK);
		      } else {
		          logger.warn("JobPost with ID {} not found for update", updatedJobPost);
		          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		      }
		  }
		  
		  // Delete JobPost by ID
		  @DeleteMapping("/delete/{jobPostId}")
		  public ResponseEntity<Void> deleteJobPost(@PathVariable Long jobPostId) {
			   jobPostService.deleteJobPost(jobPostId);
		      logger.info("Deleted JobPost with ID: {}", jobPostId);
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
			    
			    @GetMapping("/count/jobpost")
			    public long countJobPost()
			    {
			    	return jobPostService.countJobPost();
			    }

	

}
