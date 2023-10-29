package co.edu.javeriana.ProyectoWebApp.init;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.edu.javeriana.ProyectoWebApp.model.Piso;
import co.edu.javeriana.ProyectoWebApp.model.Vehiculo;
import co.edu.javeriana.ProyectoWebApp.repository.PisoRepository;
import co.edu.javeriana.ProyectoWebApp.repository.VehiculoRepository;

@Component
public class DBInitializer implements CommandLineRunner {

    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private PisoRepository pisoRepository;


    @Override
    public void run(String... args) throws Exception {
        Piso piso1 = new Piso(10, "moto");
        Piso piso2 = new Piso(20, "moto");

        // Guarda los pisos primero para obtener sus IDs
        piso1 = pisoRepository.save(piso1);
        piso2 = pisoRepository.save(piso2);

        Vehiculo vehiculo1 = new Vehiculo("www999", "moto", 0.0, 0.0, piso1);
        Vehiculo vehiculo2 = new Vehiculo("yyy999", "moto", 0.0, 0.0, piso1);
        Vehiculo vehiculo3 = new Vehiculo("zzz999", "moto", 0.0, 0.0, piso2);

        // Guarda los vehículos
        vehiculo1 = vehiculoRepository.save(vehiculo1);
        vehiculo2 = vehiculoRepository.save(vehiculo2);
        vehiculo3 = vehiculoRepository.save(vehiculo3);

        List<Vehiculo> vehiculosP1 = Arrays.asList(vehiculo1, vehiculo2);
        piso1.setVehiculos(vehiculosP1, piso1.getCapacidadMax());

        List<Vehiculo> vehiculosP2 = Arrays.asList(vehiculo3);
        piso2.setVehiculos(vehiculosP2, piso2.getCapacidadMax());

        // Ahora actualiza los pisos en la base de datos
        pisoRepository.save(piso1);
        pisoRepository.save(piso2);


    }

}
