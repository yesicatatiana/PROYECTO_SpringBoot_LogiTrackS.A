package com.s1.proyecto_spring.repository;

import com.s1.proyecto_spring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
