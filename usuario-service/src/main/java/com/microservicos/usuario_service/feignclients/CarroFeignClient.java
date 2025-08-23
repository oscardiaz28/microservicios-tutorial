package com.microservicos.usuario_service.feignclients;

import com.microservicos.usuario_service.dto.Carro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// anotacion que indica que esta clase es un cliente http para otro microservicio
// name => nombre logico del microservicio ( si se usa Eureka no se necesita la url, basta con el nombre)
// url => direccion del servicio
@FeignClient(name = "carro-service", url = "http://localhost:8082")
public interface CarroFeignClient {

    //metodo que representa la llamada http al microservicio
    // el json que devuelve el microservicio de carros se convierte automaticamente en un List<Carro>
    @GetMapping("/carros/usuario/{id}")
    public List<Carro> getCarrosPorUsuario(@PathVariable(name = "id") Integer id);



}
