package com.microservicos.carro_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarroResponse {
    private Integer id;
    private String marca;
    private String modelo;
    private UsuarioDto usuario;

    public CarroResponse(Carro carro, UsuarioDto usuario){
        this.id = carro.getId();
        this.marca =carro.getMarca();
        this.modelo = carro.getModelo();
        this.usuario = usuario;
    }

}
