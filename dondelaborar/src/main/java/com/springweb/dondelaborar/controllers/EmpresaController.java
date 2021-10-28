package com.springweb.dondelaborar.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("api/empresa")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ConvocatoriaService convocatoriaService;

    @Autowired
    private UbicacionServicio ubicacionServicio;



    @GetMapping
    public Empresa getEmpresa(@RequestParam("userid") int id){

        Usuario usuario = usuarioService.findById(id);
        Empresa empresa = empresaService.findByUsuario(usuario);
        return empresa;
    }
    





    @PostMapping("/guardar")
    public Empresa guardarEmpresa(@RequestBody Map<String,String> objeto, BindingResult result) throws Exception{
        System.out.println(objeto);
       
        if(!objeto.containsValue(null)){

            if(!usuarioService.existsByCorreo(objeto.get("correo"))){
                Usuario usuarioReturn = usuarioService.guardarUsuario(validarCampoUsuario(objeto));
                Empresa empresa = validarCampoEmpresa(objeto);
                empresa.setUsuario(usuarioReturn);
                System.out.println("se guardooo la empresa");
                return empresaService.guardarEmpresa(empresa);
                
            }

        }

        return null;
    }


    private Usuario validarCampoUsuario(Map<String,String> objeto) throws Exception{
        Usuario usuario = new Usuario();
        usuario.setCorreo(objeto.get("correo"));
        usuario.setPassword(objeto.get("password"));
        usuario.setRol(2);
        usuario.setUrlFoto(objeto.get("urlfoto"));
        return usuario;
    }

    private Empresa validarCampoEmpresa(Map<String,String> objeto) throws Exception{
        Empresa empresa = new Empresa();
        empresa.setRazonSocial(objeto.get("razonSocial"));
        empresa.setRuc(objeto.get("ruc"));
        empresa.setRubro(objeto.get("rubro"));
        empresa.setFechacreacion(convertirStringToDate(objeto.get("fechaActividades")));
        empresa.setTelefono(objeto.get("telefono"));
        empresa.setDireccion(objeto.get("direccion"));

        return empresa;

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
