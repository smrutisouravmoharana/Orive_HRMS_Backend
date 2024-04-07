package com.orivesolutions.hrms.interviewscheduler.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.orivesolutions.hrms.interviewscheduler.domain.Candidate;
import com.orivesolutions.hrms.interviewscheduler.dto.CandidateDto;

public interface CandidateService {

//    CandidateDto saveCandidate(CandidateDto candidateDto);
	
	String saveCandidate(String name, String address, String email, String mobile, Integer ctc, Integer ectc,
			String location, String notice, MultipartFile file);

    CandidateDto getCandidate(String email);

    Candidate findCandidateByEmail(String email);

	byte[] downloadPdf(Long id);
	
	 List<CandidateDto> getAllCandidates();

    


}
