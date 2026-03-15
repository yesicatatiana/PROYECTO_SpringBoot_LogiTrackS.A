package com.s1.proyecto_spring.mapper;

import com.s1.proyecto_spring.dto.request.UsuarioRequestDTO;
import com.s1.proyecto_spring.dto.response.RolResponseDTO;
import com.s1.proyecto_spring.dto.response.UsuarioResponseDTO;
import com.s1.proyecto_spring.model.Rol;
import com.s1.proyecto_spring.model.Usuario;

public class UsuarioMapper {
    public UsuarioResponseDTO entidadADTO(Usuario usuario, RolResponseDTO rolDTO) {
        if (usuario == null || rolDTO == null) return null;
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getActivo(),
                rolDTO
        );
    }

    public Usuario DTOAEntidad(UsuarioRequestDTO dto, Rol rol) {
        if (dto == null || rol == null) return null;
        Usuario u = new Usuario();
        u.setNombre(dto.nombre());
        u.setApellido(dto.apellido());
        u.setEmail(dto.email());
        u.setPassword(dto.password());
        u.setActivo(true);
        u.setRol(rol);
        return u;
    }

    public void actualizarEntidadDesdeDTO(Usuario usuario, UsuarioRequestDTO dto, Rol rol) {
        if (usuario == null || dto == null) return;
        usuario.setNombre(dto.nombre());
        usuario.setApellido(dto.apellido());
        usuario.setEmail(dto.email());
        usuario.setPassword(dto.password());
        if (rol != null) usuario.setRol(rol);
    }
}
