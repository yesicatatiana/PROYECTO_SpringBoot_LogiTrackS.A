package com.s1.proyecto_spring.controller;

import com.s1.proyecto_spring.dto.request.DetalleMovimientoRequestDTO;
import com.s1.proyecto_spring.dto.response.DetalleMovimientoResponseDTO;
import com.s1.proyecto_spring.service.DetalleMovimientoService;
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
@RequestMapping("/api/detalle-movimientos")
@RequiredArgsConstructor
@Validated
@Tag(name = "Detalle Movimiento", description = "API para gestión de detalles de cada movimiento")
public class DetalleMovimientoController {

    private final DetalleMovimientoService detalleMovimientoService;

    @PostMapping
    @Operation(summary = "Crear un detalle de movimiento", description = "Permite registrar un detalle de movimiento (POST)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Detalle creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos / body mal estructurado")
    })
    public ResponseEntity<DetalleMovimientoResponseDTO> guardar(@RequestBody DetalleMovimientoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleMovimientoService.guardar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un detalle de movimiento", description = "Permite actualizar un detalle existente (PUT)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos / body mal estructurado"),
            @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    })
    public ResponseEntity<DetalleMovimientoResponseDTO> actualizar(
            @RequestBody DetalleMovimientoRequestDTO dto,
            @Parameter(description = "ID del detalle a actualizar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(detalleMovimientoService.actualizar(dto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un detalle de movimiento", description = "Permite eliminar un detalle por ID (DELETE)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Detalle eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del detalle a eliminar", example = "1") @PathVariable Long id) {
        detalleMovimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar detalle por ID", description = "Retorna un detalle de movimiento según su ID (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    })
    public ResponseEntity<DetalleMovimientoResponseDTO> buscarPorId(
            @Parameter(description = "ID del detalle a buscar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(detalleMovimientoService.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos los detalles", description = "Retorna todos los detalles de movimiento (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalles obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<DetalleMovimientoResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(detalleMovimientoService.buscarTodos());
    }

    @GetMapping("/movimiento/{movimientoId}")
    @Operation(summary = "Buscar detalles por movimiento", description = "Retorna todos los detalles de un movimiento específico (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalles obtenidos exitosamente"),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    })
    public ResponseEntity<List<DetalleMovimientoResponseDTO>> buscarPorMovimiento(
            @Parameter(description = "ID del movimiento", example = "1") @PathVariable Long movimientoId) {
        return ResponseEntity.ok(detalleMovimientoService.buscarPorMovimiento(movimientoId));
    }
}