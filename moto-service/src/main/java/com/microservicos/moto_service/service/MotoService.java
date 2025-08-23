package com.microservicos.moto_service.service;

import com.microservicos.moto_service.model.Moto;
import com.microservicos.moto_service.repository.MotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {
    private final MotoRepository motoRepository;

    public MotoService(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    public List<Moto> getAll(){
        return motoRepository.findAll();
    }

    public Moto getMotoById(Integer id){
        return motoRepository.findById(id).orElse(null);
    }

    public Moto saveMoto(Moto moto){
        return motoRepository.save(moto);
    }

    public List<Moto> belongToUser(Integer id){
        return motoRepository.findByUsuarioId(id);
    }

}
