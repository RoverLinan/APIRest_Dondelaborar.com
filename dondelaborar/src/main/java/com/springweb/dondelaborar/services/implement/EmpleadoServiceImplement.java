package com.springweb.dondelaborar.services.implement;

import java.util.ArrayList;

import com.springweb.dondelaborar.models.*;
import com.springweb.dondelaborar.repositories.EmpleadoRepository;
import com.springweb.dondelaborar.services.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImplement implements EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public ArrayList<Empleado> obtenerEmpleados() {

        return  (ArrayList<Empleado>) empleadoRepository.findAll();
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
       
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado findByUsuario(Usuario user) {

        return empleadoRepository.findByUsuario(user);
    }

    @Override
    public Empleado buscarEmpleadoById(int id) {
        
        return empleadoRepository.findById(id);
    }







}
