package com.vinco_orbis.app.Controllers;

import com.vinco_orbis.app.Service.PeliculaService;
import com.vinco_orbis.app.Model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@Tag(name = "Controller de peliculas", description = "CRUD de  películas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    @Operation(summary = "Obtener todas las peliculas")
    public List<Pelicula> getAllPeliculas() {
        return peliculaService.getAllPeliculas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una película por su ID")
    public Pelicula getPeliculaById(
        @Parameter(description = "Obtener pelicula por id", required = true) 
        @PathVariable Long id) {
        
        return peliculaService.getPeliculaById(id);
    }

    @PostMapping
    @Operation(summary = "Agregar nueva pelicula")
    public Pelicula savePelicula(
        @Parameter(description = "Agregar nueva pelicula", required = true) 
        @RequestBody Pelicula pelicula) {
        
        return peliculaService.saveOrUpdatePelicula(pelicula);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar pelicula")
    public Pelicula updatePelicula(
        @Parameter(description = "El objeto del body para la pelicual a actualizar", required = true) 
        @RequestBody Pelicula pelicula,
        
        @Parameter(description = "El id para la pelicula que queremos actualizar", required = true) 
        @PathVariable Long id) {
        
        pelicula.setId(id);
        return peliculaService.saveOrUpdatePelicula(pelicula);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar película ")
    public void deletePelicula(
        @Parameter(description = "El id para eliminar pelicula", required = true) 
        @PathVariable Long id) {
        
        peliculaService.deletePelicula(id);
    }
}
