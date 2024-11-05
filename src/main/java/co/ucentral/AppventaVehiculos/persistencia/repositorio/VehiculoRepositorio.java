package co.ucentral.AppventaVehiculos.persistencia.repositorio;

import co.ucentral.AppventaVehiculos.persistencia.entidades.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepositorio extends JpaRepository<Vehiculo, String> {

}
