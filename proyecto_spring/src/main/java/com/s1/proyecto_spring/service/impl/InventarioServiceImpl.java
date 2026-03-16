package com.s1.proyecto_spring.service.impl;


import com.s1.proyecto_spring.dto.request.InventarioRequestDTO;
import com.s1.proyecto_spring.dto.response.InventarioResponseDTO;
import com.s1.proyecto_spring.mapper.BodegaMapper;
import com.s1.proyecto_spring.mapper.InventarioMapper;
import com.s1.proyecto_spring.mapper.ProductoMapper;
import com.s1.proyecto_spring.model.Bodega;
import com.s1.proyecto_spring.model.Inventario;
import com.s1.proyecto_spring.model.Producto;
import com.s1.proyecto_spring.repository.BodegaRepository;
import com.s1.proyecto_spring.repository.InventarioRepository;
import com.s1.proyecto_spring.repository.ProductoRepository;
import com.s1.proyecto_spring.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;
    private final BodegaRepository bodegaRepository;
    private final ProductoRepository productoRepository;
    private final InventarioMapper inventarioMapper;
    private final BodegaMapper bodegaMapper;
    private final ProductoMapper productoMapper;



    @Override
    public InventarioResponseDTO guardar(InventarioRequestDTO dto) {
        Bodega bodega = bodegaRepository.findById(dto.bodegaId())
                .orElseThrow(() -> new RuntimeException("Bodega no encontrada con id: " + dto.bodegaId()));

        Producto producto = productoRepository.findById(dto.productoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + dto.productoId()));

        Inventario inventario = inventarioMapper.DTOAEntidad(dto, bodega, producto);
        Inventario guardado = inventarioRepository.save(inventario);

        return inventarioMapper.entidadADTO(
                guardado,
                bodegaMapper.entidadADTO(bodega),
                productoMapper.entidadADTO(producto)
        );
    }

    @Override
    public InventarioResponseDTO actualizar(InventarioRequestDTO dto, Long id) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con id: " + id));

        Bodega bodega = bodegaRepository.findById(dto.bodegaId())
                .orElseThrow(() -> new RuntimeException("Bodega no encontrada con id: " + dto.bodegaId()));

        Producto producto = productoRepository.findById(dto.productoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + dto.productoId()));

        inventarioMapper.actualizarEntidadDesdeDTO(inventario, dto, bodega, producto);
        Inventario actualizado = inventarioRepository.save(inventario);

        return inventarioMapper.entidadADTO(
                actualizado,
                bodegaMapper.entidadADTO(bodega),
                productoMapper.entidadADTO(producto)
        );
    }

    @Override
    public void eliminar(Long id) {
        if (!inventarioRepository.existsById(id)) {
            throw new RuntimeException("Inventario no encontrado con id: " + id);
        }
        inventarioRepository.deleteById(id);
    }

    @Override
    public InventarioResponseDTO buscarPorId(Long id) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con id: " + id));

        return inventarioMapper.entidadADTO(
                inventario,
                bodegaMapper.entidadADTO(inventario.getBodega()),
                productoMapper.entidadADTO(inventario.getProducto())
        );
    }

    @Override
    public List<InventarioResponseDTO> buscarTodos() {
        return inventarioRepository.findAll()
                .stream()
                .map(inv -> inventarioMapper.entidadADTO(
                        inv,
                        bodegaMapper.entidadADTO(inv.getBodega()),
                        productoMapper.entidadADTO(inv.getProducto())
                ))
                .toList();
    }
}