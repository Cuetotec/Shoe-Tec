package com.shoetec.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoetec.backend.entities.Personal;
	
	@Repository
	public interface PersonalRepository extends JpaRepository<Personal, Integer> {

}
