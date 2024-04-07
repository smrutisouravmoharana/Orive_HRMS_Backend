package com.orive.Performance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Performance.Entity.SelfAssessmentEntity;

public interface SelfAssessmentRepository extends JpaRepository<SelfAssessmentEntity, String> {

}
