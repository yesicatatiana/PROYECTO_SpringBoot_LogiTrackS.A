package com.s1.proyecto_spring.controller;

import com.s1.proyecto_spring.dto.request.MovimientosRequestDTO;
import com.s1.proyecto_spring.dto.response.MovimientosResponseDTO;
import com.s1.proyecto_spring.service.MovimientosService;
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
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
@Validated
@Tag(name = "Movimientos", description = "API para gestión de movimientos de mercancía entre bodegas")
public class MovimientosController {

    private final MovimientosService movimientosService;

    @PostMapping
    @Operation(summary = "Crear un movimiento", description = "Permite registrar un nuevo movimiento (POST)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movimiento creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos / body mal estructurado")
    })
    public ResponseEntity<MovimientosResponseDTO> guardar(@RequestBody MovimientosRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientosService.guardar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un movimiento", description = "Permite actualizar un movimiento existente (PUT)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos / body mal estructurado"),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    })
    public ResponseEntity<MovimientosResponseDTO> actualizar(
            @RequestBody MovimientosRequestDTO dto,
            @Parameter(description = "ID del movimiento a actualizar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(movimientosService.actualizar(dto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un movimiento", description = "Permite eliminar un movimiento por ID (DELETE)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movimiento eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del movimiento a eliminar", example = "1") @PathVariable Long id) {
        movimientosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar movimiento por ID", description = "Retorna un movimiento según su ID (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    })
    public ResponseEntity<MovimientosResponseDTO> buscarPorId(
            @Parameter(description = "ID del movimiento a buscar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(movimientosService.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos los movimientos", description = "Retorna todos los movimientos registrados (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimientos obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<MovimientosResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(movimientosService.buscarTodos());
    }
}