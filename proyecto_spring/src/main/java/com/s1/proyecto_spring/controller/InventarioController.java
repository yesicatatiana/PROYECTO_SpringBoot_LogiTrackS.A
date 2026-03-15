package com.s1.proyecto_spring.controller;

import com.s1.proyecto_spring.dto.request.InventarioRequestDTO;
import com.s1.proyecto_spring.dto.response.InventarioResponseDTO;
import com.s1.proyecto_spring.service.InventarioService;
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
@RequestMapping("/api/inventarios")
@RequiredArgsConstructor
@Validated
@Tag(name = "Inventario", description = "API para gestión del inventario por bodega")
public class InventarioController {

    private final InventarioService inventarioService;

    @PostMapping
    @Operation(summary = "Crear un registro de inventario", description = "Permite registrar stock de un producto en una bodega (POST)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos / body mal estructurado")
    })
    public ResponseEntity<InventarioResponseDTO> guardar(@RequestBody InventarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.guardar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un inventario", description = "Permite actualizar el stock de un producto en una bodega (PUT)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos / body mal estructurado"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public ResponseEntity<InventarioResponseDTO> actualizar(
            @RequestBody InventarioRequestDTO dto,
            @Parameter(description = "ID del inventario a actualizar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(inventarioService.actualizar(dto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un inventario", description = "Permite eliminar un registro de inventario (DELETE)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Inventario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del inventario a eliminar", example = "1") @PathVariable Long id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar inventario por ID", description = "Retorna un registro de inventario según su ID (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public ResponseEntity<InventarioResponseDTO> buscarPorId(
            @Parameter(description = "ID del inventario a buscar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(inventarioService.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos los inventarios", description = "Retorna todos los registros de inventario (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventarios obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<InventarioResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(inventarioService.buscarTodos());
    }
}