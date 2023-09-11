package com.vinco_orbis.app.Service;

import com.vinco_orbis.app.Repository.AsientoRepository;
import com.vinco_orbis.app.Model.Asiento;
import com.vinco_orbis.app.Model.Horario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsientoService {

    @Autowired
    private AsientoRepository asientoRepository;
    @Autowired
    private HorarioService horarioService;
    public List<Asiento> getAllAsientos() {
        return asientoRepository.findAll();
    }

    public Asiento getAsientoById(Long id) {
        return asientoRepository.findById(id).orElse(null);
    }
    public long countAllAsientos() {
        return asientoRepository.count();
    }
    public Asiento saveOrUpdateAsiento(Asiento asiento) {
        return asientoRepository.save(asiento);
    }
  public boolean findAsientoByNumeroAndHorario(Asiento asiento) {
        Horario horario = horarioService.getHorarioById( Long.valueOf(asiento.getHorario_id()));
        if (horario == null) {
            return false;
        }
        Asiento asientoExistente = asientoRepository.findAsientoByNumeroAndHorario(asiento.getNumero(), Long.valueOf(asiento.getHorario_id()));

        if (asientoExistente != null) {
        
            return true;
        }
        return false;
    }
    public List<Asiento> saveAllAsientos(List<Asiento> asientos) {
        return asientoRepository.saveAll(asientos);
    }

    public void deleteAsiento(Long id) {
        asientoRepository.deleteById(id);
    }
}
