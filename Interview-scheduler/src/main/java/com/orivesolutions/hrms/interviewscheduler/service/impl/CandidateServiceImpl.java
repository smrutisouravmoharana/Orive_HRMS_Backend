package com.orivesolutions.hrms.interviewscheduler.service.impl;



import com.orivesolutions.hrms.interviewscheduler.UploadDocument.UploadDocuments;
import com.orivesolutions.hrms.interviewscheduler.domain.Candidate;
import com.orivesolutions.hrms.interviewscheduler.dto.CandidateDto;
import com.orivesolutions.hrms.interviewscheduler.exception.ResourceNotFoundException;
import com.orivesolutions.hrms.interviewscheduler.mapper.CandidateMapper;
import com.orivesolutions.hrms.interviewscheduler.repository.CandidateRepository;
import com.orivesolutions.hrms.interviewscheduler.service.CandidateService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateMapper candidateMapper;

//    @Override
//    public CandidateDto createCandidate(CandidateDto candidateDto) {
//        Candidate candidate = candidateMapper.toCandidate(candidateDto);
//        candidate = candidateRepository.save(candidate);
//        candidateDto = candidateMapper.toCandidateDto(candidate);
//        return candidateDto;
//    }
    
    @Override
    public String saveCandidate(
    		String name,
    		String address,
    		String email,
    		String mobile,
    		Integer ctc,
    		Integer ectc,
    		String location,
    		String notice,
			MultipartFile file) {
		
		try {
			Candidate pdfData = candidateRepository.save(Candidate.builder()
					.name(name)
					.address(address)
					.email(email)
					.mobile(mobile)
					.ctc(ctc)
					.ectc(ectc)
					.location(location)
					.notice(notice)
					.resumeUrl(UploadDocuments.compressPdf(file.getBytes()))
					.build());
			
			 if (pdfData != null) {
		            return "File uploaded successfully: " + file.getOriginalFilename();
		        }
			
		}catch (Exception e) {
			// Handle the IOException appropriately (e.g., log it, return an error message)
	        return "Error uploading file: " + e.getMessage();
		}
		
		return null;
	}
			
	public byte[] downloadPdf(Long id) {
		 Optional<Candidate> dbPdfData = candidateRepository.findById(id);
	    
	    if (dbPdfData.isPresent()) {
	        return UploadDocuments.decompressPdf(dbPdfData.get().getResumeUrl());
	    } else {
	        // Handle the case where the candidate profile is not found
	        return null;
	    }
	}
	

    @Override
    public CandidateDto getCandidate(String email) {
        Candidate candidate = candidateRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Candidate not found", HttpStatus.NOT_FOUND));
        CandidateDto candidateDto = candidateMapper.toCandidateDto(candidate);
        return candidateDto;
    }

    @Override
    public Candidate findCandidateByEmail(String email) {
        Candidate candidate = candidateRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Candidate not found", HttpStatus.NOT_FOUND));
        return candidate;
    }
    
    @Override
    public List<CandidateDto> getAllCandidates() {
        List<Candidate> candidates = candidateRepository.findAll();
        return candidateMapper.toCandidateDtoList(candidates);
    }
}
