package co.edu.javeriana.ProyectoWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.ProyectoWebApp.model.Piso;

@Repository
public interface PisoRepository extends JpaRepository<Piso, Long> {
    
}
