package com.s1.proyecto_spring.controller;

import com.s1.proyecto_spring.dto.request.AuditoriaRequestDTO;
import com.s1.proyecto_spring.dto.response.AuditoriaResponseDTO;
import com.s1.proyecto_spring.model.Auditoria;
import com.s1.proyecto_spring.service.AuditoriaService;
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
@RequestMapping("/api/auditorias")
@RequiredArgsConstructor
@Validated
@Tag(name = "Auditoria", description = "API para consulta de registros de auditoría del sistema")
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    @PostMapping
    @Operation(summary = "Registrar una auditoría", description = "Permite registrar un nuevo evento de auditoría (POST)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Auditoría registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos / body mal estructurado")
    })
    public ResponseEntity<AuditoriaResponseDTO> guardar(@RequestBody AuditoriaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(auditoriaService.guardar(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar auditoría por ID", description = "Retorna un registro de auditoría según su ID (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auditoría encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Auditoría no encontrada")
    })
    public ResponseEntity<AuditoriaResponseDTO> buscarPorId(
            @Parameter(description = "ID de la auditoría a buscar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(auditoriaService.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todas las auditorías", description = "Retorna todos los registros de auditoría (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auditorías obtenidas exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<AuditoriaResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(auditoriaService.buscarTodos());
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Buscar auditorías por usuario", description = "Retorna auditorías de un usuario específico (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auditorías obtenidas exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<List<AuditoriaResponseDTO>> buscarPorUsuario(
            @Parameter(description = "ID del usuario", example = "1") @PathVariable Long usuarioId) {
        return ResponseEntity.ok(auditoriaService.buscarPorUsuario(usuarioId));
    }

    @GetMapping("/operacion")
    @Operation(summary = "Buscar auditorías por operación", description = "Retorna auditorías filtradas por tipo de operación (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auditorías obtenidas exitosamente"),
            @ApiResponse(responseCode = "400", description = "Operación no válida")
    })
    public ResponseEntity<List<AuditoriaResponseDTO>> buscarPorOperacion(
            @Parameter(description = "Tipo de operación: INSERTAR, ACTUALIZAR, ELIMINAR", example = "INSERTAR")
            @RequestParam Auditoria.TipoOperacion operacion) {
        return ResponseEntity.ok(auditoriaService.buscarPorOperacion(operacion));
    }

    @GetMapping("/filtro")
    @Operation(summary = "Buscar auditorías por usuario y operación", description = "Filtra auditorías por usuario y tipo de operación (GET)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auditorías obtenidas exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros no válidos")
    })
    public ResponseEntity<List<AuditoriaResponseDTO>> buscarPorUsuarioYOperacion(
            @Parameter(description = "ID del usuario", example = "1") @RequestParam Long usuarioId,
            @Parameter(description = "Tipo de operación: INSERTAR, ACTUALIZAR, ELIMINAR", example = "ELIMINAR")
            @RequestParam Auditoria.TipoOperacion operacion) {
        return ResponseEntity.ok(auditoriaService.buscarPorUsuarioYOperacion(usuarioId, operacion));
    }
}