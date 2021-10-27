package com.springweb.dondelaborar.controllers;


import java.util.ArrayList;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.springweb.dondelaborar.models.*;
import com.springweb.dondelaborar.services.EmpleadoService;
import com.springweb.dondelaborar.services.EmpresaService;
import com.springweb.dondelaborar.services.UsuarioService;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private EmpresaService empresaService;



    @PostMapping("/login")
    public Usuario iniciarSesion( @RequestParam("mail")String correo, @RequestParam("pass") String pass ){

       return usuarioService.findByCorreoAndPassword(correo, pass);
    }




    @GetMapping("{id}")
    public Usuario redirectIniciarSesion(@PathVariable("id") int id){
        Usuario user = null;

        System.out.println("id es null" +id);

        try {
            user = usuarioService.findById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        


        return user;
    }

    
}
