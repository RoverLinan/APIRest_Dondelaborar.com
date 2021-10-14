package com.springweb.dondelaborar.services;

import java.util.Collection;

import com.springweb.dondelaborar.models.Usuario;



public interface UsuarioService {


    public Collection<Usuario> obtenerUsuarios();

    public Usuario findById(int id);

    public Usuario guardarUsuario(Usuario usuario);

    public Usuario findByEmail(String email);

    public boolean existsByCorreo(String correo);

    public Usuario findByCorreoAndPassword(String correo, String password);
}
