package com.s1.proyecto_spring.repository;

import com.s1.proyecto_spring.model.DetalleMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetalleMovimientoRepository extends JpaRepository<DetalleMovimiento, Long> {
    List<DetalleMovimiento> findByMovimientoId(Long movimientoId);
}