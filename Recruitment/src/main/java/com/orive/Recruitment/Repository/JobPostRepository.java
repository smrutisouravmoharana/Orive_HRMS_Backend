package com.orive.Recruitment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Recruitment.Entity.JobPostEntity;

public interface JobPostRepository extends JpaRepository<JobPostEntity, Long> {

}
