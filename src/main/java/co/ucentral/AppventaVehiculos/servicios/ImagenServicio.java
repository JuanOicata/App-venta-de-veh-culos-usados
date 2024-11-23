package co.ucentral.AppventaVehiculos.servicios;

import co.ucentral.AppventaVehiculos.persistencia.entidades.Imagen;
import co.ucentral.AppventaVehiculos.persistencia.repositorio.ImagenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagenServicio {

    @Autowired
    private ImagenRepositorio imagenRepositorio;

    public void guardarImagen(Imagen imagen) {
        imagenRepositorio.save(imagen);
    }
}
