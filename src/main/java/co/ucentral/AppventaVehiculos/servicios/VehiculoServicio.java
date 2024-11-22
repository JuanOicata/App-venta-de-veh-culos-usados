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
        vehiculoRepositorio.save(vehiculo); // Esto actualiza el vehículo si el ID ya existe
    }

    public List<Vehiculo> obtenerTodosLosVehiculos() {
        List<Vehiculo> vehiculos = (List<Vehiculo>) vehiculoRepositorio.findAll();
        // Forzar la carga de usuarios si es Lazy
        vehiculos.forEach(vehiculo -> {
            if (vehiculo.getUsuario() != null) {
                vehiculo.getUsuario().getNombre();
            }
        });
        return vehiculos;
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
    // Agregar un método para obtener vehículos disponibles para compradores
    public List<Vehiculo> obtenerVehiculosDisponibles() {
        // Aquí puedes filtrar por estado (ejemplo: vehículos no vendidos o disponibles)
        return (List<Vehiculo>) vehiculoRepositorio.findAll(); // Reemplazar con lógica específica si aplicable
    }

    public boolean marcarComoVendido(Long id) {
        Vehiculo vehiculo = vehiculoRepositorio.findById(id).orElse(null);
        if (vehiculo != null) {
            vehiculo.setDisponible(false); // Cambiar el estado
            vehiculoRepositorio.save(vehiculo);
            return true;
        }
        return false;
    }
    public boolean validarPropiedadVehiculo(Long vehiculoId, Usuario usuario) {
        Vehiculo vehiculo = vehiculoRepositorio.findById(vehiculoId).orElse(null);
        return vehiculo != null && vehiculo.getUsuario().getUsuario().equals(usuario.getUsuario());
    }

    public List<Vehiculo> buscarVehiculosPorMarcaYModelo(String marca, String modelo) {
        return vehiculoRepositorio.buscarPorMarcaYModelo(marca, modelo);
    }
}
