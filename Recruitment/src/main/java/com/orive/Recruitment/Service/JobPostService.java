package com.orive.Recruitment.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.orive.Recruitment.Dto.JobPostDto;
import com.orive.Recruitment.Entity.JobPostEntity;
import com.orive.Recruitment.Repository.JobPostRepository;



@Service
public class JobPostService {
	
	private static final Logger logger=LoggerFactory.getLogger(JobPostService.class);
	
	@Autowired
	private JobPostRepository jobPostRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public JobPostDto createJobPost(JobPostDto jobPostDto) {
    	JobPostEntity  jobPostEntity = convertToEntity(jobPostDto);
    	JobPostEntity savedJobPost = jobPostRepository.save(jobPostEntity);
        logger.info("Created JobPost with ID: {}", savedJobPost.getJobPostId());
        return convertToDTO(savedJobPost);
    }

    // Read
    public List<JobPostDto> getAllJobPost() {
        List<JobPostEntity>jobPostEntities = jobPostRepository.findAll();
        logger.info("Retrieved {} JobPost from the database", jobPostEntities.size());
        return jobPostEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by JobPostId
    public Optional<JobPostDto> getJobPostId(Long JobPostId) {
        Optional<JobPostEntity> jobPost = jobPostRepository.findById(JobPostId);
        if (jobPost.isPresent()) {
            return Optional.of(convertToDTO(jobPost.get()));
        } else {
            logger.warn("JobPost with ID {} not found", JobPostId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public JobPostDto updateJobPost(Long JobPostId, JobPostDto jobPostDto) {
        Optional<JobPostEntity> existingJobPostOptional = jobPostRepository.findById(JobPostId);
        if (existingJobPostOptional.isPresent()) {
        	JobPostEntity existingJobPost= existingJobPostOptional.get();
            modelMapper.map(jobPostDto, existingJobPostOptional);
            JobPostEntity updatedJobPosts = jobPostRepository.save(existingJobPost);
            logger.info("Updated JobPost with ID: {}", updatedJobPosts.getJobPostId());
            return convertToDTO(updatedJobPosts);
        } else {
            logger.warn("JobPost with ID {} not found for update", JobPostId);
            return null;
        }
    }
    
    // Delete
    public void deleteJobPost(Long JobPostId) {
    	jobPostRepository.deleteById(JobPostId);
        logger.info("Deleted JobPost with ID: {}", JobPostId);
    }

    //count the total JobPost
    public long countJobPost()
	 {
		 return jobPostRepository.count();
	 }
    
	// Helper method to convert JobPostDTo to JobPostEntity
    private JobPostEntity convertToEntity(JobPostDto jobPostDto)
    {
    	return modelMapper.map(jobPostDto, JobPostEntity.class);
    }

 // Helper method to convert JobPostEntity entity to JobPostDTo
    private JobPostDto convertToDTO(JobPostEntity jobPostEntity) {
        return modelMapper.map(jobPostEntity, JobPostDto.class);
    } 


}
