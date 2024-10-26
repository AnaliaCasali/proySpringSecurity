package com.Alkemy.JavaMod21.security.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class BasicSecurityController {

    private static final Logger logger= LoggerFactory.getLogger(BasicSecurityController.class);

    @GetMapping("/mensaje")
    public ResponseEntity<?> home() {
        var auth= SecurityContextHolder.getContext().getAuthentication();
        logger.info("Datos del usuario:{} ", auth.getPrincipal());
        logger.info("Datos de los roles:{} ", auth.getAuthorities());
        logger.info("Esta Autenticado:{} ", auth.isAuthenticated());

        Map<String, String> mensaje= new HashMap<>();
        mensaje.put("contenido", " aprendiendo Java Security...");
        mensaje.put("user",auth.getPrincipal().toString());

        return ResponseEntity.of(Optional.of(mensaje));
    }

}
