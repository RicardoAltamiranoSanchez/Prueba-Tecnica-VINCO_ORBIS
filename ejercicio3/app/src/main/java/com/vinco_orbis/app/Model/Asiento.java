package com.vinco_orbis.app.Model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="asiento")
public class Asiento  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String numero; 
    private String estado; 

    @Column(name = "horario_id")
    private Integer horario_id;
    
    @ManyToOne
    @JoinColumn(name="horario_id" ,insertable = false,updatable = false)
    private Horario horarioRelacion;
    
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getHorario_id() {
        return this.horario_id;
    }

    public void setHorario_id(Integer horario_id) {
        this.horario_id = horario_id;
    }

    public Horario getHorarioRelacion() {
        return this.horarioRelacion;
    }

    public void setHorarioRelacion(Horario horarioRelacion) {
        this.horarioRelacion = horarioRelacion;
    }


}
