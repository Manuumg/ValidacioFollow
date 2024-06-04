package abp.project.Examen.dao;

import abp.project.Examen.model.Entidad;
import abp.project.Examen.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UsuariRepositori extends JpaRepository<Usuario, Integer> {
    @Query(value = "SELECT u FROM Usuario u ORDER BY u.id desc ")
    List<Usuario> AllUsers();


    // Añadir método para obtener usuarios por rol
    @Query(value = "SELECT u FROM Usuario u WHERE u.rol = :rol")
    Usuario findByRole(@Param("rol")String rol);

    @Query(value = "SELECT u FROM Usuario u WHERE u.nombre = :nombre ")
    Usuario findUserByName(@Param("nombre") String nombre);

    // Nuevo método para encontrar usuario por correo electrónico
    @Query(value = "SELECT u FROM Usuario u WHERE u.email = :email")
    Usuario findUserByEmail(@Param("email") String email);

    @Query("SELECT e FROM Entidad e WHERE e.usuario.email = :email")
    List<Entidad> findEntidadesByEmail(@Param("email") String email);
}