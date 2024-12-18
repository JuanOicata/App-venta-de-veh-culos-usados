package co.ucentral.AppventaVehiculos.persistencia.entidades;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table (name = "imagenes")
public class Imagen {
    @Id
    private int codigo;
    private String imagen64;
    private String descripcion;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "veh_id")
    private Vehiculo vehiculo;

}
