package com.s1.proyecto_spring.mapper;

import com.s1.proyecto_spring.dto.request.InventarioRequestDTO;
import com.s1.proyecto_spring.dto.response.BodegaResponseDTO;
import com.s1.proyecto_spring.dto.response.InventarioResponseDTO;
import com.s1.proyecto_spring.dto.response.ProductoResponseDTO;
import com.s1.proyecto_spring.model.Bodega;
import com.s1.proyecto_spring.model.Inventario;
import com.s1.proyecto_spring.model.Producto;

public class InventarioMapper {
    public InventarioResponseDTO entidadADTO(Inventario inventario, BodegaResponseDTO bodegaDTO, ProductoResponseDTO productoDTO) {
        if (inventario == null || bodegaDTO == null || productoDTO == null) return null;
        return new InventarioResponseDTO(
                inventario.getId(),
                inventario.getStock(),
                bodegaDTO,
                productoDTO
        );
    }

    public Inventario DTOAEntidad(InventarioRequestDTO dto, Bodega bodega, Producto producto) {
        if (dto == null || bodega == null || producto == null) return null;
        Inventario i = new Inventario();
        i.setStock(dto.stock());
        i.setBodega(bodega);
        i.setProducto(producto);
        return i;
    }

    public void actualizarEntidadDesdeDTO(Inventario inventario, InventarioRequestDTO dto, Bodega bodega, Producto producto) {
        if (inventario == null || dto == null) return;
        inventario.setStock(dto.stock());
        if (bodega != null) inventario.setBodega(bodega);
        if (producto != null) inventario.setProducto(producto);
    }
}
