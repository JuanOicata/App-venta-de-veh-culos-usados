package co.ucentral.AppventaVehiculos.controladores;

import co.ucentral.AppventaVehiculos.persistencia.entidades.Usuario;
import co.ucentral.AppventaVehiculos.servicios.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class UsuarioControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    // Mostrar la pestaña registro
    @GetMapping("/registro")
    public String mostrarFormularioDeRegistro(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("elusuario", usuario);
        return "registro";
    }

    // Registro de usuario y guardar en la base de datos
    @PostMapping("/almacenar")
    public String registrarUsuario(@ModelAttribute ("elusuario") Usuario usuario, Model model) {
        usuarioServicio.registrarUsuario(usuario);
        model.addAttribute("mensaje", "Usuario registrado exitosamente");

        model.addAttribute("elusuario", usuario);
        return "registro"; // vuelve a la pestaña regsitro
    }
    @GetMapping("/inicio-sesion")
    public String mostrarFormularioDeInicioSesion() {
        return "inicioSesion";
    }

    @PostMapping("/inicio-sesion")
    public String iniciarSesion(Usuario usuario, Model model) {
        boolean esValido = usuarioServicio.validarCredenciales(usuario.getUsuario(), usuario.getContrasena());
        if (esValido) {
            model.addAttribute("mensaje", "Inicio de sesión exitoso");
            return "registro-vehiculo";  // manda para la pagina principal
        } else {
            model.addAttribute("mensaje", "Credenciales incorrectas");
            return "inicioSesion";  // Se queda en la pagina de inicio de sesion en caso de error
        }
    }
    @GetMapping("/registro-vehiculo")
    public String mostrarFormularioDeRegistroVehiculo() {
        return "registrovehiculo";
    }

}
