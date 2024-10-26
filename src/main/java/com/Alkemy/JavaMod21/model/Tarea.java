package com.Alkemy.JavaMod21.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    private String titulo;
    private String descripcion;
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="empleado_id", nullable = true )
    private Empleado empleado;

}
