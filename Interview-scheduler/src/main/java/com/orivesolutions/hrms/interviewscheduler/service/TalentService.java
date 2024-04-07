package com.orivesolutions.hrms.interviewscheduler.service;

import com.orivesolutions.hrms.interviewscheduler.domain.Talent;
import com.orivesolutions.hrms.interviewscheduler.dto.TalentDto;

import java.util.List;

public interface TalentService {

    TalentDto createTalent(TalentDto talentDto);

    Talent getTalent(Long talentId);

    List<TalentDto> findAll();
}
