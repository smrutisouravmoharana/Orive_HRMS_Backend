package com.orive.Sale.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.Sale.Entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity,String>{

}
