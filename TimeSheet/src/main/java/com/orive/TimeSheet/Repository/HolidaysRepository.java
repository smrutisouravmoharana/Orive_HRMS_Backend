package com.orive.TimeSheet.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.orive.TimeSheet.Entity.HolidaysEntity;

public interface HolidaysRepository extends JpaRepository<HolidaysEntity, String>{
	
	@Query("SELECT h FROM HolidaysEntity h WHERE h.eventName = :eventName")
	Optional<HolidaysEntity> findByEventName(String eventName);

}
