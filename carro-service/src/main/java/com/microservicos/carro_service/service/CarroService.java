package com.microservicos.carro_service.service;

import com.microservicos.carro_service.model.Carro;
import com.microservicos.carro_service.model.CarroResponse;
import com.microservicos.carro_service.model.UsuarioDto;
import com.microservicos.carro_service.repository.CarroRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CarroService {

    private final CarroRepository carroRepository;
    private final RestTemplate restTemplate;

    public CarroService(CarroRepository carroRepository, RestTemplate restTemplate) {
        this.carroRepository = carroRepository;
        this.restTemplate = restTemplate;
    }

    public List<Carro> getAll(){
        return carroRepository.findAll();
    }

    public Carro getCarroById(Integer id){
        return carroRepository.findById(id).orElse(null);
    }

    public Carro saveCarro(Carro carro){
        return carroRepository.save(carro);
    }

    public boolean deleteCarro(Integer id){
        Carro carro = carroRepository.findById(id).orElse(null);
        if(carro == null) return false;
        carroRepository.delete(carro);
        return true;
    }

    public List<Carro> belongToUser(Integer id){
        return carroRepository.findByUsuarioId(id);
    }

    public CarroResponse getCarroWithUsuario(Integer carroId){
        Carro carro = carroRepository.findById(carroId).orElse(null);
        if(carro == null) return null;
        String url = "http://localhost:8081/usuarios/" + carro.getUsuarioId();
        UsuarioDto usuario = restTemplate.getForObject(url, UsuarioDto.class);

        return new CarroResponse(carro, usuario);
    }

}
