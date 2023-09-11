package com.vinco_orbis.app.Service;

import com.vinco_orbis.app.Repository.HorarioRepository;

import jakarta.transaction.Transactional;

import com.vinco_orbis.app.Model.Horario;
import com.vinco_orbis.app.Model.Pelicula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> getAllHorarios() {
        return horarioRepository.findAll();
    }

    public Horario getHorarioById(Long id) {
        return horarioRepository.findById(id).orElse(null);
    }
    
    @Transactional
    public Horario saveOrUpdateHorario(Horario horario) {
        return horarioRepository.save(horario);
    }
    public Horario existsByHorarioPelicula(Horario horario) {
        return  horarioRepository.findPeliculaByHorario(horario.getHoraInicio(), horario.getHoraFin(), horario.getPelicula_id());
    }
    @Transactional
    public List<Horario> saveAllHorarios(List<Horario> horarios) {
        return horarioRepository.saveAll(horarios);
    }

    public void deleteHorario(Long id) {
        horarioRepository.deleteById(id);
    }
}
