package com.springweb.dondelaborar.repositories;

import com.springweb.dondelaborar.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario,Integer>{
    
    
    public Usuario  findByCorreoAndPassword(String correo, String password);

    public boolean existsByCorreo(String correo);

    public Usuario findById(int id);


}
