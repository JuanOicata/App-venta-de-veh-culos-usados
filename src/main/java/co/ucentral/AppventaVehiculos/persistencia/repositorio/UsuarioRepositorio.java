package co.ucentral.AppventaVehiculos.persistencia.repositorio;

import co.ucentral.AppventaVehiculos.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*@Query ("SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.contrasena = :contrasena");
Usuario findUsuarioByUsuarioAndContrasena(@Param("usuario") String usuario, @Param("contrasena") String contrasena);*/

public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    Usuario findByUsuarioAndContrasena(String usuario, String contrasena);

}
