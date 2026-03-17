package com.s1.proyecto_spring.service.impl;


import com.s1.proyecto_spring.dto.request.InventarioRequestDTO;
import com.s1.proyecto_spring.dto.response.*;
import com.s1.proyecto_spring.mapper.*;
import com.s1.proyecto_spring.model.*;
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
    private final UsuarioMapper usuarioMapper;
    private final RolMapper rolMapper;


    @Override
    public InventarioResponseDTO guardar(InventarioRequestDTO dto) {
        Bodega bodega = bodegaRepository.findById(dto.bodegaId())
                .orElseThrow(() -> new RuntimeException("Bodega no encontrada con id: " + dto.bodegaId()));

        Producto producto = productoRepository.findById(dto.productoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + dto.productoId()));

        Inventario inventario = inventarioMapper.DTOAEntidad(dto, bodega, producto);
        Inventario guardado = inventarioRepository.save(inventario);

        // 1. Construir RolResponseDTO desde el rol del encargado
        RolResponseDTO rolDTO = rolMapper.entidadADTO(bodega.getEncargado().getRol());

        // 2. Construir UsuarioResponseDTO pasando el rolDTO
        UsuarioResponseDTO encargadoDTO = usuarioMapper.entidadADTO(bodega.getEncargado(), rolDTO);

        // 3. Construir BodegaResponseDTO pasando el encargadoDTO
        BodegaResponseDTO bodegaDTO = bodegaMapper.entidadADTO(bodega, encargadoDTO);

        // 4. Construir ProductoResponseDTO
        ProductoResponseDTO productoDTO = productoMapper.entidadADTO(producto);

        return inventarioMapper.entidadADTO(guardado, bodegaDTO, productoDTO);
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

        // Construir de adentro hacia afuera
        RolResponseDTO rolDTO = rolMapper.entidadADTO(bodega.getEncargado().getRol());
        UsuarioResponseDTO encargadoDTO = usuarioMapper.entidadADTO(bodega.getEncargado(), rolDTO);
        BodegaResponseDTO bodegaDTO = bodegaMapper.entidadADTO(bodega, encargadoDTO);
        ProductoResponseDTO productoDTO = productoMapper.entidadADTO(producto);

        return inventarioMapper.entidadADTO(actualizado, bodegaDTO, productoDTO);
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

        RolResponseDTO rolDTO = rolMapper.entidadADTO(inventario.getBodega().getEncargado().getRol());
        UsuarioResponseDTO encargadoDTO = usuarioMapper.entidadADTO(inventario.getBodega().getEncargado(), rolDTO);
        BodegaResponseDTO bodegaDTO = bodegaMapper.entidadADTO(inventario.getBodega(), encargadoDTO);
        ProductoResponseDTO productoDTO = productoMapper.entidadADTO(inventario.getProducto());

        return inventarioMapper.entidadADTO(inventario, bodegaDTO, productoDTO);
    }

    @Override
    public List<InventarioResponseDTO> buscarTodos() {
        return inventarioRepository.findAll()
                .stream()
                .map(inv -> {
                    RolResponseDTO rolDTO = rolMapper.entidadADTO(inv.getBodega().getEncargado().getRol());
                    UsuarioResponseDTO encargadoDTO = usuarioMapper.entidadADTO(inv.getBodega().getEncargado(), rolDTO);
                    BodegaResponseDTO bodegaDTO = bodegaMapper.entidadADTO(inv.getBodega(), encargadoDTO);
                    ProductoResponseDTO productoDTO = productoMapper.entidadADTO(inv.getProducto());
                    return inventarioMapper.entidadADTO(inv, bodegaDTO, productoDTO);
                })
                .toList();
    }
}