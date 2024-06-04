package abp.project.Examen.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Paquete")
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String descripcio;
    @Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_entidad")
    private Entidad entidad;

    @OneToMany(mappedBy = "paquete")
    private List<Producto> productos;
}
