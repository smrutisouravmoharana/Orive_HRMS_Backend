package com.orive.Recruitment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Recruitment.Entity.JobCandidatesEntity;

public interface JobCandidatesRepository extends JpaRepository<JobCandidatesEntity, Long> {

}
