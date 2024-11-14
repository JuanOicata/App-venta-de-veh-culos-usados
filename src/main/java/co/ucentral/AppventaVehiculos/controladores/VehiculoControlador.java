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

    // Carga el formulario de registro de vehículo
    @GetMapping("/registro-vehiculo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        return "registro-vehiculo";
    }

    // Maneja el envío del formulario y guarda el vehículo
    @PostMapping("/almacenar-vehiculo")
    public String registrarVehiculo(@ModelAttribute("elvehiculo") Vehiculo vehiculo, Model model) {
        vehiculoServicio.registrarVehiculo(vehiculo);
        model.addAttribute("mensaje", "Vehículo registrado exitosamente");
        model.addAttribute("elvehiculo", new Vehiculo()); // Limpiar el formulario
        model.addAttribute("vehiculos", vehiculoServicio.obtenerTodosLosVehiculos()); // Obtener y mostrar vehículos
        return "pantallaVendedor"; // Redirigir a la misma página
    }

    // Método para mostrar la página inicial del vendedor con la lista de vehículos
    @GetMapping("/pantalla-vendedor")
    public String mostrarFormularioDeVendedor(Model model) {
        model.addAttribute("elvehiculo", new Vehiculo());
        model.addAttribute("vehiculos", vehiculoServicio.obtenerTodosLosVehiculos()); // Obtener y mostrar vehículos
        return "pantallaVendedor"; // Renderizar la página
    }
    @GetMapping("/pantalla-comprador")
    public String mostrarFormularioDeComprador(Model model) {
        return "pantallaComprador"; // Renderizar la página
    }
}
