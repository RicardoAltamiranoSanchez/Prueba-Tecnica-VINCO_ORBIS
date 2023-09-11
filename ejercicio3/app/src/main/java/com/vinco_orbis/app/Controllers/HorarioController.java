package com.vinco_orbis.app.Controllers;

import com.vinco_orbis.app.Excepciones.BadRequestException;
import com.vinco_orbis.app.Excepciones.ConflictException;
import com.vinco_orbis.app.Excepciones.InternalServerErrorException;
import com.vinco_orbis.app.Excepciones.ResourceNotFoundException;
import com.vinco_orbis.app.Service.HorarioService;
import com.vinco_orbis.app.Model.Horario;
import com.vinco_orbis.app.Model.Pelicula;
import com.vinco_orbis.app.Service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

@RestController
@RequestMapping("/api/horarios")
@Tag(name = "Controller de horarios", description = "CRUD de horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;
    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    @Operation(summary = "Obtener todos los horarios")
    public List<Horario> getAllHorarios() {
        List<Horario> horarios = horarioService.getAllHorarios();
        if (horarios == null || horarios.isEmpty()) {
            throw new ResourceNotFoundException("No hay horarios disponibles en este momento.");
        }
        return horarios;
        
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un horario por su ID")
    public Horario getHorarioById(
            @Parameter(description = "ID del horario", required = true) @PathVariable Long id) {

        if (id <= 0) {
            throw new BadRequestException("El ID proporcionado no es valido.");
        }

        Horario horario = horarioService.getHorarioById(id);
        if (horario == null) {
            throw new ResourceNotFoundException("No se encontró el horario con el ID: " + id);
        }
        return horario;
    }

    @PostMapping
    @Operation(summary = "Agregar nuevo horario")
    public Map<String, String> saveHorario(
            @Parameter(description = "Horario a guardar", required = true) @Valid @RequestBody Horario horario) {

        Horario existHorario = horarioService.existsByHorarioPelicula(horario);
        if (existHorario != null) {
            Pelicula pelicula = peliculaService.getPeliculaById(Long.valueOf(existHorario.getPelicula_id()));

            throw new ConflictException("Este Horario ya existe para esta pelicula :" + pelicula.getTitulo()
                    + " con el Id_pelicula:" + pelicula.getId() + " con el Id_horario:" + existHorario.getId());
        }

        Horario savedHorario = horarioService.saveOrUpdateHorario(horario);
        if (savedHorario == null) {
            throw new InternalServerErrorException("Ocurrio un error al guardar el horario.");
        }

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Horario agregado con exito");
        return response;
    }

    @PostMapping("/addHorarios")
    @Operation(summary = "Agregar multiples horarios")
    public Map<String, String> saveMultipleHorarios(
            @Parameter(description = "Horario a guardar", required = true) @Valid @RequestBody List<Horario> horarios) {

        for (Horario horario : horarios) {
            Horario existHorario = horarioService.existsByHorarioPelicula(horario);
            if (existHorario != null) {
                Pelicula pelicula = peliculaService.getPeliculaById(Long.valueOf(existHorario.getPelicula_id()));
                throw new ConflictException("Este Horario ya existe para esta pelicula :" + pelicula.getTitulo()
                        + " con el Id_pelicula:" + pelicula.getId() + " con el Id_horario:" + existHorario.getId());
            }
        }
        try {
            horarioService.saveAllHorarios(horarios);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Horarios agregado  con exito");
            return response;
        } catch (Exception e) {
            throw new InternalServerErrorException("Ocurrio un error al guardar las peliculas.");
        }

    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar horario")
    public Map<String, String> updateHorario(
            @Parameter(description = "Horario a actualizar", required = true) @RequestBody Horario horario,

            @Parameter(description = "ID del horario a actualizar", required = true) @PathVariable Long id) {

        if (horarioService.getHorarioById(id) == null) {
            throw new ResourceNotFoundException("No se encontro el horario con el ID: " + id);
        }

        Horario existHorario = horarioService.existsByHorarioPelicula(horario);
        if (existHorario != null) {
            Pelicula pelicula = peliculaService.getPeliculaById(Long.valueOf(existHorario.getPelicula_id()));

            throw new ConflictException("Este horario ya existe para la película con titulo: " + pelicula.getTitulo()
                    + "y Id:" + pelicula.getId());
        }

        try {
            horario.setId(id);
            horarioService.saveOrUpdateHorario(horario);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Horario actualizado con exito");
            return response;
        } catch (Exception e) {
            throw new InternalServerErrorException("Ocurrio un error al actualizar el horario.");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar horario")
    public Map<String, String> deleteHorario(
            @Parameter(description = "ID del horario a eliminar", required = true) @PathVariable Long id) {

        Horario horario = horarioService.getHorarioById(id);
        if (horario == null) {
            throw new ResourceNotFoundException("Horario con ID " + id + " no encontrado.");
        }

        try {
            horarioService.deleteHorario(id);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Horario eliminado con exito");
            return response;
        } catch (Exception e) {
            throw new InternalServerErrorException("Ocurrio un error al intentar eliminar el horario con ID " + id);
        }
    }

}
