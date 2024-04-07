package com.orive.Recruitment.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.Recruitment.Dto.JobCandidatesDto;
import com.orive.Recruitment.Entity.JobCandidatesEntity;
import com.orive.Recruitment.Repository.JobCandidatesRepository;

@Service
public class JobCandidatesService {
	
	private static final Logger logger= LoggerFactory.getLogger(JobCandidatesService.class);
	
	@Autowired
	private JobCandidatesRepository jobCandidatesRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public JobCandidatesDto createJobCandidates (JobCandidatesDto jobCandidatesDto) {
    	JobCandidatesEntity  jobCandidatesEntity = convertToEntity(jobCandidatesDto);
    	JobCandidatesEntity savedJobCandidates = jobCandidatesRepository.save(jobCandidatesEntity);
        logger.info("Created JobCandidates with ID: {}", savedJobCandidates.getJobCandidatesId());
        return convertToDTO(savedJobCandidates);
    }

    // Read
    public List<JobCandidatesDto> getAllJobCandidates() {
        List<JobCandidatesEntity>jobCandidatesEntities = jobCandidatesRepository.findAll();
        logger.info("Retrieved {} JobCandidates from the database", jobCandidatesEntities.size());
        return jobCandidatesEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by JobCandidatesId
    public Optional<JobCandidatesDto> getJobCandidatesId(Long jobCandidatesId) {
        Optional<JobCandidatesEntity> jobCandidates = jobCandidatesRepository.findById(jobCandidatesId);
        if (jobCandidates.isPresent()) {
            return Optional.of(convertToDTO(jobCandidates.get()));
        } else {
            logger.warn("JobCandidates with ID {} not found", jobCandidatesId);
            return Optional.empty();
        }
    }
    
 // Update list by id
    public JobCandidatesDto updateJobCandidates(Long jobCandidatesId, JobCandidatesDto jobCandidatesDto) {
        Optional<JobCandidatesEntity> existingJobCandidatesOptional = jobCandidatesRepository.findById(jobCandidatesId);
        if (existingJobCandidatesOptional.isPresent()) {
        	JobCandidatesEntity existingJobCandidates= existingJobCandidatesOptional.get();
            modelMapper.map(jobCandidatesDto, existingJobCandidatesOptional);
            JobCandidatesEntity updatedJobCandidates = jobCandidatesRepository.save(existingJobCandidates);
            logger.info("Updated JobCandidates with ID: {}", updatedJobCandidates.getJobCandidatesId());
            return convertToDTO(updatedJobCandidates);
        } else {
            logger.warn("JobCandidates with ID {} not found for update", jobCandidatesId);
            return null;
        }
    }
    
    // Delete
    public void deleteJobCandidates(Long jobCandidatesId) {
    	jobCandidatesRepository.deleteById(jobCandidatesId);
        logger.info("Deleted JobCandidates with ID: {}", jobCandidatesId);
    }

    //count the total JobCandidates
    public long countJobCandidates()
	 {
		 return jobCandidatesRepository.count();
	 }
    
	// Helper method to convert JobCandidatesDTo to JobCandidatesEntity
    private JobCandidatesEntity convertToEntity(JobCandidatesDto jobCandidatesDto)
    {
    	return modelMapper.map(jobCandidatesDto, JobCandidatesEntity.class);
    }

 // Helper method to convert JobCandidatesEntity entity to JobCandidatesDTo
    private JobCandidatesDto convertToDTO(JobCandidatesEntity jobCandidatesEntity) {
        return modelMapper.map(jobCandidatesEntity, JobCandidatesDto.class);
    }
}
