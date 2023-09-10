package com.vinco_orbis.app.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name="horario")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    @ManyToOne
    @JoinColumn(name="pelicula_id")
    private Pelicula pelicula;

    @OneToMany(mappedBy = "horario")
    private List<Asiento> asientos;

    public Horario() {
    }


    public Horario(Long id, LocalTime horaInicio, LocalTime horaFin, Pelicula pelicula, List<Asiento> asientos) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.pelicula = pelicula;
        this.asientos = asientos;
    }

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

    public Pelicula getPelicula() {
        return this.pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public List<Asiento> getAsientos() {
        return this.asientos;
    }

    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }
   
}
