package abp.project.Examen.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="Usuario")
public class Usuario {

    @Id
    private String email;

    @Column
    private String nombre;

    @Column
    private String rol;

    @OneToMany(mappedBy = "usuario") //especifica que la entidad Entidad tiene un campo llamado usuario que es el propietario de la relación
    private List<Entidad> entidades; //define una lista de objetos Entidad asociados con un objeto Usuario

    @OneToMany(mappedBy = "usuario")
    private List<Paquete> paquetes;


}
