package com.microservicos.usuario_service.controller;

import com.microservicos.usuario_service.dto.Carro;
import com.microservicos.usuario_service.dto.Moto;
import com.microservicos.usuario_service.model.Usuario;
import com.microservicos.usuario_service.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<?> listarUsuarios(){
        List<Usuario> usuarios = usuarioService.getAll();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable(name = "id") Integer id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if(usuario == null){
            return ResponseEntity.badRequest().body(Map.of("message", "El usuario no existe"));
        }
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping
    public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario){
        Usuario newUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok().body(newUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer id){
        boolean deleted = usuarioService.deleteUsuario(id);
        if(!deleted) return ResponseEntity.badRequest()
                .body(Map.of("message", "El usuario no existe"));
        return ResponseEntity.ok().body(Map.of("message", "Usuario eliminado correctamente"));
    }

    @GetMapping("/{id}/carros")
    public ResponseEntity<?> listarCarros(@PathVariable Integer id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if(usuario == null) return ResponseEntity.badRequest()
                .body(Map.of("message", "El usuario no existe"));

        //List<Carro> carros = usuarioService.getCarrosWithRestTemplate(id);
        List<Carro> carros = usuarioService.getCarrosWithFeign(id);

        Map<String, Object> resp = new HashMap<>();
        resp.put("usuario", usuario);
        resp.put("carros", carros);

        return ResponseEntity.ok().body(resp);

    }

    @GetMapping("/{id}/motos")
    public ResponseEntity<?> listarMotos(@PathVariable Integer id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if(usuario == null) return ResponseEntity.badRequest()
                .body(Map.of("message", "El usuario no existe"));

        List<Moto> motos = usuarioService.getMotos(id);
        Map<String, Object> resp = new HashMap<>();
        resp.put("usuario", usuario);
        resp.put("motos", motos);
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/{id}/vehiculos")
    public ResponseEntity<?> listarVehiculos(@PathVariable Integer id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if(usuario == null) return ResponseEntity.badRequest()
                .body(Map.of("message", "El usuario no existe"));

        Map<String, Object> resp = new HashMap<>();
        resp.put("usuario", usuario);
        resp.put("carros", usuarioService.getCarrosWithFeign(id));
        //resp.put("motos", usuarioService.getMotos(id));

        return ResponseEntity.ok().body(resp);
    }

}















