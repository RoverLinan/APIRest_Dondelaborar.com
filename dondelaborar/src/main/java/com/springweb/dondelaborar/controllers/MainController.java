package com.springweb.dondelaborar.controllers;



import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.springweb.dondelaborar.models.*;
import com.springweb.dondelaborar.services.ConvocatoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    
    @Autowired
    private ConvocatoriaService convocatoriaService;

    @GetMapping("/")
    public String welcome(Model view){
        Usuario usuario = new Usuario();
        view.addAttribute("usuario", usuario);
        view.addAttribute("convocatoria", new Convocatoria());
        view.addAttribute("ubicacion", new Ubicacion());
        return "index.html";

    }


    @GetMapping("/convocatorias")
    private String buscarConvocatorias(@RequestParam("puesto") String puesto, @RequestParam("departamento") String departamento, Model view){

        List<Convocatoria> listaAux;
        List<Convocatoria> listaConvocatorias = new ArrayList<>();

        if(puesto.isBlank() && departamento.equalsIgnoreCase("todo")){
            listaConvocatorias = convocatoriaService.findAll();
        }else{

            listaAux = convocatoriaService.findByPuesto(puesto);
            if(departamento.equalsIgnoreCase("todo")){
                listaConvocatorias = listaAux;
            }else{
                for (Convocatoria convocatoria : listaAux) {
                    if(convocatoria.getUbicacion().getDepartamento().equalsIgnoreCase(departamento)){
                        listaConvocatorias.add(convocatoria);
                    }
                }
    
            }
    
            if(listaConvocatorias != null && listaConvocatorias.size()>0){
                view.addAttribute("result","Se encontro " + listaConvocatorias.size() + " puestos para su busqueda");
                view.addAttribute("listaConvocatorias", listaConvocatorias);
            }else{
                view.addAttribute("result", "No se encontraron puestos");
            }
        }

        Usuario usuario = new Usuario();
        view.addAttribute("usuario", usuario);
        view.addAttribute("convocatoria", new Convocatoria());
        view.addAttribute("ubicacion", new Ubicacion());
        return "mostrarConvocatoriasSL.html";
    }


    @PostMapping("/convocatorias")
    public String mostrarConvocatorias(@Valid Ubicacion ubicacion, @Valid Convocatoria convocatoria, Model view){
        List<Convocatoria> convocatorias;
        if(ubicacion.getDepartamento().equalsIgnoreCase("todo")){
           
          
            convocatorias = convocatoriaService.findByPuesto(convocatoria.getPuesto());
        }else{
            convocatorias = convocatoriaService.findByPuestoAndDepartamento(convocatoria.getPuesto(), ubicacion.getDepartamento());
        }

        if(convocatorias != null && convocatorias.size()>0){
            view.addAttribute("result","Se encontro " + convocatorias.size() + " puestos para su busqueda");
            view.addAttribute("listaConvocatorias", convocatorias);
        }else{
            view.addAttribute("result", "No se encontraron puestos");
        }

        Usuario usuario = new Usuario();
        view.addAttribute("usuario", usuario);
        view.addAttribute("convocatoria", new Convocatoria());
        view.addAttribute("ubicacion", new Ubicacion());


        return "mostrarConvocatoriasSL.html";
    }

    
    
    @GetMapping("/convocatorias/{id}")
    private String obtenerConvocatorias(@PathVariable("id") int idConvocatoria, Model view){


        Convocatoria convocatoria = convocatoriaService.findById(idConvocatoria);
        if(convocatoria != null){
            Usuario usuario = new Usuario();
            view.addAttribute("usuario", usuario);
            view.addAttribute("convocatoria", convocatoria);
            return "informacionConvocatoriaSL.html";
        }

        return "redirect:/";
        
    }



}
