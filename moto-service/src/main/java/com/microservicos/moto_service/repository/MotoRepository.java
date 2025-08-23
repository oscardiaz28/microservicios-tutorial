package com.microservicos.moto_service.repository;

import com.microservicos.moto_service.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Integer> {
    List<Moto> findByUsuarioId(Integer id);
}
