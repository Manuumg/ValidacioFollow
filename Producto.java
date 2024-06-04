package abp.project.Examen.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String descripcion;

    @Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "paquete_id")
    private Paquete paquete;




}