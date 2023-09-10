package com.vinco_orbis.app.Repository;



import com.vinco_orbis.app.Model.Asiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsientoRepository extends JpaRepository<Asiento, Long> {
}
