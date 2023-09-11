package com.vinco_orbis.app.Repository;

import com.vinco_orbis.app.Model.Asiento;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AsientoRepository extends JpaRepository<Asiento, Long> {
 @Query("SELECT a FROM Asiento a JOIN a.horarioRelacion h WHERE a.numero = :numero AND h.id = :horarioId")
Asiento findAsientoByNumeroAndHorario(@Param("numero") String numero, @Param("horarioId") Long horarioId);


}
