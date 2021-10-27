package com.springweb.dondelaborar.controllers;

import java.util.ArrayList;
import java.util.List;

import com.springweb.dondelaborar.models.Convocatoria;
import com.springweb.dondelaborar.services.ConvocatoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/convocatoria")
public class ConvocatoriaController {
    
    @Autowired
    ConvocatoriaService convocatoriaService;


    @GetMapping
    public Convocatoria getConvocatoria(@RequestParam("id") int id){
        System.out.println(id);
        return convocatoriaService.findById(id);
    }


    @GetMapping("/buscar")
    public ArrayList<Convocatoria> getConvocatorias(@RequestParam("puesto") String puesto,@RequestParam("departamento") String departamento){
       
        
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
        }

        return (ArrayList<Convocatoria>) listaConvocatorias;

    }






}
