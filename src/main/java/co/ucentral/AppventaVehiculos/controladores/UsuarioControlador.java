package co.ucentral.AppventaVehiculos.controladores;

import co.ucentral.AppventaVehiculos.perisitencia.entidades.Usuario;
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

    // Método para mostrar el formulario de registro
    @GetMapping("/registro")
    public String mostrarFormularioDeRegistro(Model model) {
        Usuario usuario = new Usuario();

        model.addAttribute("elusuario", usuario);
        return "registro";  // Nombre del archivo HTML (sin extensión)
    }

    // Método para manejar el envío del formulario y registrar el usuario
    @PostMapping("/almacenar")
    public String registrarUsuario(Usuario usuario, Model model) {
        usuarioServicio.registrarUsuario(usuario);
        model.addAttribute("mensaje", "Usuario registrado exitosamente");
        return "bienvenida";  // Redirige a una página de bienvenida después de registrar


    }


}
