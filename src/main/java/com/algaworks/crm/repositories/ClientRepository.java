package com.algaworks.crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.crm.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
}
