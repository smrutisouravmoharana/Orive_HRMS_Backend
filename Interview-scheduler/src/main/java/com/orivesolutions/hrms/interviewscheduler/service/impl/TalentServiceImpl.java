package com.orivesolutions.hrms.interviewscheduler.service.impl;

import com.orivesolutions.hrms.interviewscheduler.domain.Talent;
import com.orivesolutions.hrms.interviewscheduler.dto.TalentDto;
import com.orivesolutions.hrms.interviewscheduler.exception.ResourceNotFoundException;
import com.orivesolutions.hrms.interviewscheduler.mapper.TalentMapper;
import com.orivesolutions.hrms.interviewscheduler.repository.TalentRepository;
import com.orivesolutions.hrms.interviewscheduler.service.TalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalentServiceImpl implements TalentService {

    @Autowired
    private TalentRepository talentRepository;
    @Autowired
    private TalentMapper talentMapper;

    @Override
    public TalentDto createTalent(TalentDto talentDto) {
        Talent talent = talentMapper.toTalent(talentDto);
        talent = talentRepository.save(talent);
        talentDto = talentMapper.toTalentDto(talent);
        return talentDto;
    }

    @Override
    public Talent getTalent(Long talentId) {
        Talent talent = talentRepository.findById(talentId).orElseThrow(() -> new ResourceNotFoundException("Talent not found", HttpStatus.NOT_FOUND));
        return talent;
    }
    
    @Override
    public List<TalentDto> findAll() {
        List<Talent> talents = talentRepository.findAll();
        List<TalentDto> talentDtos = talentMapper.toTalentDtos(talents);
        return talentDtos;
    }
}
