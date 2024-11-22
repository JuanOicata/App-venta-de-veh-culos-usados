package co.ucentral.AppventaVehiculos.controladores;

import co.ucentral.AppventaVehiculos.persistencia.entidades.Usuario;
import co.ucentral.AppventaVehiculos.persistencia.entidades.Vehiculo;
import co.ucentral.AppventaVehiculos.servicios.VehiculoServicio;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
public class VehiculoControlador {

    @Autowired
    VehiculoServicio vehiculoServicio;

    /////////// PANTALLA VENDEDOR /////////////////
    // Maneja el envío del formulario y guarda el vehículo
    @PostMapping("/almacenar-vehiculo")
    public String registrarVehiculo(@ModelAttribute("elvehiculo") Vehiculo vehiculo, Model model, HttpServletRequest request) {
        try {
            // Obtener el usuario logueado desde la sesión
            Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");

            if (usuarioLogueado != null) {
                vehiculo.setUsuario(usuarioLogueado);  // Asociar el vehículo al usuario logueado
                vehiculoServicio.registrarVehiculo(vehiculo);
                model.addAttribute("mensaje", "Vehículo registrado exitosamente");
                model.addAttribute("elvehiculo", new Vehiculo());

                // Mostrar solo los vehículos del vendedor logueado
                model.addAttribute("vehiculos", vehiculoServicio.obtenerVehiculosPorUsuario(usuarioLogueado));
            } else {
                model.addAttribute("mensaje", "Debes iniciar sesión para registrar un vehículo.");
                return "pantallaVendedor";
            }

            return "pantallaVendedor";
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al registrar el vehículo: " + e.getMessage());
            return "pantallaVendedor";
        }
    }




    // Método para mostrar la página inicial del vendedor con la lista de vehículos
    @GetMapping("/pantalla-vendedor")
    public String mostrarFormularioDeVendedor(Model model, HttpServletRequest request) {
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");

        if (usuarioLogueado != null) {
            // Obtener vehículos solo del usuario logueado
            List<Vehiculo> vehiculos = vehiculoServicio.obtenerVehiculosPorUsuario(usuarioLogueado);
            model.addAttribute("vehiculos", vehiculos);
            if (vehiculos.isEmpty()) {
                model.addAttribute("mensaje", "Aún no has registrado ningún vehículo.");
            }
        } else {
            model.addAttribute("mensaje", "Debes iniciar sesión para acceder a esta página.");
            return "redirect:/inicio-sesion";
        }

        model.addAttribute("elvehiculo", new Vehiculo());
        return "pantallaVendedor";
    }


    @GetMapping("/editar-vehiculo/{id}")
    public String cargarVehiculoParaEdicion(@PathVariable Long id, Model model, HttpServletRequest request) {
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");

        if (usuarioLogueado != null && vehiculoServicio.validarPropiedadVehiculo(id, usuarioLogueado)) {
            Vehiculo vehiculo = vehiculoServicio.obtenerVehiculoPorId(id);
            model.addAttribute("elvehiculo", vehiculo);
            return "pantallaVendedor";
        }

        model.addAttribute("mensaje", "No tienes permiso para editar este vehículo.");
        return "redirect:/pantalla-vendedor";
    }

    @PostMapping("/editar-vehiculo")
    public String editarVehiculo(@ModelAttribute("elvehiculo") Vehiculo vehiculo, HttpServletRequest request, Model model) {
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");

        if (usuarioLogueado == null) {
            model.addAttribute("mensaje", "Debes iniciar sesión para realizar esta acción.");
            return "redirect:/inicio-sesion";
        }

        // Obtener el vehículo actual de la base de datos
        Vehiculo vehiculoExistente = vehiculoServicio.obtenerVehiculoPorId(vehiculo.getId());

        if (vehiculoExistente == null) {
            model.addAttribute("mensaje", "El vehículo no existe.");
            return "redirect:/pantalla-vendedor";
        }

        // Validar que el vehículo pertenece al usuario logueado
        if (!vehiculoExistente.getUsuario().getUsuario().equals(usuarioLogueado.getUsuario())) {
            model.addAttribute("mensaje", "No puedes editar un vehículo que no te pertenece.");
            return "redirect:/pantalla-vendedor";
        }

        // Conservar el usuario asociado al vehículo
        vehiculo.setUsuario(vehiculoExistente.getUsuario());

        // Actualizar el vehículo con los nuevos datos
        vehiculoServicio.actualizarVehiculo(vehiculo);

        // Mostrar solo los vehículos del usuario logueado
        model.addAttribute("vehiculos", vehiculoServicio.obtenerVehiculosPorUsuario(usuarioLogueado));
        model.addAttribute("mensaje", "Vehículo actualizado exitosamente.");

        return "pantallaVendedor";
    }
    // Eliminar un vehículo
    @GetMapping("/eliminar-vehiculo/{id}")
    public String eliminarVehiculo(@PathVariable Long id, Model model, HttpServletRequest request) {
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");

        if (usuarioLogueado != null && vehiculoServicio.validarPropiedadVehiculo(id, usuarioLogueado)) {
            boolean eliminado = vehiculoServicio.borrarVehiculo(id);
            model.addAttribute("mensaje", eliminado ? "Vehículo eliminado exitosamente" : "Error al eliminar el vehículo");
        } else {
            model.addAttribute("mensaje", "No tienes permiso para eliminar este vehículo.");
        }

        return "redirect:/pantalla-vendedor";
    }



    /////////// PANTALLA VENDEDOR /////////////////
    @GetMapping("/cerrar-sesion")
    public String cerrarSesion(HttpServletRequest request) {
        // Invalidar la sesión del usuario
        request.getSession().invalidate();

        // Redirigir a la página de inicio de sesión
        return "redirect:/inicio-sesion";
    }
    @GetMapping("/pantalla-comprador")
    public String mostrarFormularioDeComprador(Model model) {
        // Obtener todos los vehículos y pasarlos al modelo
        List<Vehiculo> vehiculos = vehiculoServicio.obtenerTodosLosVehiculos();
        model.addAttribute("vehiculos", vehiculos);

        return "pantallaComprador";
    }

    /////////////VENTANA COMPRADOR/////////////////

    @PostMapping("/comprar-vehiculo")
    public String comprarVehiculo(@RequestParam("idVehiculo") Long idVehiculo, Model model, HttpServletRequest request) {
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");

        if (usuarioLogueado == null) {
            model.addAttribute("mensaje", "Debes iniciar sesión para realizar una compra.");
            return "redirect:/inicio-sesion";
        }

        boolean eliminado = vehiculoServicio.borrarVehiculo(idVehiculo);

        if (eliminado) {
            model.addAttribute("mensaje", "Compra realizada con éxito ☺.");
        } else {
            model.addAttribute("mensaje", "Error al realizar la compra. Inténtalo de nuevo.");
        }

        // Recargar los vehículos disponibles
        List<Vehiculo> vehiculos = vehiculoServicio.obtenerTodosLosVehiculos();
        model.addAttribute("vehiculos", vehiculos);

        return "pantallaComprador";
    }

    @GetMapping("/buscar-vehiculos")
    public String buscarVehiculos(@RequestParam(value = "marca", required = false) String marca,
                                  @RequestParam(value = "modelo", required = false) String modelo,
                                  Model model) {
        List<Vehiculo> vehiculos = vehiculoServicio.buscarVehiculosPorMarcaYModelo(marca, modelo);
        model.addAttribute("vehiculos", vehiculos);
        return "pantallaComprador";
    }


}
