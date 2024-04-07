package com.orivesolutions.hrms.interviewscheduler.service;

import com.orivesolutions.hrms.interviewscheduler.dto.InterviewDto;

import java.util.List;

public interface InterviewService {

    InterviewDto scheduleInterview(InterviewDto interviewDto);

    List<InterviewDto> getInterview(String schedulerEmail);
    
    List<InterviewDto> getAllInterviews();
}
