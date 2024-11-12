package co.ucentral.AppventaVehiculos.servicios;

import co.ucentral.AppventaVehiculos.persistencia.entidades.Vehiculo;
import co.ucentral.AppventaVehiculos.persistencia.repositorio.VehiculoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class VehiculoServicio {

    @Autowired
    VehiculoRepositorio vehiculoRepositorio;

    public void registrarVehiculo(Vehiculo vehiculo) {
        vehiculoRepositorio.save(vehiculo);
    }

    public List<Vehiculo> obtenerTodosLosVehiculos() {
        return (List<Vehiculo>) vehiculoRepositorio.findAll();
    }

    // Obtener todos los vehículos
    public List<Vehiculo> obtenerTodos() {
        return (List<Vehiculo>) vehiculoRepositorio.findAll();
    }

    // Eliminar un vehículo
    public boolean borrarVehiculo(Vehiculo vehiculo) {
        try {
            vehiculoRepositorio.delete(vehiculo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
