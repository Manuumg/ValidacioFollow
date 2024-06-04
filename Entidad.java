package abp.project.Examen.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Entidad")
public class Entidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String descripcio;
    @Column
    private String nombre;

    //esta relacionando la tabla Entidad con la tabla Usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario") //esta es la columna que se llama id_usuario que estara conectado a Usuario
    private Usuario usuario; //aqui lo conectamos con la clase Usuario

    @OneToMany(mappedBy = "entidad")
    private List<Paquete> paquetes;

}
