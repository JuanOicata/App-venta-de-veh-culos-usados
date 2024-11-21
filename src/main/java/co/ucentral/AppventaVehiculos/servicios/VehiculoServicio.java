package co.ucentral.AppventaVehiculos.servicios;

import co.ucentral.AppventaVehiculos.persistencia.entidades.Usuario;
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

    public Vehiculo obtenerVehiculoPorId(Long id) {
        return vehiculoRepositorio.findById(id).orElse(null);
    }

    public void actualizarVehiculo(Vehiculo vehiculo) {
        vehiculoRepositorio.save(vehiculo); // `save` actualiza si el objeto tiene un ID existente
    }

    public List<Vehiculo> obtenerTodosLosVehiculos() {
        return (List<Vehiculo>) vehiculoRepositorio.findAll();
    }

    // Obtener todos los vehículos
    public List<Vehiculo> obtenerTodos() {
        return (List<Vehiculo>) vehiculoRepositorio.findAll();
    }

    // Eliminar un vehículo
    public boolean borrarVehiculo(Long id) {
        try {
            // Verificar si el vehículo existe antes de intentar eliminarlo
            Vehiculo vehiculo = vehiculoRepositorio.findById(id).orElse(null);
            if (vehiculo == null) {
                return false; // El vehículo no existe
            }

            vehiculoRepositorio.delete(vehiculo);
            return true; // Vehículo eliminado exitosamente
        } catch (Exception e) {
            // Registrar el error para diagnóstico si es necesario
            e.printStackTrace();
            return false; // Error al eliminar el vehículo
        }
    }
    public List<Vehiculo> obtenerVehiculosPorUsuario(Usuario usuario) {
        return vehiculoRepositorio.findByUsuario(usuario);
    }
}
