package com.vinco_orbis.app.Controllers;

import com.vinco_orbis.app.Service.AsientoService;
import com.vinco_orbis.app.Service.HorarioService;

import com.vinco_orbis.app.Excepciones.BadRequestException;
import com.vinco_orbis.app.Excepciones.BusinessException;
import com.vinco_orbis.app.Excepciones.ConflictException;
import com.vinco_orbis.app.Excepciones.InternalServerErrorException;
import com.vinco_orbis.app.Excepciones.ResourceNotFoundException;
import com.vinco_orbis.app.Model.Asiento;
import com.vinco_orbis.app.Model.Horario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/asientos")
@Tag(name = "Controller de asientos", description = "CRUD de asientos")
public class AsientoController {

    @Autowired
    private AsientoService asientoService;
  @Autowired
    private HorarioService horarioService;
   @GetMapping
@Operation(summary = "Obtener todos los asientos")
public List<Asiento> getAllAsientos() {
    List<Asiento> asientos = asientoService.getAllAsientos();
    if (asientos == null || asientos.isEmpty()) {
        throw new ResourceNotFoundException("No hay asientos disponibles en este momento.");
    }
    return asientos;
}


  @GetMapping("/{id}")
@Operation(summary = "Obtener un asiento por su ID")
public Asiento getAsientoById(
        @Parameter(description = "ID del asiento", required = true) @PathVariable Long id) {

    if (id <= 0) {
        throw new BadRequestException("El ID proporcionado no es válido.");
    }

    Asiento asiento = asientoService.getAsientoById(id);
    if (asiento == null) {
        throw new ResourceNotFoundException("No se encontró el asiento con el ID: " + id);
    }
    return asiento;
}


  @PostMapping
@Operation(summary = "Agregar nuevo asiento")
public Map<String, String> saveAsiento(
        @Parameter(description = "Asiento a guardar", required = true) @Valid @RequestBody Asiento asiento) {

    boolean existAsiento = asientoService.findAsientoByNumeroAndHorario(asiento);
    if (existAsiento) {
       Horario horario =  horarioService.getHorarioById( Long.valueOf(asiento.getHorario_id()));
      throw new ConflictException("Este asiento ya esta ocupado para el horario inicio: " + horario.getHoraInicio()+" Fin: "+horario.getHoraFin()+"Id de pelicula "+horario.getPelicula_id()+" Id de horario "+horario.getId());
    }
    
 
    if (asientoService.countAllAsientos() >= 50) {
        throw new BusinessException("No se pueden crear mas de 50 asientos.");
    }
    Asiento savedAsiento = asientoService.saveOrUpdateAsiento(asiento);
    if (savedAsiento == null) {
        throw new InternalServerErrorException("Ocurrio un error al guardar el asiento.");
    }

    Map<String, String> response = new HashMap<>();
    response.put("mensaje", "Asiento agregado con exito");
    return response;
}


@PostMapping("/addAsientos")
@Operation(summary = "Agregar multiples asientos")
public Map<String, String> saveMultipleAsientos(
        @Parameter(description = "Listado de asientos para agregar", required = true) @Valid @RequestBody List<Asiento> asientos) {

    for (Asiento asiento : asientos) {
      boolean existAsiento = asientoService.findAsientoByNumeroAndHorario(asiento);
    if (existAsiento) {
       Horario horario =  horarioService.getHorarioById( Long.valueOf(asiento.getHorario_id()));
      throw new ConflictException("Este asiento ya esta ocupado para el horario inicio: " + horario.getHoraInicio()+" Fin: "+horario.getHoraFin()+"Id de pelicula "+horario.getPelicula_id()+" Id de horario "+horario.getId());
    }
    
    }
    try {
        asientoService.saveAllAsientos(asientos);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Asientos agregados con éxito");
        return response;
    } catch (Exception e) {
        throw new InternalServerErrorException("Ocurrio un error al guardar los asientos.");
    }
}

@PutMapping("/{id}")
@Operation(summary = "Actualizar asiento")
public ResponseEntity<Object> updateAsiento(
        @Parameter(description = "El objeto del body para el asiento a actualizar", required = true) 
        @RequestBody Asiento asiento,
        @Parameter(description = "El id para el asiento que queremos actualizar", required = true) 
        @PathVariable Long id) {

    if (asientoService.getAsientoById(id) == null) {
        throw new ResourceNotFoundException("No se encontró el asiento con el ID: " + id);
    }
  boolean existAsiento = asientoService.findAsientoByNumeroAndHorario(asiento);
    if (existAsiento) {
       Horario horario =  horarioService.getHorarioById( Long.valueOf(asiento.getHorario_id()));
      throw new ConflictException("Este asiento ya esta ocupado para el horario inicio: " + horario.getHoraInicio()+" Fin: "+horario.getHoraFin()+"Id de pelicula "+horario.getPelicula_id()+" Id de horario "+horario.getId());
    }
    
 
    try {
        asiento.setId(id);
        Asiento updatedAsiento = asientoService.saveOrUpdateAsiento(asiento);
        if (updatedAsiento == null) {
            throw new InternalServerErrorException("Ocurrio un error al actualizar el asiento.");
        }
        return ResponseEntity.ok(updatedAsiento);
    } catch (InternalServerErrorException | ResourceNotFoundException | ConflictException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


@DeleteMapping("/{id}")
@Operation(summary = "Eliminar asiento")
public ResponseEntity<Object> deleteAsiento(
        @Parameter(description = "El id para eliminar asiento", required = true) 
        @PathVariable Long id) {
    
    Asiento asiento = asientoService.getAsientoById(id);
    if (asiento == null) {
        throw new ResourceNotFoundException("Asiento con ID " + id + " no encontrado.");
    }

    try {
        asientoService.deleteAsiento(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "Asiento eliminado con exito");
        return ResponseEntity.ok(response);
    } catch (ResourceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    } catch (Exception e) {
        throw new InternalServerErrorException("Ocurrio un error al intentar eliminar el asiento con ID " + id);
    }
}


}
