package com.springweb.dondelaborar.services.implement;

import java.util.Collection;

import com.springweb.dondelaborar.models.Empresa;
import com.springweb.dondelaborar.models.Usuario;
import com.springweb.dondelaborar.repositories.EmpresaRepository;
import com.springweb.dondelaborar.services.EmpresaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImplement implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Collection<Empresa> obtenerEmpresas() {
        
        return null;
    }

    @Override
    public Empresa guardarEmpresa(Empresa empresa) {
    
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa findByUsuario(Usuario user) {
        return  empresaRepository.findByUsuario(user);
    }

    @Override
    public boolean existsById(int id) {
        return empresaRepository.existsById(id);
    }

    @Override
    public Empresa findById(int id) {
    
        return empresaRepository.findById(id);
    }
    
}
