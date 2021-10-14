package com.springweb.dondelaborar.repositories;

import com.springweb.dondelaborar.models.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa,Integer> {
    

    public Empresa findByUsuario(Usuario user);
    public Empresa findById(int id);
}
