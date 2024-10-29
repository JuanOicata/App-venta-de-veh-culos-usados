package co.ucentral.AppventaVehiculos.servicios;

import co.ucentral.AppventaVehiculos.persistencia.entidades.Usuario;
import co.ucentral.AppventaVehiculos.persistencia.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InicioSesionServicio {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public boolean validarCredenciales(String usuario, String contrasena) {
        Usuario user = usuarioRepositorio.findByUsuarioAndContrasena(usuario, contrasena);
        return user != null;
    }
}
