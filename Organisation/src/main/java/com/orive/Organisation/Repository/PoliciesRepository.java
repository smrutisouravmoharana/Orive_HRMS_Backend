package com.orive.Organisation.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.orive.Organisation.Entity.CompanyEntity;
import com.orive.Organisation.Entity.LocationEntity;
import com.orive.Organisation.Entity.PoliciesEntity;

public interface PoliciesRepository extends JpaRepository<PoliciesEntity, Long>{

	@Query("SELECT d FROM PoliciesEntity d WHERE d.title = :title")
	Optional<PoliciesEntity> findByPoliciesTittle(String title);
}
