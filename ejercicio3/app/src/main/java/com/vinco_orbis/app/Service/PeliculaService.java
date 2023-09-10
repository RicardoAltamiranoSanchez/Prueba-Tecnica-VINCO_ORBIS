package com.vinco_orbis.app.Service;
import com.vinco_orbis.app.Repository.PeliculaRepository;
import com.vinco_orbis.app.Model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<Pelicula> getAllPeliculas() {
        return peliculaRepository.findAll();
    }

    public Pelicula getPeliculaById(Long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    public Pelicula saveOrUpdatePelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public void deletePelicula(Long id) {
        peliculaRepository.deleteById(id);
    }
}
