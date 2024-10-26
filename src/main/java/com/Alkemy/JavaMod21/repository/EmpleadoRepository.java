package com.Alkemy.JavaMod21.repository;


import com.Alkemy.JavaMod21.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByNombre(String nombre);

}
