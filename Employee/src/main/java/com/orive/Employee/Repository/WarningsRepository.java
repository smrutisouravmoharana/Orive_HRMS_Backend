package com.orive.Employee.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.orive.Employee.Entity.WarningsEntity;

public interface WarningsRepository extends JpaRepository<WarningsEntity, Long> {

	List<WarningsEntity> findByUsername(String username);
}
