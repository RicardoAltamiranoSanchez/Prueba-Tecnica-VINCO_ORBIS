package com.vinco_orbis.app.Mapper;

import org.springframework.stereotype.Component;
import com.vinco_orbis.app.Model.Asiento;
import com.vinco_orbis.app.Dto.AsientoDTO;

@Component
public class AsientoMapper {

    public AsientoDTO toDTO(Asiento asiento) {
        AsientoDTO dto = new AsientoDTO();
        dto.setId(asiento.getId());
        dto.setNumero(asiento.getNumero());
        dto.setEstado(asiento.getEstado());
        return dto;
    }

    public Asiento toEntity(AsientoDTO dto) {
        Asiento asiento = new Asiento();
        asiento.setId(dto.getId());
        asiento.setNumero(dto.getNumero());
        asiento.setEstado(dto.getEstado());
      
        return asiento;
    }
}
