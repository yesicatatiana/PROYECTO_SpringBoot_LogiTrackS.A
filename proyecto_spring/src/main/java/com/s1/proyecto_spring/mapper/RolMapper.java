package com.s1.proyecto_spring.mapper;

import com.s1.proyecto_spring.dto.response.RolResponseDTO;
import com.s1.proyecto_spring.model.Rol;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {

    public RolResponseDTO entidadADTO(Rol rol) {
        if (rol == null) return null;
        return new RolResponseDTO(
                rol.getId(),
                rol.getRol().name() // TipoRol.ADMINISTRADOR → "ADMINISTRADOR"
        );
    }

    public Rol DTOAEntidad(RolResponseDTO dto) {
        if (dto == null) return null;
        Rol r = new Rol();
        r.setId(dto.id());
        r.setRol(Rol.TipoRol.valueOf(dto.rol())); // "ADMINISTRADOR" → TipoRol.ADMINISTRADOR
        return r;
    }

}
