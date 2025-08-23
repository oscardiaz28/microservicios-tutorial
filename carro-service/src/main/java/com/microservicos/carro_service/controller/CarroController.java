package com.microservicos.carro_service.controller;

import com.microservicos.carro_service.model.Carro;
import com.microservicos.carro_service.model.CarroResponse;
import com.microservicos.carro_service.service.CarroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carros")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping
    public ResponseEntity<?> listarCarros(){
        List<Carro> carros = carroService.getAll();
        return ResponseEntity.ok().body(carros);
    }

    @PostMapping
    public ResponseEntity<?> guardarCarro(@RequestBody Carro carro){
        Carro saved = carroService.saveCarro(carro);
        return ResponseEntity.ok().body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCarro(@PathVariable(name = "id") Integer id ){
        // Carro carro = carroService.getCarroById(id);

        CarroResponse carro = carroService.getCarroWithUsuario(id);

        if(carro == null) return ResponseEntity.badRequest()
                .body(Map.of("message", "El carro no existe"));

        return ResponseEntity.ok().body(carro);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> obtenerCarrosDeUsuario(@PathVariable Integer id){
        List<Carro> carros = carroService.belongToUser(id);
        return ResponseEntity.ok().body(carros);
    }

}
