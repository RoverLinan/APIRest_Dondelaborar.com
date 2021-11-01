package com.springweb.dondelaborar.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.springweb.dondelaborar.models.*;
import com.springweb.dondelaborar.services.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("api/persona")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ConvocatoriaService convocatoriaService;

    @Autowired
    private PostularServicio postularServicio;




    @PostMapping("/guardar")
    public Empleado guardarPersona(@RequestBody Map<String,String> objeto) throws Exception{

        System.out.println(objeto);
        if(!objeto.containsValue(null)){

            if(!usuarioService.existsByCorreo(objeto.get("correo"))){
            
                Usuario usuarioReturn = usuarioService.guardarUsuario( validarCampoUsuario(objeto));
                Empleado empleado = validarCampoPersona(objeto);
                empleado.setUsuario(usuarioReturn);
                 return empleadoService.guardarEmpleado(empleado);
            }

        }
    
        

        return null;

    }


    private Usuario validarCampoUsuario(Map<String,String> objeto) throws Exception{
        Usuario usuario = new Usuario();
        usuario.setCorreo(objeto.get("correo"));
        usuario.setPassword(objeto.get("password"));
        usuario.setRol(1);
        usuario.setUrlFoto(objeto.get("urlfoto"));
        return usuario;
    }

    private Empleado validarCampoPersona(Map<String,String> objeto) throws Exception{
       
        Empleado empleado = new Empleado();
        empleado.setNombre(objeto.get("nombres"));
        empleado.setApellidos(objeto.get("apellidos"));
        empleado.setDni(objeto.get("dni"));
        empleado.setDomicilio(objeto.get("direccion"));
        empleado.setTelefono(objeto.get("telefono"));
        empleado.setGenero(Integer.parseInt(objeto.get("genero")));

        return empleado;
    }

    
    @PostMapping
    public String registrar(@Validated Empleado empleado, @Validated Usuario usuario, BindingResult result, Model view) {
        String template="redirect:/";

        if(result.hasErrors()){
            template = "./empleado/registrarEmpleado.html";
        }else{

            if(!usuarioService.existsByCorreo(usuario.getCorreo())){
                usuario.setRol(1);
                Usuario user =  usuarioService.guardarUsuario(usuario);
                empleado.setUsuario(user);
                empleadoService.guardarEmpleado(empleado);


            }else{
                view.addAttribute("mailConflict", "El correo ya esta registrado");
                template = "./empleado/registrarEmpleado.html";
            }
        }

    

        return template;
    }



    @PostMapping("/buscar")
    public String buscarConvocatorias(@RequestParam("idempleado") int idEmpleado,  @RequestParam("puesto") String puesto, @RequestParam("departamento") String departamento, Model view ){

        System.out.println(puesto + "  " + idEmpleado);
        
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

        Empleado empleado = empleadoService.buscarEmpleadoById(idEmpleado);
        view.addAttribute("empleado", empleado);
        view.addAttribute("convocatoria", new Convocatoria());
        return "./empleado/mostrarConvocatorias.html";

    }
    

    @PostMapping("/convocatorias")
    private String obtenerConvocatorias(@RequestParam("idconvocatoria") int idConvocatoria, @RequestParam("idempleado") int idEmpleado , Model view){

        System.out.println(idConvocatoria + "         ------------    " + idEmpleado);
        Convocatoria convocatoria = convocatoriaService.findById(idConvocatoria);
        Empleado empleado = empleadoService.buscarEmpleadoById(idEmpleado);
        if(convocatoria != null && empleado != null){
            view.addAttribute("empleado", empleado);
            view.addAttribute("convocatoria", convocatoria);
            return "./empleado/informacionConvocatoria.html";
        }

        return "redirect:/";
        
    }

    // se envian los id para asociar a la Postulacion
    @PostMapping("/convocatorias/postular")
    private String postularConvocatorias(@RequestParam("comentario") String comentario, @RequestParam("idconvocatoria") int idConvocatoria, @RequestParam("idempleado") int idEmpleado , Model view){

        System.out.println(idConvocatoria + "         ------------    " + idEmpleado);
        Convocatoria convocatoria = convocatoriaService.findById(idConvocatoria);
        Empleado empleado = empleadoService.buscarEmpleadoById(idEmpleado);
        if(convocatoria != null && empleado != null){
            //validar doble postulacion a la misma convocatoria
            Postulacion postulacion = new Postulacion();
            postulacion.setComentario(comentario);
            postulacion.setEmpleado(empleado);
            postulacion.setConvocatoria(convocatoria);
            postulacion.setEstado(true);

            postularServicio.guardarPostulacion(postulacion);

            view.addAttribute("empleado", empleado);
            view.addAttribute("convocatoria", convocatoria);
            return "./empleado/informacionConvocatoria.html";
        }

        return "redirect:/";
        
    }


    @GetMapping("{id}/postulaciones")
    public String misPostulaciones(@PathVariable("id") int id, Model view){
        String  template = "./empleado/misPostulaciones.html";
        Empleado empleado = empleadoService.buscarEmpleadoById(id);
        List<Postulacion> listaPostulaciones = postularServicio.obtenerMisPostulaciones(empleado);

        if(listaPostulaciones != null){
            view.addAttribute("empleado",empleado);
            view.addAttribute("listaPostulaciones", listaPostulaciones);
            view.addAttribute("mostrarMensaje", false);
        }else{
            view.addAttribute("mostrarMensaje", true);
            view.addAttribute("mensaje", "Aun no ah postulado a ninguna convocatoria");
        }

        return template;
    }

    
}
