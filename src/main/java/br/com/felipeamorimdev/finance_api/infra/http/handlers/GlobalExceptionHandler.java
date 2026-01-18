package br.com.felipeamorimdev.finance_api.infra.http.handlers;

import br.com.felipeamorimdev.finance_api.infra.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if ("Access denied".equals(ex.getMessage())) {
            status = HttpStatus.FORBIDDEN;
        } else if ("Account not found".equals(ex.getMessage())) {
            status = HttpStatus.NOT_FOUND;
        }
        
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> fields = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        fields.put(error.getField(), error.getDefaultMessage())
                );

        Map<String, Object> body = Map.of(
                "status", 400,
                "error", "Bad Request",
                "message", "Erro de validação",
                "fields", fields
        );

        return ResponseEntity.badRequest().body(body);
    }

}
