package com.springweb.dondelaborar.services;

import java.util.List;

import com.springweb.dondelaborar.models.*;

public interface ConvocatoriaService {
    

    public Convocatoria save(Convocatoria convocatoria);
    public Convocatoria findById(int id);
    
    public List<Convocatoria> findAll();
    public List<Convocatoria> findByPuesto(String puesto);
    public List<Convocatoria> findByPuestoAndDepartamento(String puesto, String departamento);
}
