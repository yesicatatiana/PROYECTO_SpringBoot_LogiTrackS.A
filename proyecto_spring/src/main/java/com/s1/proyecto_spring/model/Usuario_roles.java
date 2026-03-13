package com.s1.proyecto_spring.model;

import jakarta.persistence.*;
        import lombok.*;

@Entity
@Table(name = "usuario_roles")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Usuario_roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;
}