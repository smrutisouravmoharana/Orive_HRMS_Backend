package com.orive.Recruitment.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Recruitment.Dto.JobInterviewDto;
import com.orive.Recruitment.Dto.JobPostDto;
import com.orive.Recruitment.Entity.JobInterviewEntity;
import com.orive.Recruitment.Entity.JobPostEntity;
import com.orive.Recruitment.Repository.JobInterviewRepository;

@Service
public class JobInterviewService {
	
	private static final Logger logger = LoggerFactory.getLogger(JobInterviewService.class);
	
	@Autowired
	private JobInterviewRepository jobInterviewRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public JobInterviewDto createJobInterview (JobInterviewDto jobInterviewDto) {
    	JobInterviewEntity  jobInterviewEntity = convertToEntity(jobInterviewDto);
    	JobInterviewEntity savedJobInterview = jobInterviewRepository.save(jobInterviewEntity);
        logger.info("Created JobInterview with ID: {}", savedJobInterview.getJobInterviewId());
        return convertToDTO(savedJobInterview);
    }

    // Read
    public List<JobInterviewDto> getAllJobInterview() {
        List<JobInterviewEntity>jobInterviewEntities = jobInterviewRepository.findAll();
        logger.info("Retrieved {} JobInterview from the database", jobInterviewEntities.size());
        return jobInterviewEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by JobInterviewId
    public Optional<JobInterviewDto> getJobInterviewId(Long JobInterviewId) {
        Optional<JobInterviewEntity> jobInterview = jobInterviewRepository.findById(JobInterviewId);
        if (jobInterview.isPresent()) {
            return Optional.of(convertToDTO(jobInterview.get()));
        } else {
            logger.warn("JobInterview with ID {} not found", JobInterviewId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public JobInterviewDto updateJobInterview(Long JobInterviewId, JobInterviewDto jobInterviewDto) {
        Optional<JobInterviewEntity> existingJobInterviewOptional = jobInterviewRepository.findById(JobInterviewId);
        if (existingJobInterviewOptional.isPresent()) {
        	JobInterviewEntity existingJobInterview= existingJobInterviewOptional.get();
            modelMapper.map(jobInterviewDto, existingJobInterviewOptional);
            JobInterviewEntity updatedJobInterview = jobInterviewRepository.save(existingJobInterview);
            logger.info("Updated JobInterview with ID: {}", updatedJobInterview.getJobInterviewId());
            return convertToDTO(updatedJobInterview);
        } else {
            logger.warn("JobInterview with ID {} not found for update", JobInterviewId);
            return null;
        }
    }
    
    // Delete
    public void deleteJobInterview(Long JobInterviewId) {
    	jobInterviewRepository.deleteById(JobInterviewId);
        logger.info("Deleted JobInterview with ID: {}", JobInterviewId);
    }

    //count the total JobInterview
    public long countJobInterview()
	 {
		 return jobInterviewRepository.count();
	 }
    
	// Helper method to convert JobInterviewDTo to JobInterviewEntity
    private JobInterviewEntity convertToEntity(JobInterviewDto jobInterviewDto)
    {
    	return modelMapper.map(jobInterviewDto, JobInterviewEntity.class);
    }

 // Helper method to convert JobInterviewEntity entity to JobInterviewDTo
    private JobInterviewDto convertToDTO(JobInterviewEntity jobInterviewEntity) {
        return modelMapper.map(jobInterviewEntity, JobInterviewDto.class);
    }

}
