package abp.project.Examen.service;

import abp.project.Examen.dao.EntitatRepositori;
import abp.project.Examen.dao.PaqueteRepositori;
import abp.project.Examen.dao.UsuariRepositori;
import abp.project.Examen.model.Entidad;
import abp.project.Examen.model.Paquete;
import abp.project.Examen.model.Usuario;
import lombok.SneakyThrows;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuariRepositori usuariRepositori;
    PaqueteRepositori paqueteRepositori;


    public ResponseEntity getAllUsers() {
        List<Usuario> users = this.usuariRepositori.AllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(users);

    }

    // Añadir método para obtener usuarios por rol
    public ResponseEntity getUsersByRole(String rol) {
        Usuario users = this.usuariRepositori.findByRole(rol);
        if (users == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }
    public ResponseEntity postUser(Usuario user) {

        Usuario userDao = this.usuariRepositori.findUserByName(user.getNombre());
        if (userDao == null) {
            this.usuariRepositori.saveAndFlush(user);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);

        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    // Nuevo método para encontrar usuario por correo electrónico
    public ResponseEntity postUserByEmail(String email) {
        Usuario user = this.usuariRepositori.findUserByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // Nuevo método para obtener entidades del usuario por email
    public ResponseEntity getEntidadesByEmail(String email) {
        List<Entidad> entidades = this.usuariRepositori.findEntidadesByEmail(email);
        if (entidades.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entidades);
    }

    // Nuevo método para actualizar o crear una entidad
//    public ResponseEntity updateOrCreateEntidad(String email, Entidad entidad) {
//        Usuario usuario = this.usuariRepositori.findUserByEmail(email);
//        if (usuario == null) {
//            return ResponseEntity.notFound().build();
//        }
//        entidad.set(usuario);
//        Entidad entidadExistente = this.entitatRepositori.findById(entidad.getId()).orElse(null);
//        if (entidadExistente != null) {
//            entidadExistente.setNombre(entidad.getNombre());
//            // Actualiza otros campos según sea necesario
//            this.entitatRepositori.save(entidadExistente);
//            return ResponseEntity.ok(entidadExistente);
//        } else {
//            this.entitatRepositori.save(entidad);
//            return ResponseEntity.status(HttpStatus.CREATED).body(entidad);
//        }
//    }

    public ResponseEntity asignarPaqueteAUsuario(String email, int idPaquete) {
        Usuario usuario = usuariRepositori.findUserByEmail(email);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        Paquete paquete = paqueteRepositori.findById(idPaquete);
        if (paquete == null) {
            return ResponseEntity.notFound().build();
        }

        // Asignar el paquete al usuario
        usuario.getPaquetes().add(paquete);
        usuariRepositori.save(usuario);

        return ResponseEntity.ok("Paquete asignado correctamente al usuario");
    }
}