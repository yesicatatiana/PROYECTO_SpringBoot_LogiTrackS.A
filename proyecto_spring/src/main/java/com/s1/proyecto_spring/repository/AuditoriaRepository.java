package com.s1.proyecto_spring.repository;


import com.s1.proyecto_spring.model.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {
    List<Auditoria> findByUsuarioId(Long usuarioId);
    List<Auditoria> findByOperacion(Auditoria.TipoOperacion operacion);
    List<Auditoria> findByUsuarioIdAndOperacion(Long usuarioId, Auditoria.TipoOperacion operacion);
}