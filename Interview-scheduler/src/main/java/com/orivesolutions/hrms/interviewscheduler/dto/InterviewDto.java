package com.orivesolutions.hrms.interviewscheduler.dto;

import com.orivesolutions.hrms.interviewscheduler.enums.InterviewStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDto {

    private Long id;
    private String candidateEmailId;
    private String schedulerEmailId;
    private String interviewerEmailId;
    private Long talentId;
    private InterviewStatus interviewStatus;
    private String meetingLink;
    private LocalDate dateTime;

}
