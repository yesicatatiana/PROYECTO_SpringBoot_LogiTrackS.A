package com.s1.proyecto_spring.mapper;

import com.s1.proyecto_spring.dto.request.DetalleMovimientoRequestDTO;
import com.s1.proyecto_spring.dto.response.DetalleMovimientoResponseDTO;
import com.s1.proyecto_spring.dto.response.ProductoResponseDTO;
import com.s1.proyecto_spring.model.DetalleMovimiento;
import com.s1.proyecto_spring.model.Movimientos;
import com.s1.proyecto_spring.model.Producto;

public class DetalleMovimientoMapper {

    public DetalleMovimientoResponseDTO entidadADTO(DetalleMovimiento detalle, ProductoResponseDTO productoDTO) {
        if (detalle == null || productoDTO == null) return null;
        return new DetalleMovimientoResponseDTO(
                detalle.getId(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                productoDTO
        );
    }

    public DetalleMovimiento DTOAEntidad(DetalleMovimientoRequestDTO dto, Movimientos movimientos, Producto producto) {
        if (dto == null || movimientos == null || producto == null) return null;
        DetalleMovimiento d = new DetalleMovimiento();
        d.setCantidad(dto.cantidad());
        d.setPrecioUnitario(dto.precioUnitario());
        d.setMovimiento(movimientos);
        d.setProducto(producto);
        return d;
    }

    public void actualizarEntidadDesdeDTO(DetalleMovimiento detalle, DetalleMovimientoRequestDTO dto,
                                          Movimientos movimientos, Producto producto) {
        if (detalle == null || dto == null) return;
        detalle.setCantidad(dto.cantidad());
        detalle.setPrecioUnitario(dto.precioUnitario());
        if (movimientos != null) detalle.setMovimiento(movimientos);
        if (producto != null) detalle.setProducto(producto);
    }
}
