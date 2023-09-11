package com.vinco_orbis.app.Repository;


import com.vinco_orbis.app.Model.Horario;


import java.time.LocalTime;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    @Query("SELECT h FROM Horario h JOIN h.peliculaRelacion p WHERE h.horaInicio = :horarioInicio AND h.horaFin = :horarioFin AND p.id = :peliculaId")
    Horario findPeliculaByHorario(@Param("horarioInicio") LocalTime horarioInicio, @Param("horarioFin") LocalTime horarioFin, @Param("peliculaId") Integer peliculaId);
  
}
