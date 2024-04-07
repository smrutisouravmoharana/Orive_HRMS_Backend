package com.orivesolutions.hrms.interviewscheduler.domain;

import com.orivesolutions.hrms.interviewscheduler.enums.InterviewStatus;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedback")
@Data
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;
    
    @OneToOne
    @JoinColumn(name = "interview_id", referencedColumnName = "id")
    private Interview interview;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InterviewStatus status;
    
    @Column(name = "remarks")
    private String remarks;
    
    @Column(name = "rating")
    private Integer rating;

}
