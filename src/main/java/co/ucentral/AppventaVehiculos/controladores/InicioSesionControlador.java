package co.ucentral.AppventaVehiculos.controladores;

import co.ucentral.AppventaVehiculos.persistencia.entidades.Usuario;
import co.ucentral.AppventaVehiculos.servicios.InicioSesionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InicioSesionControlador {

    @Autowired
    private InicioSesionServicio inicioSesionServicio;

    @GetMapping("/iniciosesion")
    public String mostrarFormularioDeInicioSesion() {
        return "inicioSesion";
    }

    @PostMapping("/inicio-sesion")
    public String iniciarSesion(Usuario usuario, Model model) {
        boolean esValido = inicioSesionServicio.validarCredenciales(usuario.getUsuario(), usuario.getContrasena());
        if (esValido) {
            model.addAttribute("mensaje", "Inicio de sesión exitoso");
            return "bienvenida";  // Redirige a la página principal después de iniciar sesión
        } else {
            model.addAttribute("mensaje", "Credenciales incorrectas");
            return "inicioSesion";  // Mantiene al usuario en la página de inicio de sesión
        }
    }
}
