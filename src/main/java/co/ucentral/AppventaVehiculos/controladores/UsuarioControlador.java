package co.ucentral.AppventaVehiculos.controladores;


import co.ucentral.AppventaVehiculos.perisitencia.entidades.Usuario;
import co.ucentral.AppventaVehiculos.servicios.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
@AllArgsConstructor
@Controller
public class UsuarioControlador {

    UsuarioServicio usuarioServicio;

    public List<Usuario> obtenerTodos() {
        return usuarioServicio.obtenerTodos();
    }
}
