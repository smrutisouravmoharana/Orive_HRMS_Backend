package com.orive.Organisation.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.orive.Organisation.Entity.LocationEntity;

public interface LocationRepository extends JpaRepository<LocationEntity, Long>{

	@Query("SELECT l FROM LocationEntity l WHERE l.companyName = :companyName")
    List<LocationEntity> findAllByCompanyName(String companyName);
	
	@Query("SELECT d FROM LocationEntity d WHERE d.locationName = :locationName")
	Optional<LocationEntity> findByLocationName(String locationName);
}
