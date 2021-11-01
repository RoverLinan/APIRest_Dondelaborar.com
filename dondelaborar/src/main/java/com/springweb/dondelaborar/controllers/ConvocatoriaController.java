package com.springweb.dondelaborar.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.springweb.dondelaborar.models.Convocatoria;
import com.springweb.dondelaborar.models.Empresa;
import com.springweb.dondelaborar.models.Ubicacion;
import com.springweb.dondelaborar.models.Usuario;
import com.springweb.dondelaborar.services.ConvocatoriaService;
import com.springweb.dondelaborar.services.EmpresaService;
import com.springweb.dondelaborar.services.UbicacionServicio;
import com.springweb.dondelaborar.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/convocatoria")
@CrossOrigin(origins = "http://localhost:4200")
public class ConvocatoriaController {
    
    @Autowired
    ConvocatoriaService convocatoriaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    EmpresaService empresaService;

    @Autowired
    UbicacionServicio ubicacionServicio;

    @GetMapping("/all")
    public ArrayList<Convocatoria> getConvocatoriaByUser(@RequestParam("userid") int id){
        System.out.println(id);
        Usuario usuario = usuarioService.findById(id);
        if( usuario!= null){
            Empresa empresa = empresaService.findByUsuario(usuario);
            ArrayList<Convocatoria> lista = (ArrayList<Convocatoria>) convocatoriaService.findByEmpresa(empresa);
            return lista;
        }

        return null;
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


    @GetMapping("/invitado/buscar")
    public ArrayList<Convocatoria> getConvocatoriasInvitado(@RequestParam("puesto") String puesto,@RequestParam("departamento") String departamento){
       
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

        System.out.println(listaConvocatorias) ;

        return (ArrayList<Convocatoria>) listaConvocatorias;

    }




    @PostMapping("/guardar")
    public Convocatoria guardarConvocatoria(@RequestBody Map<String,String> objeto)throws Exception{
        System.out.println(objeto);
        if(objeto.containsKey(null)){
            return null;
        }
        Usuario usuarioReturn = null;
        Ubicacion ubicacionReturn;
        Empresa empresaReturn;
        Convocatoria convocatoriaReturn = null;

        Convocatoria convocatoria = validarCampos(objeto);
        Ubicacion ubicacion = new Ubicacion();
        
        ubicacion.setDepartamento(objeto.get("departamento"));
        ubicacion.setProvincia(objeto.get("provincia"));
        ubicacion.setDistrito(objeto.get("distrito"));
        ubicacion.setPais("Peru");

        if(ubicacionServicio.existsByPaisAndDepartamentoAndProvinciaAndDistrito(ubicacion)){
            
            ubicacionReturn = ubicacionServicio.findByPaisAndDepartamentoAndProvinciaAndDistrito(ubicacion);


        }else{

            ubicacionReturn =  ubicacionServicio.save(ubicacion);
            

        }
        convocatoria.setUbicacion(ubicacionReturn);
        usuarioReturn = usuarioService.findById(Integer.parseInt(objeto.get("idempresa")));
        empresaReturn = empresaService.findByUsuario(usuarioReturn);
        
        if(empresaReturn != null){
            convocatoria.setEmpresa(empresaReturn);
            convocatoriaReturn = convocatoriaService.save(convocatoria);
        }

        System.out.println(ubicacionReturn +"           "+empresaReturn + "           " + convocatoriaReturn);

        return convocatoriaReturn;
    }


    private Convocatoria validarCampos(Map<String,String> objeto) throws Exception{
        if(objeto.containsValue(null)){
            return null;
        }
        Convocatoria convocatoria = new Convocatoria();
        convocatoria.setPuesto(objeto.get("puesto"));
        convocatoria.setDescripcion(objeto.get("descripcion"));
        convocatoria.setArea(objeto.get("area"));
        convocatoria.setCantEmpleados(Integer.parseInt(objeto.get("cantEmpleados")));
        convocatoria.setHorario(objeto.get("horario"));
        convocatoria.setSalario(Double.parseDouble(objeto.get("salario")));
        convocatoria.setFechaFin(convertirStringToDate(objeto.get("fecha")));
        return convocatoria;
    }

    private Date convertirStringToDate(String date){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(date);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
    }


}
