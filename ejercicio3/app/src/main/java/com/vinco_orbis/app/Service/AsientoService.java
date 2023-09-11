package com.vinco_orbis.app.Service;

import com.vinco_orbis.app.Repository.AsientoRepository;
import com.vinco_orbis.app.Model.Asiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsientoService {

    @Autowired
    private AsientoRepository asientoRepository;

    public List<Asiento> getAllAsientos() {
        return asientoRepository.findAll();
    }

    public Asiento getAsientoById(Long id) {
        return asientoRepository.findById(id).orElse(null);
    }

    public Asiento saveOrUpdateAsiento(Asiento asiento) {
        return asientoRepository.save(asiento);
    }

    public List<Asiento> saveAllAsientos(List<Asiento> asientos) {
        return asientoRepository.saveAll(asientos);
    }

    public void deleteAsiento(Long id) {
        asientoRepository.deleteById(id);
    }
}
