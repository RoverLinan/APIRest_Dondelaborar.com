package com.springweb.dondelaborar.services;

import java.util.List;

import com.springweb.dondelaborar.models.Empleado;
import com.springweb.dondelaborar.models.Postulacion;

public interface PostularServicio {


    public Postulacion guardarPostulacion( Postulacion postulacion);
    public List<Postulacion> obtenerPostulaciones();
    public List<Postulacion> obtenerMisPostulaciones(Empleado empleado);
    public Postulacion buscarPorId(int id);
    
    
}
