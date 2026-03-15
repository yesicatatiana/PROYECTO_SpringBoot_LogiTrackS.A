package com.s1.proyecto_spring.controller;

import com.s1.proyecto_spring.dto.request.BodegaRequestDTO;
import com.s1.proyecto_spring.dto.response.BodegaResponseDTO;
import com.s1.proyecto_spring.service.BodegaService;
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
@RequestMapping("/api/bodegas")
@RequiredArgsConstructor
@Validated
@Tag(name = "Bodega", description = "API para gestión de bodegas del sistema")
public class BodegaController {

    private final BodegaService bodegaService;

    @PostMapping
    @Operation(summary = "Crear una bodega", description = "Permite registrar una nueva bodega (POST)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bodega creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos / body mal estructurado")
    })
    public ResponseEntity<BodegaResponseDTO> guardar(@RequestBody BodegaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bodegaService.guardar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una bodega", description = "Permite actualizar una bodega existente (PUT)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bodega actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos / body mal estructurado"),
            @ApiResponse(responseCode = "404", description = "Bodega no encontrada")
    })
    public ResponseEntity<BodegaResponseDTO> actualizar(
            @RequestBody BodegaRequestDTO dto,
            @Parameter(description = "ID de la bodega a actualizar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(bodegaService.actualizar(dto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una bodega", description = "Permite eliminar una bodega por ID (DELETE)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Bodega eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Bodega no encontrada")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID de la bodega a eliminar", example = "1") @PathVariable Long id) {
        bodegaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar bodega por ID", description = "Retorna una bodega según su ID (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bodega encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Bodega no encontrada")
    })
    public ResponseEntity<BodegaResponseDTO> buscarPorId(
            @Parameter(description = "ID de la bodega a buscar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(bodegaService.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todas las bodegas", description = "Retorna todas las bodegas registradas (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bodegas obtenidas exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<BodegaResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(bodegaService.buscarTodos());
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Activar o desactivar bodega", description = "Cambia el estado activo/inactivo de una bodega (PATCH)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Bodega no encontrada")
    })
    public ResponseEntity<BodegaResponseDTO> activarDesactivar(
            @Parameter(description = "ID de la bodega", example = "1") @PathVariable Long id,
            @Parameter(description = "Estado: true=activa, false=inactiva", example = "true") @RequestParam Boolean activo) {
        return ResponseEntity.ok(bodegaService.activarDesactivar(id, activo));
    }
}