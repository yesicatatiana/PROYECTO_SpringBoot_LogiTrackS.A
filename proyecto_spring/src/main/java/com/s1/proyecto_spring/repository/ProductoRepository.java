package com.s1.proyecto_spring.repository;


import com.s1.proyecto_spring.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByStockLessThan(Integer stock);

}