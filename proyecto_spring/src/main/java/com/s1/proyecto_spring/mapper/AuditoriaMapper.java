package com.s1.proyecto_spring.mapper;

import com.s1.proyecto_spring.dto.request.AuditoriaRequestDTO;
import com.s1.proyecto_spring.dto.response.AuditoriaResponseDTO;
import com.s1.proyecto_spring.dto.response.UsuarioResponseDTO;
import com.s1.proyecto_spring.model.Auditoria;
import com.s1.proyecto_spring.model.Usuario;

public class AuditoriaMapper {

    public AuditoriaResponseDTO entidadADTO(Auditoria auditoria, UsuarioResponseDTO usuarioDTO) {
        if (auditoria == null || usuarioDTO == null) return null;
        return new AuditoriaResponseDTO(
                auditoria.getId(),
                auditoria.getOperacion().name(),
                auditoria.getEntidad(),
                auditoria.getValoresAnteriores(),
                auditoria.getValoresNuevos(),
                auditoria.getOrigen(),
                auditoria.getFechaHora(),
                usuarioDTO
        );
    }

    public Auditoria DTOAEntidad(AuditoriaRequestDTO dto, Usuario usuario) {
        if (dto == null || usuario == null) return null;
        Auditoria a = new Auditoria();
        a.setOperacion(dto.operacion());
        a.setEntidad(dto.entidad());
        a.setValoresAnteriores(dto.valoresAnteriores());
        a.setValoresNuevos(dto.valoresNuevos());
        a.setOrigen(dto.origen());
        a.setFechaHora(dto.fechaHora());
        a.setUsuario(usuario);
        return a;
    }

    public void actualizarEntidadDesdeDTO(Auditoria auditoria, AuditoriaRequestDTO dto, Usuario usuario) {
        if (auditoria == null || dto == null) return;
        auditoria.setOperacion(dto.operacion());
        auditoria.setEntidad(dto.entidad());
        auditoria.setValoresAnteriores(dto.valoresAnteriores());
        auditoria.setValoresNuevos(dto.valoresNuevos());
        auditoria.setOrigen(dto.origen());
        auditoria.setFechaHora(dto.fechaHora());
        if (usuario != null) auditoria.setUsuario(usuario);
    }
}
