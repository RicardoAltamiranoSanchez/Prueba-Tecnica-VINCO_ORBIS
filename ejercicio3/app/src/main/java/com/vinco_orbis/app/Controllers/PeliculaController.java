package com.vinco_orbis.app.Controllers;

import com.vinco_orbis.app.Service.PeliculaService;
import com.vinco_orbis.app.Model.Pelicula;
import com.vinco_orbis.app.Excepciones.BadRequestException;
import com.vinco_orbis.app.Excepciones.ConflictException;
import com.vinco_orbis.app.Excepciones.InternalServerErrorException;
import com.vinco_orbis.app.Excepciones.ResourceNotFoundException;

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
@RequestMapping("/api/peliculas")
@Tag(name = "Controller de peliculas", description = "CRUD de películas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    @Operation(summary = "Obtener todas las peliculas")
    public List<Pelicula> getAllPeliculas() {
        List<Pelicula> peliculas = peliculaService.getAllPeliculas();
        if (peliculas == null || peliculas.isEmpty()) {
            throw new ResourceNotFoundException("No hay peliculas disponibles en este momento.");
        }
        return peliculas;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una película por su ID")
    public Pelicula getPeliculaById(
            @Parameter(description = "Obtener pelicula por id", required = true) @PathVariable Long id) {

        if (id <= 0) {
            throw new BadRequestException("El ID proporcionado no es valido.");
        }

        Pelicula pelicula = peliculaService.getPeliculaById(id);
        if (pelicula == null) {
            throw new ResourceNotFoundException("No se encontro la película con el ID: " + id);
        }
        return pelicula;
    }

    @PostMapping
    @Operation(summary = "Agregar nueva pelicula")
    public Map<String, String> savePelicula(
            @Parameter(description = "Agregar nueva pelicula", required = true) @Valid @RequestBody Pelicula pelicula) {

        if (peliculaService.existsByTitulo(pelicula.getTitulo())) {
            throw new ConflictException("Esta película ya existe "+pelicula.getTitulo());
        }

        Pelicula savedPelicula = peliculaService.saveOrUpdatePelicula(pelicula);
        if (savedPelicula == null) {
            throw new InternalServerErrorException("Ocurrio un error al guardar la película.");
        }

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Peliculas agregada con exito");
        return response;
    }

    @PostMapping("/addPeliculas")
    @Operation(summary = "Agregar multiples peliculas")
    public Map<String, String> saveMultiplePeliculas(
            @Parameter(description = "Listado de peliculas para agregar", required = true)
            @RequestBody List<Pelicula> peliculas) {

        if (peliculas == null || peliculas.isEmpty()) {
            throw new BadRequestException("La lista de peliculas no puede estar vacia.");
        }

        for (Pelicula pelicula : peliculas) {
            if (peliculaService.existsByTitulo(pelicula.getTitulo())) {
                throw new ConflictException("La pelicula con el título '" + pelicula.getTitulo() + "' ya existe.");
            }
        }

        try {
            peliculaService.saveAllPeliculas(peliculas);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Peliculas Agregadas con exito con exito");
            return response;
        } catch (Exception e) {
            throw new InternalServerErrorException("Ocurrio un error al guardar las peliculas.");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar pelicula")
    public Map<String, String> updatePelicula(
            @Parameter(description = "El objeto del body para la pelicula a actualizar", required = true) 
            @RequestBody Pelicula pelicula,

            @Parameter(description = "El id para la pelicula que queremos actualizar", required = true)
            @PathVariable Long id) {

        if (peliculaService.getPeliculaById(id) == null) {
            throw new ResourceNotFoundException("No se encontró la pelicula con el ID: " + id);
        }
   
        if (peliculaService.existsByTitulo(pelicula.getTitulo())) {
            throw new ConflictException("El titulo '" + pelicula.getTitulo() + "' ya esta en uso por otra pelicula.");
        }

        try {
            pelicula.setId(id);
            peliculaService.saveOrUpdatePelicula(pelicula);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Pelicula actualizada con exito");
            return response;
        } catch (Exception e) {
            throw new InternalServerErrorException("Ocurrio un error al actualizar la pelicula.");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar película")
    public Map<String, String> deletePelicula(
            @Parameter(description = "El id para eliminar pelicula", required = true) @PathVariable Long id) {

        Pelicula pelicula = peliculaService.getPeliculaById(id);
        if (pelicula == null) {
            throw new ResourceNotFoundException("Pelicula con ID " + id + " no encontrada.");
        }

        try {

            peliculaService.deletePelicula(id);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Pelicula eliminada con exito");
            return response;
        } catch (Exception e) {
            throw new InternalServerErrorException("Ocurrio un error al intentar eliminar la película con ID " + e);
        }
    }

}
