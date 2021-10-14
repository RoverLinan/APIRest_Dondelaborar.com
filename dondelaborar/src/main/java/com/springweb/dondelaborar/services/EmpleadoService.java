package com.springweb.dondelaborar.services;

import java.util.ArrayList;

import com.springweb.dondelaborar.models.*;



public interface EmpleadoService {
    

    public ArrayList<Empleado> obtenerEmpleados();

    public Empleado buscarEmpleadoById(int id);
    public Empleado guardarEmpleado(Empleado empleado);

    public Empleado findByUsuario(Usuario user);
    

    
}
