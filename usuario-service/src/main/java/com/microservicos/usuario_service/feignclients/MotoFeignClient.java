package com.microservicos.usuario_service.feignclients;

import com.microservicos.usuario_service.dto.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "moto-service", url = "http://localhost:8083")
public interface MotoFeignClient{

    @GetMapping("/motos/usuario/{id}")
    public List<Moto> getMotosPorUsuarioId(@PathVariable Integer id);

}
