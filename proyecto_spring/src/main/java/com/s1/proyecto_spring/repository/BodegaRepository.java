package com.s1.proyecto_spring.repository;

import com.s1.proyecto_spring.model.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BodegaRepository extends JpaRepository<Bodega, Long> {
}
