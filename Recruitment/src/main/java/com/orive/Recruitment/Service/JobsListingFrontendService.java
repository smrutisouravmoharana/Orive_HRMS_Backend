package com.orive.Recruitment.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Recruitment.Dto.JobsListingFrontendDto;
import com.orive.Recruitment.Entity.JobsListingFrontendEntity;
import com.orive.Recruitment.Repository.JobsListingFrontedRepository;


@Service
public class JobsListingFrontendService {
	
	private static final Logger logger = LoggerFactory.getLogger(JobsListingFrontendService.class);
	
	@Autowired
	private JobsListingFrontedRepository jobsListingFrontedRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public JobsListingFrontendDto createJobsListingFrontend (JobsListingFrontendDto jobsListingFrontendDto) {
    	JobsListingFrontendEntity  jobsListingFrontendEntity = convertToEntity(jobsListingFrontendDto);
    	JobsListingFrontendEntity savedJobsListingFrontend = jobsListingFrontedRepository.save(jobsListingFrontendEntity);
        logger.info("Created JobsListingFrontend with ID: {}", savedJobsListingFrontend.getJobsListingFrontendId());
        return convertToDTO(savedJobsListingFrontend);
    }

    // Read
    public List<JobsListingFrontendDto> getAllJobsListingFrontend() {
        List<JobsListingFrontendEntity>jobsListingFrontendEntities = jobsListingFrontedRepository.findAll();
        logger.info("Retrieved {} JobsListingFrontend from the database", jobsListingFrontendEntities.size());
        return jobsListingFrontendEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by JobsListingFrontendId
    public Optional<JobsListingFrontendDto> getJobsListingFrontendId(Long jobsListingFrontendId) {
        Optional<JobsListingFrontendEntity> jobsListingFrontend = jobsListingFrontedRepository.findById(jobsListingFrontendId);
        if (jobsListingFrontend.isPresent()) {
            return Optional.of(convertToDTO(jobsListingFrontend.get()));
        } else {
            logger.warn("JobsListingFrontend with ID {} not found", jobsListingFrontendId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public JobsListingFrontendDto updateJobsListingFrontend(Long jobsListingFrontendId, JobsListingFrontendDto jobsListingFrontendDto) {
        Optional<JobsListingFrontendEntity> existingJobsListingFrontendOptional = jobsListingFrontedRepository.findById(jobsListingFrontendId);
        if (existingJobsListingFrontendOptional.isPresent()) {
        	JobsListingFrontendEntity existingJobsListingFrontend= existingJobsListingFrontendOptional.get();
            modelMapper.map(jobsListingFrontendDto, existingJobsListingFrontendOptional);
            JobsListingFrontendEntity updatedJobsListingFrontend = jobsListingFrontedRepository.save(existingJobsListingFrontend);
            logger.info("Updated JobsListingFrontend with ID: {}", updatedJobsListingFrontend.getJobsListingFrontendId());
            return convertToDTO(updatedJobsListingFrontend);
        } else {
            logger.warn("JobsListingFrontend with ID {} not found for update", jobsListingFrontendId);
            return null;
        }
    }
    
    // Delete
    public void deleteJobsListingFrontend(Long jobsListingFrontendId) {
    	jobsListingFrontedRepository.deleteById(jobsListingFrontendId);
        logger.info("Deleted JobsListingFrontend with ID: {}", jobsListingFrontendId);
    }

    //count the total JobsListingFrontend
    public long countJobsListingFrontend()
	 {
		 return jobsListingFrontedRepository.count();
	 }
    
	// Helper method to convert JobsListingFrontendDTo to JobsListingFrontendEntity
    private JobsListingFrontendEntity convertToEntity(JobsListingFrontendDto jobsListingFrontendDto)
    {
    	return modelMapper.map(jobsListingFrontendDto, JobsListingFrontendEntity.class);
    }

 // Helper method to convert JobsListingFrontendEntity  to JobsListingFrontendDTo
    private JobsListingFrontendDto convertToDTO(JobsListingFrontendEntity jobsListingFrontendEntity ) 
    {
        return modelMapper.map(jobsListingFrontendEntity, JobsListingFrontendDto.class);
    }
}
