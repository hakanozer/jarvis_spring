package com.works.configs;

import com.works.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValid( MethodArgumentNotValidException ex ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, false);
        hm.put(REnum.errors, parseErrors(ex.getFieldErrors()) );
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity httpMessageNotReadable( HttpMessageNotReadableException ex) {
        String message = ex.getCause().getMessage();
        message = message.replaceAll("com.works", "" );
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, false);
        hm.put(REnum.message, message);
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity httpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<String> ls = new ArrayList<>();
        hm.put(REnum.status, false);
        ls.add( ex.getMessage() );
        hm.put(REnum.errors, ls);
        return new ResponseEntity(hm, HttpStatus.METHOD_NOT_ALLOWED);
    }

    private List parseErrors(List<FieldError> fieldErrors) {
        List<Map<String, String>> ls = new ArrayList<>();
        for ( FieldError item : fieldErrors ) {
            Map<String, String > hm = new HashMap<>();
            String field = item.getField();
            String message = item.getDefaultMessage();
            hm.put("field", field);
            hm.put("message", message);
            ls.add(hm);
        }
        return ls;
    }

}
