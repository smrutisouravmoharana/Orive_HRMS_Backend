package com.orivesolutions.hrms.interviewscheduler.mapper;

import com.orivesolutions.hrms.interviewscheduler.domain.Talent;
import com.orivesolutions.hrms.interviewscheduler.dto.TalentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TalentMapper {

    TalentDto toTalentDto(Talent talent);

    Talent toTalent(TalentDto talentDto);

    List<Talent> toTalents(List<TalentDto> talentDtos);

    List<TalentDto> toTalentDtos(List<Talent> talens);
}
