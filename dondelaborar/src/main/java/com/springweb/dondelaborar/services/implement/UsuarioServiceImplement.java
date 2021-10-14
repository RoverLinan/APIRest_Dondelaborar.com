package com.springweb.dondelaborar.services.implement;

import java.util.ArrayList;

import com.springweb.dondelaborar.models.Usuario;
import com.springweb.dondelaborar.repositories.UsuarioRepository;
import com.springweb.dondelaborar.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImplement  implements UsuarioService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ArrayList<Usuario> obtenerUsuarios() {
        
        return  (ArrayList<Usuario>)usuarioRepository.findAll();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findByEmail(String correo) {
        return null;
    }

    @Override
    public Usuario findByCorreoAndPassword(String correo, String password) {
        
        return usuarioRepository.findByCorreoAndPassword(correo, password);
    }

    @Override
    public boolean existsByCorreo(String correo) {

        return usuarioRepository.existsByCorreo(correo);
    }

    @Override
    public Usuario findById(int id) {
        
        return usuarioRepository.findById(id);
    }

    
    

}
