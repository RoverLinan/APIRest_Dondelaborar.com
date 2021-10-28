package com.springweb.dondelaborar.controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.springweb.dondelaborar.models.*;
import com.springweb.dondelaborar.services.EmpleadoService;
import com.springweb.dondelaborar.services.EmpresaService;
import com.springweb.dondelaborar.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios/")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private EmpresaService empresaService;



    @GetMapping
    public Usuario iniciarSesion( @RequestParam("mail")String correo, @RequestParam("password") String pass ){

       return usuarioService.findByCorreoAndPassword(correo, pass);
    }


    
}
