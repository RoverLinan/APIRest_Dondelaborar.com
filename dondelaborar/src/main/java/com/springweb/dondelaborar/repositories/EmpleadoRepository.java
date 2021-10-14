package com.springweb.dondelaborar.repositories;
import com.springweb.dondelaborar.models.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Integer>{
    

    public Empleado findByUsuario(Usuario usuario);
    public Empleado findById(int id);


}
