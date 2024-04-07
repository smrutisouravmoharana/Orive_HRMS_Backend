package com.orivesolutions.hrms.interviewscheduler.dto;

import biweekly.property.Attendee;
import biweekly.property.Organizer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder(toBuilder = true)
public class CalenderDto {
    private String subject;
    private String description;
    private String summary;
    private Organizer organizer;
    private String meetingLink;
    private LocalDate eventDateTime;
    private List<Attendee> attendees;

}
