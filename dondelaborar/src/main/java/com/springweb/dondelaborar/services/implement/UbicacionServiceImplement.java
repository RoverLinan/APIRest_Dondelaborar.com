package com.springweb.dondelaborar.services.implement;

import com.springweb.dondelaborar.models.Ubicacion;
import com.springweb.dondelaborar.repositories.UbicacionRepository;
import com.springweb.dondelaborar.services.UbicacionServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UbicacionServiceImplement  implements UbicacionServicio{

    @Autowired
    private UbicacionRepository ubicacionRepository;



    

    @Override
    public Ubicacion save(Ubicacion ubicacion) {
        
        return ubicacionRepository.save(ubicacion);
    }

    @Override
    public Ubicacion findByPaisAndDepartamentoAndProvinciaAndDistrito(Ubicacion ubicacion) {
        
        return ubicacionRepository.findByPaisAndDepartamentoAndProvinciaAndDistrito(    ubicacion.getPais(),
                                                                                        ubicacion.getDepartamento(),
                                                                                        ubicacion.getProvincia(),
                                                                                        ubicacion.getDistrito());
    }

    @Override
    public boolean existsByPaisAndDepartamentoAndProvinciaAndDistrito(Ubicacion ubicacion) {
        
        return ubicacionRepository.existsByPaisAndDepartamentoAndProvinciaAndDistrito(  ubicacion.getPais(),
                                                                                        ubicacion.getDepartamento(),
                                                                                        ubicacion.getProvincia(),
                                                                                        ubicacion.getDistrito());

    }
    
}
