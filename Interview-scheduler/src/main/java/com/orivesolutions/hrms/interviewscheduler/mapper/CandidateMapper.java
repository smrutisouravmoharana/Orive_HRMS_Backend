package com.orivesolutions.hrms.interviewscheduler.mapper;

import com.orivesolutions.hrms.interviewscheduler.domain.Candidate;
import com.orivesolutions.hrms.interviewscheduler.dto.CandidateDto;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface CandidateMapper {

    CandidateDto toCandidateDto(Candidate candidate);

    Candidate toCandidate(CandidateDto candidateDto);
    
    List<CandidateDto> toCandidateDtoList(List<Candidate> candidates);
   }


