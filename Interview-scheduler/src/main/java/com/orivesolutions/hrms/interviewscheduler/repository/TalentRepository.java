package com.orivesolutions.hrms.interviewscheduler.repository;

import com.orivesolutions.hrms.interviewscheduler.domain.Talent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalentRepository extends JpaRepository<Talent, Long> {

}
