package com.orivesolutions.hrms.interviewscheduler.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.orivesolutions.hrms.interviewscheduler.enums.InterviewStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "interview")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "scheduler_id", referencedColumnName = "id")
    private User scheduler;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "interviewer_id", referencedColumnName = "id")
    private User interviewer;
    
    private LocalDate dateTime;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "talent_id", referencedColumnName = "id")
    private Talent talent;
    
    @Enumerated(EnumType.STRING)
    private InterviewStatus interviewStatus;
    
    
    private String meetingLink;

}
