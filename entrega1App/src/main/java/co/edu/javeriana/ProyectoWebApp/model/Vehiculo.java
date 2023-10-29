package co.edu.javeriana.ProyectoWebApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TablaVehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID_Vehiculo;

    private String placa;

    private String tipoV;

    private double tarifa;

    private double minutos;

    @ManyToOne
    Piso piso;

    public Vehiculo() {

    }

    public Vehiculo(String placa, String tipoV, double tarifa, double minutos, Piso piso) {
        this.placa = placa;
        this.tipoV = tipoV;
        this.tarifa = tarifa;
        this.minutos = minutos;
        this.piso = piso;
    }

    public Piso getPiso() {
        return piso;
    }

    public void setPiso(Piso piso) {
        this.piso = piso;
    }

    public Long getId() {
        return ID_Vehiculo;
    }

    public void setId(Long id) {
        this.ID_Vehiculo = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoV() {
        return tipoV;
    }

    public void setTipoV(String tipoV) {
        this.tipoV = tipoV;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    public double getMinutos() {
        return minutos;
    }

    public void setMinutos(double minutos) {
        this.minutos = minutos;
    }

    public double calcularCostoTotal() {
        return tarifa * minutos;
    }

}
