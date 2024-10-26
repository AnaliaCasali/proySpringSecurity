package com.Alkemy.JavaMod21.repository;

import com.Alkemy.JavaMod21.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea,Long > {
}


