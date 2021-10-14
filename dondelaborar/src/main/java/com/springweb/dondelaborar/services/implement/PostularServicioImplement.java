package com.springweb.dondelaborar.services.implement;

import java.util.ArrayList;
import java.util.List;

import com.springweb.dondelaborar.models.Empleado;
import com.springweb.dondelaborar.models.Postulacion;
import com.springweb.dondelaborar.repositories.PostularRepository;
import com.springweb.dondelaborar.services.PostularServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostularServicioImplement implements PostularServicio {

    @Autowired
    private PostularRepository postularRepository;


    @Override
    public Postulacion guardarPostulacion(Postulacion postulacion) {
    
        return postularRepository.save(postulacion);
    }

    @Override
    public List<Postulacion> obtenerPostulaciones() {
        
        return (ArrayList<Postulacion>)postularRepository.findAll();
    }

    @Override
    public Postulacion buscarPorId(int id) {
        
        return postularRepository.findById(id);
    }

    @Override
    public List<Postulacion> obtenerMisPostulaciones(Empleado empleado) {
        
        return postularRepository.findByEmpleado(empleado);
    }
    
}
