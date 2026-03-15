package com.s1.proyecto_spring.controller;

import com.s1.proyecto_spring.dto.response.RolResponseDTO;
import com.s1.proyecto_spring.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@Tag(name = "Rol", description = "API para consulta de roles del sistema")
public class RolController {

    private final RolService rolService;

    @GetMapping
    @Operation(summary = "Listar todos los roles", description = "Retorna todos los roles disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<RolResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(rolService.buscarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar rol por ID", description = "Retorna un rol según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    public ResponseEntity<RolResponseDTO> buscarPorId(
            @Parameter(description = "ID del rol a buscar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(rolService.buscarPorId(id));
    }
}