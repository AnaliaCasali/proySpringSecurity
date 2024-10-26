package com.Alkemy.JavaMod21.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="empleados")
public class Empleado {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre del empleado no debe ser nulo")
    @Size(min=3 , max=100, message = "El nombre del empleado debe " +
            "   tener al menos 3 caracteres y no superar los 100")
    private String nombre;

    @Size(min=3 , max=50, message = "El cargo del empleado debe " +
            "   tener al menos 3 caracteres y no superar los 50")
    private String cargo;


    @Min(value=10000, message = "El importe de salario debe ser mayor que cero")
    @Digits(integer = 7, fraction = 2, message = "Debe tener 2 decimales")
    private double salario;

    @NotNull (message = "el dni es obligatorio")
    @Pattern(regexp="\\{a}\\d{7,8}", message = "El dni debe contener 7 u 8 digitos" )
    private String dni;

    @Email (message = "el mail debe tener formato valido")
    private String email;

    @Column(name="fecha-nacimiento")
    @Past(message = "La fecha debe ser anterior a la actual")
    private LocalDateTime fechaNacimiento;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "imagen_id", nullable = true)
    private Imagen fotoPerfil;
}
