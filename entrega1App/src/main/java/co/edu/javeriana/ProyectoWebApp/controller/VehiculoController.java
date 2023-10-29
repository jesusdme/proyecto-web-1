package co.edu.javeriana.ProyectoWebApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import co.edu.javeriana.ProyectoWebApp.model.Vehiculo;
import co.edu.javeriana.ProyectoWebApp.service.VehiculoService;

@Controller
@RequestMapping("/vehiculo")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/view/{ID_Vehiculo}")
    public ModelAndView recuperarVehiculo(@PathVariable Long ID_Vehiculo) {
        Vehiculo vehiculo = vehiculoService.recuperarVehiculo(ID_Vehiculo);
        ModelAndView vehiculoView = new ModelAndView("vehiculo-view");
        vehiculoView.addObject("vehiculo", vehiculo);
        return vehiculoView;
    }

    @GetMapping("/list")
    public ModelAndView listarVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.listarVehiculos();
        ModelAndView vehiculoListView = new ModelAndView("vehiculo-list");
        vehiculoListView.addObject("allVehiculos", vehiculos);
        return vehiculoListView;
    }

    @GetMapping("/edit/{ID_Vehiculo}")
    public ModelAndView editarVehiculo(@PathVariable Long ID_Vehiculo) {
        Vehiculo vehiculo = vehiculoService.recuperarVehiculo(ID_Vehiculo);
        ModelAndView vehiculoEditView = new ModelAndView("vehiculo-edit");
        vehiculoEditView.addObject("vehiculo", vehiculo);
        return vehiculoEditView;
    }
    

    @PostMapping("/save")
    public RedirectView guardarVehiculo(@ModelAttribute Vehiculo v) {
        vehiculoService.guardarVehiculo(v);
        return new RedirectView("/vehiculo/list");
    }

}
