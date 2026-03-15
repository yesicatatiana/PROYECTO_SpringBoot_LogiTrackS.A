package com.s1.proyecto_spring.repository;

import com.s1.proyecto_spring.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}
