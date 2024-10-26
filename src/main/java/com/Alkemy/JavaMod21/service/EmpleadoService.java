package com.Alkemy.JavaMod21.service;


import com.Alkemy.JavaMod21.exception.EmpleadoNotFoundException;
import com.Alkemy.JavaMod21.model.Empleado;
import com.Alkemy.JavaMod21.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> findAll();
    Optional<Empleado> findById(Long id);
    Empleado save(Empleado empleado);
    Empleado update(Empleado empleado, Long id) throws EmpleadoNotFoundException;
    void deleteById(Long id) throws EmpleadoNotFoundException;
    List<Empleado> findByNombre(String nombre);
}
