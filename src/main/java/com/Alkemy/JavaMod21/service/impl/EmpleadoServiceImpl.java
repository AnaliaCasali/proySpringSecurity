package com.Alkemy.JavaMod21.service.impl;

import com.Alkemy.JavaMod21.exception.EmpleadoNotFoundException;
import com.Alkemy.JavaMod21.model.Empleado;
import com.Alkemy.JavaMod21.model.Imagen;
import com.Alkemy.JavaMod21.repository.EmpleadoRepository;
import com.Alkemy.JavaMod21.repository.ImagenRepository;
import com.Alkemy.JavaMod21.service.EmpleadoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    private final ImagenRepository imagenRepository;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository,
                               ImagenRepository imagenRepository ){
        this.empleadoRepository=empleadoRepository;
        this.imagenRepository= imagenRepository;
    }


    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> findById(Long id) {
        return empleadoRepository.findById(id);
    }

    @Override
    @Transactional
    public Empleado save(Empleado empleado) {
        Imagen foto=  empleado.getFotoPerfil();
        if(foto!=null && foto.getId()!=null)
        {   //si ya esta creada
                Imagen fotoGuardada= createOrFind(foto);
                empleado.setFotoPerfil(fotoGuardada);
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado update(Empleado empleado, Long id) throws EmpleadoNotFoundException {
        Empleado empleadoaactualizar=  this.findById(id)
                .orElseThrow( ()-> new EmpleadoNotFoundException(id));

        empleadoaactualizar.setSalario(empleado.getSalario());
        empleadoaactualizar.setCargo(empleado.getCargo());
        empleadoaactualizar.setDni(empleado.getDni());
        empleadoaactualizar.setEmail(empleado.getEmail());
        empleadoaactualizar.setNombre(empleado.getNombre());
        empleadoaactualizar.setFechaNacimiento(empleado.getFechaNacimiento());
        Imagen foto=  empleado.getFotoPerfil();
        if(foto!=null && foto.getId()!=null)
        {   //si ya esta creada
            Imagen fotoGuardada= createOrFind(foto);
            empleadoaactualizar.setFotoPerfil(fotoGuardada);
        }

        return empleadoRepository.save(empleado);

    }

    private Imagen createOrFind(Imagen imagen){
        return  imagenRepository.findById(imagen.getId())
                .orElse(createImagen(imagen));
    }

    private Imagen createImagen(Imagen imagen){
           Imagen img = Imagen.builder()
                   .id(imagen.getId())
                   .nombreImagen(imagen.getNombreImagen())
                   .build();
            return imagenRepository.save(img);
    }

    @Override
    public void deleteById(Long id) throws  EmpleadoNotFoundException {

        Empleado empleado=  this.findById(id)
                    .orElseThrow( ()-> new EmpleadoNotFoundException(id));

        empleadoRepository.deleteById(id);
    }

    @Override
    public List<Empleado> findByNombre(String nombre) {
        return empleadoRepository.findByNombre(nombre);
    }
}
