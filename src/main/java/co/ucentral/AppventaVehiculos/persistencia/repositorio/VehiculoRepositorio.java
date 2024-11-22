package co.ucentral.AppventaVehiculos.persistencia.repositorio;

import co.ucentral.AppventaVehiculos.persistencia.entidades.Usuario;
import co.ucentral.AppventaVehiculos.persistencia.entidades.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehiculoRepositorio extends CrudRepository<Vehiculo, Long> {
    List<Vehiculo> findByUsuario(Usuario usuario);
    List<Vehiculo> findByDisponibleTrue(); // Supongamos que tienes un campo `disponible` en Vehiculo
}
