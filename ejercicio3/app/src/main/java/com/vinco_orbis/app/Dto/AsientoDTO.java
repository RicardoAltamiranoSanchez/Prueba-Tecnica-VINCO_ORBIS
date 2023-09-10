package com.vinco_orbis.app.Dto;

public class AsientoDTO {

    private Long id;
    private String numero;
    private String estado;


    public AsientoDTO() {
    }

    public AsientoDTO(Long id, String numero, String estado) {
        this.id = id;
        this.numero = numero;
        this.estado = estado;
    }

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

}
