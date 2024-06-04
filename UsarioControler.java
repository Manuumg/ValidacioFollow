package abp.project.Examen.api;

import abp.project.Examen.model.Entidad;
import abp.project.Examen.model.Usuario;
import abp.project.Examen.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class UsarioControler {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/users")
    public ResponseEntity AllUsers(){
        return this.usuarioService.getAllUsers();
    }

    // Nuevo endpoint para obtener usuarios por rol
    @GetMapping("/user")
    public ResponseEntity getUsersByRole(@RequestParam String rol) {
        return this.usuarioService.getUsersByRole(rol);
    }

    @PostMapping("/user/registre")
    @ResponseBody
    public ResponseEntity postUser(@RequestBody Usuario user) {
        return this.usuarioService.postUser(user);

    }

    // Nuevo endpoint para obtener usuario por correo electrónico
    @PostMapping("/usuari")
    @ResponseBody
    public ResponseEntity postUserByEmail(@RequestBody Usuario usuario) {
        return this.usuarioService.postUserByEmail(usuario.getEmail());
    }

    // Nuevo endpoint para obtener entidades del usuario por email
    @GetMapping("/usuari/{email}/entidad")
    public ResponseEntity getEntidadesByEmail(@PathVariable String email) {
        return this.usuarioService.getEntidadesByEmail(email);
    }

    // Nuevo endpoint para actualizar o crear una entidad
//    @PostMapping("/usuario/{email}/entidad")
//    public ResponseEntity updateOrCreateEntidad(@PathVariable String email, @RequestBody Entidad entidad) {
//        return this.usuarioService.updateOrCreateEntidad(email, entidad);
//    }


    @PutMapping("/usuari/{email}/paquete/{idPaquete}")
    public ResponseEntity asignarPaqueteAUsuario(@PathVariable String email, @PathVariable int idPaquete) {
        ResponseEntity response = usuarioService.asignarPaqueteAUsuario(email, idPaquete);
        return response;
    }
}
