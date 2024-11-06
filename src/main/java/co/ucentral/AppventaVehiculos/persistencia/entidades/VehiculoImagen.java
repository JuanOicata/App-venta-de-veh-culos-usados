package co.ucentral.AppventaVehiculos.persistencia.entidades;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table (name = "vehiculos_imagenes")
public class VehiculoImagen {
    @Id
    private int codigo;

}
