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

import co.edu.javeriana.ProyectoWebApp.model.Piso;
import co.edu.javeriana.ProyectoWebApp.service.PisoService;

@Controller
@RequestMapping("/piso")
public class PisoController {

    @Autowired
    private PisoService pisoService;

    @GetMapping("/view/{ID_Piso}")
    public ModelAndView recuperarPiso(@PathVariable Long ID_Piso) {
        Piso piso = pisoService.recuperarPiso(ID_Piso);
        // Calcula la disponibilidad y actualiza el valor en el objeto piso
        piso.setDisponible(piso.getVehiculos(), piso.getCapacidadMax());
        // Guarda el objeto piso actualizado en la base de datos
        pisoService.guardarPiso(piso);

        ModelAndView pisoView = new ModelAndView("piso-view");
        pisoView.addObject("piso", piso);
        
        return pisoView;
    }

    @GetMapping("/list")
    public ModelAndView listarPisos() {
        List<Piso> pisos = pisoService.listarPisos();
        ModelAndView pisoListView = new ModelAndView("piso-list");
        pisoListView.addObject("allPisos", pisos);
        return pisoListView;
    }

    @GetMapping("/edit/{ID_Piso}")
    public ModelAndView editarPiso(@PathVariable Long ID_Piso) {
        Piso piso = pisoService.recuperarPiso(ID_Piso);
        ModelAndView pisoEditView = new ModelAndView("piso-edit");
        pisoEditView.addObject("piso", piso);
        pisoEditView.addObject("minimo", piso.getVehiculos().size());//editar para el area 
        return pisoEditView;
    }

    @GetMapping("/create")
    public ModelAndView crearPiso() {
        Piso nuevoPiso = new Piso(); // Crea una instancia de Piso para el nuevo piso
        ModelAndView pisoCreateView = new ModelAndView("piso-create");
        pisoCreateView.addObject("nuevoPiso", nuevoPiso);
        return pisoCreateView;
    
    }

    @PostMapping("/save")
    public RedirectView guardarPiso(@ModelAttribute Piso p) {
        pisoService.guardarPiso(p); // Guardar el piso con la nueva capacidad máxima
    
        // Recalcular el campo de disponibilidad
        Piso pisoActualizado = pisoService.recuperarPiso(p.getID_Piso());
        pisoActualizado.setDisponible(pisoActualizado.getVehiculos(), pisoActualizado.getCapacidadMax());
    
        // Guardar nuevamente el piso con el campo de disponibilidad actualizado
        pisoService.guardarPiso(pisoActualizado);
        String url="/piso/view/"+p.getID_Piso();
        return new RedirectView(url);
    }

    @GetMapping("/delete/{ID_Piso}")
    public RedirectView eliminarPiso(@PathVariable Long ID_Piso) {
        // Verificar si el piso con el ID proporcionado existe antes de eliminarlo
        Piso piso = pisoService.recuperarPiso(ID_Piso);
        if (piso != null) {
            pisoService.borrarPiso(ID_Piso); // Utiliza el método borrarPiso de tu servicio
            return new RedirectView("/piso/list");
        } else {
            // Manejar el caso en que no se encuentra el piso
            // Puedes redirigir a una página de error o mostrar un mensaje al usuario
            return new RedirectView("/piso/list");
        }
    }

    
}
