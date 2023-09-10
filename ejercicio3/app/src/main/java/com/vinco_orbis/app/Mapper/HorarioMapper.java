package com.vinco_orbis.app.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.vinco_orbis.app.Model.Horario;
import com.vinco_orbis.app.Dto.HorarioDTO;
import java.util.stream.Collectors;

@Component
public class HorarioMapper {

    @Autowired
    private AsientoMapper asientoMapper;

    public HorarioDTO toDTO(Horario horario) {
        HorarioDTO dto = new HorarioDTO();
        dto.setId(horario.getId());
        dto.setHoraInicio(horario.getHoraInicio());
        dto.setHoraFin(horario.getHoraFin());
        if (horario.getAsientos() != null && !horario.getAsientos().isEmpty()) {
            dto.setAsientos(horario.getAsientos().stream().map(asientoMapper::toDTO).collect(Collectors.toList()));
        }
        return dto;
    }

    public Horario toEntity(HorarioDTO dto) {
        Horario horario = new Horario();
        horario.setId(dto.getId());
        horario.setHoraInicio(dto.getHoraInicio());
        horario.setHoraFin(dto.getHoraFin());
      
        return horario;
    }
}
