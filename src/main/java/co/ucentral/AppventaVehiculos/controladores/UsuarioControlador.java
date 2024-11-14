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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String registrarUsuario(@ModelAttribute("elusuario") Usuario usuario, Model model) {
        usuarioServicio.registrarUsuario(usuario);
        model.addAttribute("mensaje", "Usuario registrado exitosamente");

        model.addAttribute("elusuario", usuario);
        return "registro"; // vuelve a la pestaña regsitro
    }

    @GetMapping("/inicio-sesion")
    public String mostrarFormularioDeInicioSesion() { return "inicioSesion";
    }

    @PostMapping("/inicio-sesion")
    public String iniciarSesion(@RequestParam String usuario, @RequestParam String contrasena, Model model) {
        // Llamar al servicio para autenticar al usuario
        Usuario usuarioAutenticado = usuarioServicio.validarUsuario(usuario, contrasena);

        // Verificar si el usuario fue encontrado y autenticado correctamente
        if (usuarioAutenticado != null) {
            // Redirigir según el rol del usuario
            switch (usuarioAutenticado.getRol()) {
                case "vendedor":
                    return "redirect:/pantalla-vendedor";  // Redirige a la página del vendedor
                case "comprador":
                    return "redirect:/pantalla-comprador";  // Redirige a la página del comprador
                default:
                    model.addAttribute("error", "Rol no reconocido");
                    return "iniciosesion";  // Redirige al login si el rol no es reconocido
            }
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "iniciosesion";  // Redirige al login si la autenticación falla
        }
    }

}
