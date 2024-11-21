package co.ucentral.AppventaVehiculos.controladores;

import co.ucentral.AppventaVehiculos.persistencia.entidades.Vehiculo;
import co.ucentral.AppventaVehiculos.servicios.VehiculoServicio;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class VehiculoControlador {

    @Autowired
    VehiculoServicio vehiculoServicio;

    @GetMapping("/cerrar-sesion")
    public String cerrarSesion(HttpServletRequest request) {
        // Invalidar la sesión del usuario
        request.getSession().invalidate();

        // Redirigir a la página de inicio de sesión
        return "redirect:/inicio-sesion";
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

    @GetMapping("/editar-vehiculo/{id}")
    public String cargarVehiculoParaEdicion(@PathVariable Long id, Model model) {
        Vehiculo vehiculo = vehiculoServicio.obtenerVehiculoPorId(id);
        if (vehiculo == null) {
            model.addAttribute("mensaje", "Vehículo no encontrado.");
            return "redirect:/pantalla-vendedor";
        }
        model.addAttribute("elvehiculo", vehiculo);
        return "pantallaVendedor";
    }

    @PostMapping("/editar-vehiculo")
    public String editarVehiculo(@ModelAttribute("elvehiculo") Vehiculo vehiculo, Model model) {
        vehiculoServicio.actualizarVehiculo(vehiculo);
        model.addAttribute("mensaje", "Vehículo actualizado exitosamente");
        model.addAttribute("vehiculos", vehiculoServicio.obtenerTodosLosVehiculos());
        return "pantallaVendedor";
    }
    // Eliminar un vehículo
    @GetMapping("/eliminar-vehiculo/{id}")
    public String eliminarVehiculo(@PathVariable Long id, Model model) {
        boolean eliminado = vehiculoServicio.borrarVehiculo(id);

        if (eliminado) {
            model.addAttribute("mensaje", "Vehículo eliminado exitosamente");
        } else {
            model.addAttribute("mensaje", "Error al eliminar el vehículo");
        }

        // Obtener la lista actualizada de vehículos
        model.addAttribute("vehiculos", vehiculoServicio.obtenerTodosLosVehiculos());
        return "redirect:/pantalla-vendedor"; // Volver a la página del vendedor con la lista actualizada
    }



}
