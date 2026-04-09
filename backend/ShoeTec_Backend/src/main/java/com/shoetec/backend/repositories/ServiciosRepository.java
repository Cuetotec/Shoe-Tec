package com.shoetec.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoetec.backend.entities.Servicios;

@Repository
public interface ServiciosRepository extends JpaRepository<Servicios, Integer> {

}
