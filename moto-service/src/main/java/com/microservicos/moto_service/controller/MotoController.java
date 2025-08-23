package com.microservicos.moto_service.controller;

import com.microservicos.moto_service.model.Moto;
import com.microservicos.moto_service.service.MotoService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/motos")
public class MotoController {

    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping
    public ResponseEntity<?> listarMotos(){
        List<Moto> motos = motoService.getAll();
        return ResponseEntity.ok().body(motos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerMoto(@PathVariable Integer id){
        Moto moto = motoService.getMotoById(id);
        if(moto == null) return ResponseEntity.badRequest()
                .body(Map.of("message", "La moto no existe"));
        return ResponseEntity.ok().body(moto);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> obtenerMotosDeUsuario(@PathVariable Integer id){
        List<Moto> motos = motoService.belongToUser(id);
        return ResponseEntity.ok().body(motos);
    }

    @PostMapping
    public ResponseEntity<?> crearMoto(@RequestBody Moto moto){
        Moto saved = motoService.saveMoto(moto);
        return ResponseEntity.ok().body(moto);
    }

}















