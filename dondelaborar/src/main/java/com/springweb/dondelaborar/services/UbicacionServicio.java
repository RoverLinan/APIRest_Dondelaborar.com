package com.springweb.dondelaborar.services;


import com.springweb.dondelaborar.models.Ubicacion;

public interface UbicacionServicio {
    
    public Ubicacion save(Ubicacion ubicacion);
    public Ubicacion findByPaisAndDepartamentoAndProvinciaAndDistrito(Ubicacion ubicacion);
    public boolean existsByPaisAndDepartamentoAndProvinciaAndDistrito(Ubicacion ubicacion);
}
