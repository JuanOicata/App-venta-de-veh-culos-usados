package co.ucentral.AppventaVehiculos.persistencia.repositorio;

import co.ucentral.AppventaVehiculos.persistencia.entidades.Usuario;
import co.ucentral.AppventaVehiculos.persistencia.entidades.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehiculoRepositorio extends CrudRepository<Vehiculo, Long> {
    List<Vehiculo> findByUsuario(Usuario usuario);
    List<Vehiculo> findByDisponibleTrue();
    @Query("SELECT v FROM Vehiculo v WHERE (:marca IS NULL OR v.marca LIKE %:marca%) AND (:modelo IS NULL OR v.modelo LIKE %:modelo%)")
    List<Vehiculo> buscarPorMarcaYModelo(@Param("marca") String marca, @Param("modelo") String modelo);

}
