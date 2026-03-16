package com.s1.proyecto_spring.mapper;

import com.s1.proyecto_spring.dto.request.BodegaRequestDTO;
import com.s1.proyecto_spring.dto.response.BodegaResponseDTO;
import com.s1.proyecto_spring.dto.response.UsuarioResponseDTO;
import com.s1.proyecto_spring.model.Bodega;
import com.s1.proyecto_spring.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class BodegaMapper {
    public BodegaResponseDTO entidadADTO(Bodega bodega, UsuarioResponseDTO encargadoDTO) {
        if (bodega == null || encargadoDTO == null) return null;
        return new BodegaResponseDTO(
                bodega.getId(),
                bodega.getNombre(),
                bodega.getUbicacion(),
                bodega.getCapacidad(),
                bodega.getActivo(),
                encargadoDTO
        );
    }

    public Bodega DTOAEntidad(BodegaRequestDTO dto, Usuario encargado) {
        if (dto == null || encargado == null) return null;
        Bodega b = new Bodega();
        b.setNombre(dto.nombre());
        b.setUbicacion(dto.ubicacion());
        b.setCapacidad(dto.capacidad());
        b.setActivo(dto.activo());
        b.setEncargado(encargado);
        return b;
    }

    public void actualizarEntidadDesdeDTO(Bodega bodega, BodegaRequestDTO dto, Usuario encargado) {
        if (bodega == null || dto == null) return;
        bodega.setNombre(dto.nombre());
        bodega.setUbicacion(dto.ubicacion());
        bodega.setCapacidad(dto.capacidad());
        bodega.setActivo(dto.activo());
        if (encargado != null) bodega.setEncargado(encargado);
    }
}
