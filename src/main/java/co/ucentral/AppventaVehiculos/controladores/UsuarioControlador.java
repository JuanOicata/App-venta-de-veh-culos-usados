package co.ucentral.AppventaVehiculos.controladores;

import co.ucentral.AppventaVehiculos.persistencia.entidades.Usuario;
import co.ucentral.AppventaVehiculos.servicios.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String registrarUsuario(Usuario usuario, Model model) {
        usuarioServicio.registrarUsuario(usuario);
        model.addAttribute("mensaje", "Usuario registrado exitosamente");
        return "bienvenida";


    }

    @GetMapping("/iniciosesion")
    public String mostrarFormularioDeInicioSesion() {
        return "inicioSesion";
    }

    @PostMapping("/inicio-sesion")
    public String iniciarSesion(Usuario usuario, Model model) {
        boolean esValido = usuarioServicio.validarCredenciales(usuario.getUsuario(), usuario.getContrasena());
        if (esValido) {
            model.addAttribute("mensaje", "Inicio de sesión exitoso");
            return "bienvenida";  // Redirige a la página principal después de iniciar sesión
        } else {
            model.addAttribute("mensaje", "Credenciales incorrectas");
            return "inicioSesion";  // Mantiene al usuario en la página de inicio de sesión
        }
    }

}
