package com.s1.proyecto_spring.controller;

import com.s1.proyecto_spring.dto.request.ProductoRequestDTO;
import com.s1.proyecto_spring.dto.response.ProductoResponseDTO;
import com.s1.proyecto_spring.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Validated
@Tag(name = "Producto", description = "API para gestión de productos del inventario")
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    @Operation(summary = "Crear un producto", description = "Permite registrar un nuevo producto (POST)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos / body mal estructurado")
    })
    public ResponseEntity<ProductoResponseDTO> guardar(@RequestBody ProductoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto", description = "Permite actualizar un producto existente (PUT)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos / body mal estructurado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<ProductoResponseDTO> actualizar(
            @RequestBody ProductoRequestDTO dto,
            @Parameter(description = "ID del producto a actualizar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(productoService.actualizar(dto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto", description = "Permite eliminar un producto por ID (DELETE)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del producto a eliminar", example = "1") @PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar producto por ID", description = "Retorna un producto según su ID (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<ProductoResponseDTO> buscarPorId(
            @Parameter(description = "ID del producto a buscar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos los productos", description = "Retorna todos los productos registrados (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<ProductoResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(productoService.buscarTodos());
    }

    @GetMapping("/stock-bajo")
    @Operation(summary = "Buscar productos con stock bajo", description = "Retorna productos con stock menor al valor indicado (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos obtenidos exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetro no válido")
    })
    public ResponseEntity<List<ProductoResponseDTO>> buscarStockBajo(
            @Parameter(description = "Umbral de stock", example = "10") @RequestParam Integer stock) {
        return ResponseEntity.ok(productoService.buscarStockBajo(stock));
    }
}
