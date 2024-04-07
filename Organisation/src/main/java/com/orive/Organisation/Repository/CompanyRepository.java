package com.orive.Organisation.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.orive.Organisation.Entity.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long>{

	Optional<CompanyEntity> findByCompanyName(String companyName);
	
	@Query("SELECT l FROM CompanyEntity l WHERE l.companyName = :companyName")
    List<CompanyEntity> findAllByCompanyName(String companyName);
}
