package co.ucentral.AppventaVehiculos.persistencia.repositorio;

import co.ucentral.AppventaVehiculos.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    Usuario findByUsuarioAndContrasena(String usuario, String contrasena);

}
