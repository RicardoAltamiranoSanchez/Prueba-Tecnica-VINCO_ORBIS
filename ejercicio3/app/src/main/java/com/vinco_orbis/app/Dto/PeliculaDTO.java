package com.vinco_orbis.app.Dto;

import java.util.List;

public class PeliculaDTO {

    private Long id;
    private String titulo;
    private String director;
    private String genero;
    private String clasificacion;
    private List<HorarioDTO> horarios;


    public PeliculaDTO() {
    }

    public PeliculaDTO(Long id, String titulo, String director, String genero, String clasificacion, List<HorarioDTO> horarios) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.clasificacion = clasificacion;
        this.horarios = horarios;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return this.director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasificacion() {
        return this.clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public List<HorarioDTO> getHorarios() {
        return this.horarios;
    }

    public void setHorarios(List<HorarioDTO> horarios) {
        this.horarios = horarios;
    }
   
}
