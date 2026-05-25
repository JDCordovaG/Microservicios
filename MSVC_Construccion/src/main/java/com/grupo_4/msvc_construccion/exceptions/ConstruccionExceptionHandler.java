package com.grupo_4.msvc_construccion.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ConstruccionExceptionHandler {

    @ExceptionHandler(ConstruccionException.class)
    public ResponseEntity<Map<String, String>> handleConstruccionException(ConstruccionException ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("error", "Validación fallida");
        respuesta.put("mensaje", ex.getMessage());

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

}
