package com.microservicos.carro_service.repository;

import com.microservicos.carro_service.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {
    List<Carro> findByUsuarioId(Integer usuarioId);
}
