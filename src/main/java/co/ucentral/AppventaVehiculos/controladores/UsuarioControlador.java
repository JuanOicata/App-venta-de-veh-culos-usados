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


}
