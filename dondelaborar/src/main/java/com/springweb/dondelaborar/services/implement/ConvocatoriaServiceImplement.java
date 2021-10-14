package com.springweb.dondelaborar.services.implement;

import java.util.List;

import com.springweb.dondelaborar.models.Convocatoria;
import com.springweb.dondelaborar.repositories.ConvocatoriaRepository;
import com.springweb.dondelaborar.services.ConvocatoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvocatoriaServiceImplement implements ConvocatoriaService {

    @Autowired
    private ConvocatoriaRepository convocatoriaRepository;


    @Override
    public Convocatoria save(Convocatoria convocatoria) {
        return convocatoriaRepository.save(convocatoria);
    }

    @Override
    public Convocatoria findById(int id) {
        
        return convocatoriaRepository.findById(id);
    }

    @Override
    public List<Convocatoria> findAll() {
        
        return convocatoriaRepository.findAll();
    }

    @Override
    public List<Convocatoria> findByPuesto(String puesto) {
        
        return convocatoriaRepository.findByPuestoContainingIgnoreCase(puesto);
    }

    @Override
    public List<Convocatoria> findByPuestoAndDepartamento(String puesto, String departamento) {
        
        return null;
    }
    
}
