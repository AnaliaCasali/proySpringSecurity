package com.Alkemy.JavaMod21.controller;

import com.Alkemy.JavaMod21.exception.EmpleadoNotFoundException;
import com.Alkemy.JavaMod21.model.Empleado;
import com.Alkemy.JavaMod21.service.EmpleadoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.DummyInvocationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Validated
@RestController
@RequestMapping("api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> getAll(){
        List<Empleado> lista = empleadoService.findAll();
        return ResponseEntity.ok(lista);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getById(@PathVariable
        @Min(value=0, message = "El valor debe ser mayor a cero")

        Long id) throws EmpleadoNotFoundException  {
        Empleado empleado=  empleadoService.findById(id)
                .orElseThrow( ()-> new EmpleadoNotFoundException(id));
        return  ResponseEntity.ok(empleado);
    }

    @GetMapping("/links/{id}")
    public ResponseEntity<EntityModel< Empleado>> getByIdConLinks(@PathVariable
                                            @Min(value=0, message = "El valor debe ser mayor a cero")
                                            Long id){
        Empleado empleado=  empleadoService.findById(id)
                .orElse( null);

        EntityModel<Empleado> empleadoModel=EntityModel.of(
                empleado,linkTo(methodOn( EmpleadoController.class ).getByIdConLinks(id)).withSelfRel()
        );
        return  ResponseEntity.ok(empleadoModel);
    }

    @GetMapping("/links")
    public ResponseEntity<CollectionModel<EntityModel<Empleado>>> getAllLinks(){
        List<Empleado> lista = empleadoService.findAll();

        List<EntityModel<Empleado>> listaConLinks=
                lista.stream()
                        .map(empleado -> EntityModel.of(empleado,
              linkTo(methodOn(EmpleadoController.class)
              .getByIdConLinks(empleado.getId())).withSelfRel()))
                        .collect(Collectors.toList());

        return ResponseEntity.ok( CollectionModel.of(listaConLinks, linkTo(methodOn(EmpleadoController.class).getAllLinks()).withSelfRel()));

    }



    @PostMapping
    public ResponseEntity<Empleado>  createEmpleado(@RequestBody
                                        @Valid Empleado empleado)
    {
         Empleado empleadoCreado= empleadoService.save(empleado);
         return  ResponseEntity.status(HttpStatus.CREATED).body(empleadoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable("id")
                   @Min(value=0, message = "El valor debe ser mayor a cero")
                   Long id ,
                   @RequestBody @Valid Empleado empleado)
                    throws  EmpleadoNotFoundException
    {
             return ResponseEntity.ok( empleadoService.update(empleado, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmpleado (@PathVariable("id")
                                          @Min(value=0, message = "El valor debe ser mayor a cero")
                                          Long id ) throws  EmpleadoNotFoundException{
        empleadoService.deleteById(id);
        return ResponseEntity.ok("Empleado borrado");
    }


    @ExceptionHandler(EmpleadoNotFoundException.class)
    public ResponseEntity<String> handleEmpleadoException(EmpleadoNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
