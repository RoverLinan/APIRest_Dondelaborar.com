package com.springweb.dondelaborar.repositories;

import com.springweb.dondelaborar.models.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository  extends JpaRepository<Ubicacion, Integer>{
    

    public Ubicacion findByPaisAndDepartamentoAndProvinciaAndDistrito(String pais, String departamento, String provincia, String distrito);
    public boolean existsByPaisAndDepartamentoAndProvinciaAndDistrito(String pais, String departamento, String provincia, String distrito);


}
