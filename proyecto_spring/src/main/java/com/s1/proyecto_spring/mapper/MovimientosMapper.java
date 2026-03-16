package com.s1.proyecto_spring.mapper;

import com.s1.proyecto_spring.dto.request.MovimientosRequestDTO;
import com.s1.proyecto_spring.dto.response.BodegaResponseDTO;
import com.s1.proyecto_spring.dto.response.DetalleMovimientoResponseDTO;
import com.s1.proyecto_spring.dto.response.MovimientosResponseDTO;
import com.s1.proyecto_spring.dto.response.UsuarioResponseDTO;
import com.s1.proyecto_spring.model.Bodega;
import com.s1.proyecto_spring.model.Movimientos;
import com.s1.proyecto_spring.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovimientosMapper {
    public MovimientosResponseDTO entidadADTO(Movimientos movimientos, UsuarioResponseDTO usuarioDTO,
                                              BodegaResponseDTO bodegaOrigenDTO, BodegaResponseDTO bodegaDestinoDTO,
                                              List<DetalleMovimientoResponseDTO> detalles) {
        if (movimientos == null || usuarioDTO == null) return null;
        return new MovimientosResponseDTO(
                movimientos.getId(),
                movimientos.getTipo(),
                movimientos.getFecha(),
                movimientos.getEstado(),
                usuarioDTO,
                bodegaOrigenDTO,
                bodegaDestinoDTO,
                detalles  // ← agregado
        );
    }

    public Movimientos DTOAEntidad(MovimientosRequestDTO dto, Usuario usuario, Bodega bodegaOrigen, Bodega bodegaDestino) {
        if (dto == null || usuario == null) return null;
        Movimientos m = new Movimientos();
        m.setTipo(dto.tipo());
        m.setFecha(dto.fecha());
        m.setEstado(dto.estado());
        m.setUsuario(usuario);
        m.setBodegaOrigen(bodegaOrigen);   // puede ser null
        m.setBodegaDestino(bodegaDestino);
        return m;
    }

    public void actualizarEntidadDesdeDTO(Movimientos movimientos, MovimientosRequestDTO dto, Usuario usuario, Bodega bodegaOrigen, Bodega bodegaDestino) {
        if (movimientos == null || dto == null) return;
        movimientos.setTipo(dto.tipo());
        movimientos.setFecha(dto.fecha());
        movimientos.setEstado(dto.estado());
        if (usuario != null) movimientos.setUsuario(usuario);
        movimientos.setBodegaOrigen(bodegaOrigen);
        movimientos.setBodegaDestino(bodegaDestino);
    }
}
