package com.orivesolutions.hrms.interviewscheduler.repository;

import com.orivesolutions.hrms.interviewscheduler.domain.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> getInterviewByScheduler_EmailId(String schedulerEmail);
}
