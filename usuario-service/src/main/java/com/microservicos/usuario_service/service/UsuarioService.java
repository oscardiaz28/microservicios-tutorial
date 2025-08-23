package com.microservicos.usuario_service.service;

import com.microservicos.usuario_service.dto.Carro;
import com.microservicos.usuario_service.dto.Moto;
import com.microservicos.usuario_service.feignclients.CarroFeignClient;
import com.microservicos.usuario_service.feignclients.MotoFeignClient;
import com.microservicos.usuario_service.model.Usuario;
import com.microservicos.usuario_service.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RestTemplate restTemplate;
    private final CarroFeignClient carroClient;
    private final MotoFeignClient motoClient;

    public UsuarioService(UsuarioRepository usuarioRepository, RestTemplate restTemplate, CarroFeignClient carroClient, MotoFeignClient motoClient) {
        this.usuarioRepository = usuarioRepository;
        this.restTemplate = restTemplate;
        this.carroClient = carroClient;
        this.motoClient = motoClient;
    }

    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Integer id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public boolean deleteUsuario(Integer id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null){
           return false;
        }
        usuarioRepository.delete(usuario);
        return true;
    }

    // metodo que consume APIS externas haciendo uso de Rest Template
    public List<Carro> getCarrosWithRestTemplate(Integer usuarioId){
        String url = "http://localhost:8082/carros/usuario/" + usuarioId;
        // Carro[] => sintaxis para indicar un arreglo de objetos de tipo Carro
       // Carro[].class => parametro para indicar a rest template a que tipo de dato convertir la resp
        Carro[] carros = restTemplate.getForObject(url, Carro[].class);
       if (carros == null) {
           return Collections.emptyList();
       }
       return Arrays.asList(carros);
   }

   // se hace uso de CarroFeignClient como si fuera una clase normal, se llama al metodo definido
   // en la interfaz y spring genera la llamada http
   public List<Carro> getCarrosWithFeign(Integer usuarioId){
        return carroClient.getCarrosPorUsuario(usuarioId);
   }

   public List<Moto> getMotosRestTemplate(Integer usuarioId){
        String url = "http://localhost:8083/motos/usuario/" + usuarioId;
       return restTemplate.getForObject(url, List.class);
   }

   public List<Moto> getMotos(Integer usuarioId){
        return motoClient.getMotosPorUsuarioId(usuarioId);
   }

}
