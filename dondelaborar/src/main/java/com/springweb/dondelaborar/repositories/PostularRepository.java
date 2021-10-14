package com.springweb.dondelaborar.repositories;

import java.util.List;

import com.springweb.dondelaborar.models.Empleado;
import com.springweb.dondelaborar.models.Postulacion;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostularRepository extends CrudRepository<Postulacion,Integer>{
    
    public Postulacion findById(int id);
    public List<Postulacion> findByEmpleado(Empleado empleado);

}
