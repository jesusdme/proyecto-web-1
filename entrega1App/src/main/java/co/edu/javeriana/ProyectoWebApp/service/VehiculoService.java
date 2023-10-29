package co.edu.javeriana.ProyectoWebApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.ProyectoWebApp.model.Vehiculo;
import co.edu.javeriana.ProyectoWebApp.repository.VehiculoRepository;

@Service
public class VehiculoService {
    
    @Autowired
    private VehiculoRepository vehiculoRepository;

    public Vehiculo recuperarVehiculo(Long id) {
        return vehiculoRepository.findById(id).orElseThrow();
    }

    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll();
    }

    public void guardarVehiculo(Vehiculo vehiculo) {
        vehiculoRepository.save(vehiculo);
    }
}
