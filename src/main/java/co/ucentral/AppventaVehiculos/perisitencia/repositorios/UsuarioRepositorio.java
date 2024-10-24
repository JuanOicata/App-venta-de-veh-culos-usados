package co.ucentral.AppventaVehiculos.perisitencia.repositorios;

import co.ucentral.AppventaVehiculos.perisitencia.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

}
