package com.springweb.dondelaborar.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.springweb.dondelaborar.models.*;
import com.springweb.dondelaborar.services.EmpleadoService;
import com.springweb.dondelaborar.services.EmpresaService;
import com.springweb.dondelaborar.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private EmpresaService empresaService;




    @PostMapping
    public String iniciarSesion(@Valid Usuario usuario, Model view){
        String template = "redirect:/";
        Usuario user = null;

        try {
            user = usuarioService.findByCorreoAndPassword(usuario.getCorreo(), usuario.getPassword());
            System.out.println(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        
        if(user != null){

            switch(user.getRol()){

                case 1: 
                    //Se busca al empleado por la propiedad USUARIO pasandole el objeto y no un ID
                    Empleado empleado = empleadoService.findByUsuario(user);
                    System.out.println(empleado.toString());
                    view.addAttribute("usuario", user);
                    view.addAttribute("empleado", empleado);
                    template = "./empleado/mainEmpleado.html";
                    break;
                case 2:
                    Empresa empresa = empresaService.findByUsuario(user);
                    view.addAttribute("usuario", user);
                    view.addAttribute("empresa", empresa);
                    template = "./empresa/mainEmpresa.html";
                    break;

                default:
                    System.out.println("Rol incorrecto");
            }
            
        }


        return template;
    }



    @GetMapping("{id}")
    public String redirectIniciarSesion(@PathVariable("id") int id, Model view){
        String template = "redirect:/";
        Usuario user = null;

        System.out.println("id es null" +id);

        try {
            user = usuarioService.findById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
        
        if(user != null){

            switch(user.getRol()){

                case 1: 
                    //Se busca al empleado por la propiedad USUARIO pasandole el objeto y no un ID
                    Empleado empleado = empleadoService.findByUsuario(user);
                    System.out.println(empleado.toString());
                    view.addAttribute("usuario", user);
                    view.addAttribute("empleado", empleado);
                    template = "./empleado/mainEmpleado.html";
                    break;
                case 2:
                    Empresa empresa = empresaService.findByUsuario(user);
                    view.addAttribute("usuario", user);
                    view.addAttribute("empresa", empresa);
                    template = "./empresa/mainEmpresa.html";
                    break;

                default:
                    System.out.println("Rol incorrecto");
            }
            
        }


        return template;
    }

    
}
