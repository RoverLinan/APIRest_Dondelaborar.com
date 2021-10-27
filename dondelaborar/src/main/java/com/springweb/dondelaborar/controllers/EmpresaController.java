package com.springweb.dondelaborar.controllers;

import java.util.Map;

import javax.validation.Valid;

import com.springweb.dondelaborar.models.Convocatoria;
import com.springweb.dondelaborar.models.Empresa;
import com.springweb.dondelaborar.models.Ubicacion;
import com.springweb.dondelaborar.models.Usuario;
import com.springweb.dondelaborar.services.ConvocatoriaService;
import com.springweb.dondelaborar.services.EmpresaService;
import com.springweb.dondelaborar.services.UbicacionServicio;
import com.springweb.dondelaborar.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ConvocatoriaService convocatoriaService;

    @Autowired
    private UbicacionServicio ubicacionServicio;



    @GetMapping("/registrar")
    public String registrarEmpresa(Map<String,Object> view){
        Empresa empresa = new Empresa();
        Usuario usuario = new Usuario();

        view.put("empresa", empresa);
        view.put("usuario", usuario);
        return "./empresa/registrarEmpresa.html";
    }







    @PostMapping()
    public String guardarEmpresa(@Valid Empresa empresa, @Valid Usuario usuario, BindingResult result){

        String template = "./empresa/registrarEmpresa.html";

        if(result.hasErrors()){
            return template;
        }else{

            if(usuarioService.existsByCorreo(usuario.getCorreo())){
                
                return template;
            }else{
                usuario.setRol(2);
                Usuario user = usuarioService.guardarUsuario(usuario);
                empresa.setUsuario(user);
                empresaService.guardarEmpresa(empresa);
            }

        }

        return template = "redirect:/";
    }


    @GetMapping("{id}/convocatorias")
    public String listarConvocatorias(   @PathVariable("id") int id, 
                                        Model view){

        String template = "redirect:/";
                                    
        if(empresaService.existsById(id)){
            Empresa empresa = empresaService.findById(id);
        }
        return template;
    }


    @GetMapping("{id}/convocatoria/registrar")
    public String registrarEmpleoGet(   @PathVariable("id") int id, 
                                        Model view){

        String template = "redirect:/usuario";                                 
        Ubicacion ubicacion = new Ubicacion();  
        Convocatoria convocatoria = new Convocatoria();                                  
        if(empresaService.existsById(id)){

            Empresa empresa = empresaService.findById(id);
            view.addAttribute("empresa", empresa);
            view.addAttribute("empresaId", id);
            view.addAttribute("ubicacion", ubicacion);
            view.addAttribute("convocatoria", convocatoria);
            return "./empresa/registrarConvocatoria.html";
        }
        return template;
    }

    @PostMapping("{empresaId}/convocatoria")
    public String registrarEmpleoPost(  @PathVariable("empresaId") int id,
                                        @Valid Convocatoria convocatoria,
                                        @Valid Ubicacion ubicacion, RedirectAttributes attributes){
        
        String template = "redirect:/usuario/{id}";
        ubicacion.setPais("Peru");                                  
        if(empresaService.existsById(id)){
            Empresa empresa = empresaService.findById(id);
            Ubicacion ubicacionAux;
            if(ubicacionServicio.existsByPaisAndDepartamentoAndProvinciaAndDistrito(ubicacion)){
                ubicacionAux = ubicacionServicio.findByPaisAndDepartamentoAndProvinciaAndDistrito(ubicacion);
                
            }else{
                System.out.println("ubicacion no encontrada");
                ubicacionAux = ubicacionServicio.save(ubicacion);
            }



            convocatoria.setUbicacion(ubicacionAux);
            convocatoria.setEmpresa(empresa);

            System.out.println(convocatoria.toString());

            convocatoriaService.save(convocatoria);
            attributes.addAttribute("id", empresa.getUsuario().getId());
            
        }
        
        return template;
    }


}
