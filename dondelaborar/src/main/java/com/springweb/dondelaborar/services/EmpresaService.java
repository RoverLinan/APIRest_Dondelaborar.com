package com.springweb.dondelaborar.services;


import java.util.Collection;

import com.springweb.dondelaborar.models.*;


public interface EmpresaService {




    public Collection<Empresa> obtenerEmpresas();

    public Empresa guardarEmpresa(Empresa empresa);

    public Empresa findByUsuario(Usuario user);

    public boolean existsById(int id);

    public Empresa findById(int id);
}
