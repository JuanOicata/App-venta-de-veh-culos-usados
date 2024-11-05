package co.ucentral.AppventaVehiculos.controladores;

import co.ucentral.AppventaVehiculos.persistencia.entidades.Vehiculo;
import co.ucentral.AppventaVehiculos.servicios.VehiculoServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class VehiculoControlador {

    @Autowired
    VehiculoServicio vehiculoServicio;

    // Mostrar la página de registro de vehículos
    @GetMapping("/registro-vehiculo")
    public String mostrarFormularioDeRegistroVehiculo(Model model) {
        Vehiculo vehiculo = new Vehiculo();
        model.addAttribute("vehiculo", vehiculo);
        return "registro-vehiculo";
    }

    // Guardar el vehículo en la base de datos
    @PostMapping("/almacenar-vehiculo")
    public String registrarVehiculo(@ModelAttribute("vehiculo") Vehiculo vehiculo, Model model) {
        vehiculoServicio.registrarVehiculo(vehiculo);
        model.addAttribute("mensaje", "Vehículo registrado exitosamente");
        return "registro-vehiculo"; // Retorna a la misma página después del registro
    }
    @GetMapping("/pantalla-vendedor")
    public String mostrarFormularioDeVendedor(Model model) {
        return "pantallaVendedor";
    }

}
