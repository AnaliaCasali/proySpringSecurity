package com.Alkemy.JavaMod21.exception;

public class EmpleadoNotFoundException  extends Exception {

        public  EmpleadoNotFoundException(Long id){
            super("El empleado " + id + " no se encontro" );
        }

}
