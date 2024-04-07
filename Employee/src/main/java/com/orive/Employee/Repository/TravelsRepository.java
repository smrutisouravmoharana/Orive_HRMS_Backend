package com.orive.Employee.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.orive.Employee.Entity.TravelsEntity;

public interface TravelsRepository extends JpaRepository<TravelsEntity, Long>{

	List<TravelsEntity> findByUsername(String username);
}
