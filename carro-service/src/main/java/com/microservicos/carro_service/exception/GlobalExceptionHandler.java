package com.microservicos.carro_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNotFoundRoute(NoHandlerFoundException ex){
        return ResponseEntity.badRequest()
                .body(Map.of("message", "Ruta no encontrada",
                        "path", ex.getRequestURL()));
    }

}
