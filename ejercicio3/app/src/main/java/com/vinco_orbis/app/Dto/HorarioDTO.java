package com.vinco_orbis.app.Dto;

import java.time.LocalTime;
import java.util.List;

public class HorarioDTO {

    private Long id;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private List<AsientoDTO> asientos;


    public HorarioDTO() {
    }

    public HorarioDTO(Long id, LocalTime horaInicio, LocalTime horaFin, List<AsientoDTO> asientos) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
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

    public List<AsientoDTO> getAsientos() {
        return this.asientos;
    }

    public void setAsientos(List<AsientoDTO> asientos) {
        this.asientos = asientos;
    }
   
}
