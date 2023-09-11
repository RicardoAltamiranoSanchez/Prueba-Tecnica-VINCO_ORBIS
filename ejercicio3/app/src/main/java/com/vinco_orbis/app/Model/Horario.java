package com.vinco_orbis.app.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="horario")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="horario_id")
    private Long id;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    
    @NotNull
    @Column(name = "pelicula_id",nullable = false)
    private Integer pelicula_id;
    
    
    @ManyToOne
    @JoinColumn(name="pelicula_id",insertable = false,updatable = false)
    private Pelicula peliculaRelacion;

    @OneToMany(mappedBy = "horarioRelacion")
    private List<Asiento> asientos;

   

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getHoraInicio() {
        return this.horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return this.horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getPelicula_id() {
        return this.pelicula_id;
    }

    public void setPelicula_id(Integer pelicula_id) {
        this.pelicula_id = pelicula_id;
    }

    public Pelicula getPeliculaRelacion() {
        return this.peliculaRelacion;
    }

    public void setPeliculaRelacion(Pelicula peliculaRelacion) {
        this.peliculaRelacion = peliculaRelacion;
    }

    public List<Asiento> getAsientos() {
        return this.asientos;
    }

    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }
  
 
   
}
