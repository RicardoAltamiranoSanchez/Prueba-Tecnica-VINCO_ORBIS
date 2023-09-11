package com.vinco_orbis.app.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.vinco_orbis.app.Model.Pelicula;
import com.vinco_orbis.app.Dto.PeliculaDTO;
import java.util.stream.Collectors;

@Component
public class PeliculaMapper {

    @Autowired
    private HorarioMapper horarioMapper;

    public PeliculaDTO toDTO(Pelicula pelicula) {
        PeliculaDTO dto = new PeliculaDTO();
        dto.setId(pelicula.getId());
        dto.setTitulo(pelicula.getTitulo());
        dto.setDirector(pelicula.getDirector());
        dto.setGenero(pelicula.getGenero());
        dto.setClasificacion(pelicula.getClasificacion());
        dto.setDescripcion(pelicula.getDescripcion());
        dto.setRaking(pelicula.getRaking());
        dto.setImagen(pelicula.getImagen());
        if (pelicula.getHorarios() != null && !pelicula.getHorarios().isEmpty()) {
            dto.setHorarios(pelicula.getHorarios().stream().map(horarioMapper::toDTO).collect(Collectors.toList()));
        }
        return dto;
    }

    public Pelicula toEntity(PeliculaDTO dto) {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(dto.getId());
        pelicula.setTitulo(dto.getTitulo());
        pelicula.setDirector(dto.getDirector());
        pelicula.setGenero(dto.getGenero());
        pelicula.setClasificacion(dto.getClasificacion());
        pelicula.setDescripcion(dto.getDescripcion());
        pelicula.setRaking(dto.getRaking());
        pelicula.setImagen(dto.getImagen());
      
        return pelicula;
    }
}
