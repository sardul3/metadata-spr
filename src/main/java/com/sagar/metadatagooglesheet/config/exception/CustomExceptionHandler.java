//package com.sagar.metadatagooglesheet.config.exception;
//
//import com.sagar.metadatagooglesheet.dto.ErrorMessage;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import javax.persistence.EntityNotFoundException;
//import java.sql.SQLIntegrityConstraintViolationException;
//import java.time.Instant;
//
//@ControllerAdvice
//@Slf4j
//public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//    public ResponseEntity<Object> handleDuplicateUsername(SQLIntegrityConstraintViolationException ex, WebRequest request) {
//        ErrorMessage errorMessage = new ErrorMessage("Username is not available", Instant.now());
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
//    }
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<Object> handleDuplicateUser(SQLIntegrityConstraintViolationException ex, WebRequest request) {
//        ErrorMessage errorMessage = new ErrorMessage("Username is not available", Instant.now());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//    }
//
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("sdsdds dfjjd", Instant.now()));
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Error occured", Instant.now()));
//    }
//
//
//
//
//    }
