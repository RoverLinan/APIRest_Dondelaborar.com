package com.springweb.dondelaborar.controllers;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
    public Usuario iniciarSesion( @RequestParam("mail")String correo, @RequestParam("password") String pass){

       return usuarioService.findByCorreoAndPassword(correo, pass);
    }

    @PostMapping(path = "auth", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<Usuario> iniciarSesion( @RequestBody Map<String,String> usuario){
        System.out.println(usuario.toString());
        Usuario userReturn =usuarioService.findByCorreoAndPassword(usuario.get("correo"), usuario.get("password"));
       
        if(userReturn != null)
            return new ResponseEntity<Usuario>(userReturn,HttpStatus.OK);
        else    
            return new ResponseEntity<Usuario>(new Usuario(),HttpStatus.BAD_REQUEST);
       
    }



    
}
