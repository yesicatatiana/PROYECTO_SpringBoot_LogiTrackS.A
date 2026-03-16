package com.s1.proyecto_spring.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bodega")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(nullable = false, length = 200)
    private String ubicacion;

    @Column(nullable = false)
    private Integer capacidad;

    @Column(nullable = false)
    private Boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encargado_id", nullable = false)
    private Usuario encargado;
}
