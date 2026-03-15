package com.s1.proyecto_spring.repository;

import com.s1.proyecto_spring.model.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimientos, Long> {
    List<Movimientos> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

}

