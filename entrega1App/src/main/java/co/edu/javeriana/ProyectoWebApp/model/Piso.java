package co.edu.javeriana.ProyectoWebApp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TablaPisos")
public class Piso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Piso;

    private int capacidadMax;

    private String tipoVehiculo;

    private int disponible;

    @OneToMany(mappedBy = "piso", cascade = CascadeType.ALL, orphanRemoval = false)
    List<Vehiculo> vehiculos = new ArrayList<>();

    public Piso() {
    }

    public Piso(int capacidadMax, String tipoVehiculo) {
        this.capacidadMax = capacidadMax;
        this.disponible = capacidadMax;
        this.tipoVehiculo = tipoVehiculo;
    }

    public Long getID_Piso() {
        return ID_Piso;
    }

    public void setID_Piso(Long iD_Piso) {
        ID_Piso = iD_Piso;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(List<Vehiculo> vehiculos, int capacidadMax) {
        this.disponible = capacidadMax - vehiculos.size();
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

public void setVehiculos(List<Vehiculo> vehiculos, int capacidadMax) {
    // Filtrar vehículos por tipo
    List<Vehiculo> vehiculosFiltrados = vehiculos.stream()
        .filter(v -> v.getTipoV().equals(this.tipoVehiculo))
        .collect(Collectors.toList());

    if (this.vehiculos.size() + vehiculosFiltrados.size() <= capacidadMax) {
        this.vehiculos.addAll(vehiculosFiltrados);
        this.disponible = capacidadMax - this.vehiculos.size();
    } else {
        // Manejar el caso en el que no se pueden agregar más vehículos debido a la capacidad máxima.
        // Puedes registrar un mensaje de error o tomar cualquier otra acción apropiada.
        Logger.getLogger(getClass().getName()).severe("No se pueden agregar más vehículos debido a la capacidad máxima.");
    }
}

}
