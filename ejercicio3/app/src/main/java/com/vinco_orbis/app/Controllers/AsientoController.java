package com.vinco_orbis.app.Controllers;

import com.vinco_orbis.app.Service.AsientoService;
import com.vinco_orbis.app.Model.Asiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/asientos")
@Tag(name = "Controller de asientos", description = "CRUD de asientos")
public class AsientoController {

    @Autowired
    private AsientoService asientoService;

    @GetMapping
    @Operation(summary = "Obtener todos los asientos")
    public ResponseEntity<Object> getAllAsientos() {
        try {
            return ResponseEntity.ok(asientoService.getAllAsientos());
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un asiento por su ID")
    public ResponseEntity<Object> getAsientoById(
            @Parameter(description = "Obtener asiento por id", required = true) 
            @PathVariable Long id) {
        try {
            return ResponseEntity.ok(asientoService.getAsientoById(id));
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar nuevo asiento")
    public ResponseEntity<Object> saveAsiento(
            @Parameter(description = "Agregar nuevo asiento", required = true) 
            @RequestBody Asiento asiento) {
        try {
            return ResponseEntity.ok(asientoService.saveOrUpdateAsiento(asiento));
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @PostMapping("/addAsientos")
    @Operation(summary = "Agregar multiples asientos")
    public ResponseEntity<Object> saveMultipleAsientos(
            @Parameter(description = "Listado de asientos para agregar", required = true) 
            @RequestBody List<Asiento> asientos) {
        try {
            return ResponseEntity.ok(asientoService.saveAllAsientos(asientos));
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar asiento")
    public ResponseEntity<Object> updateAsiento(
            @Parameter(description = "El objeto del body para el asiento a actualizar", required = true) 
            @RequestBody Asiento asiento,
            @Parameter(description = "El id para el asiento que queremos actualizar", required = true) 
            @PathVariable Long id) {
        try {
            asiento.setId(id);
            return ResponseEntity.ok(asientoService.saveOrUpdateAsiento(asiento));
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar asiento")
    public ResponseEntity<Object> deleteAsiento(
            @Parameter(description = "El id para eliminar asiento", required = true) 
            @PathVariable Long id) {
        try {
            asientoService.deleteAsiento(id);
            Map<String, String> response = new HashMap<>();
            response.put("status", "Asiento eliminado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private ResponseEntity<Object> handleException(Exception e) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
