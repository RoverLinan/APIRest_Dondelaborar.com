package com.springweb.dondelaborar.repositories;

import java.util.List;

import com.springweb.dondelaborar.models.Convocatoria;
import com.springweb.dondelaborar.models.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvocatoriaRepository extends JpaRepository<Convocatoria,Integer> {
    

    public Convocatoria findById(int id);
    public List<Convocatoria> findByPuestoContainingIgnoreCase(String puesto);
    public List<Convocatoria> findByEmpresa(Empresa empresa);

}
