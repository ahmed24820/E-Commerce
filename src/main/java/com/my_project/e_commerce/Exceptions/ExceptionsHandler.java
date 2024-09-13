package com.my_project.e_commerce.Exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionsHandler {

  @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ErrorResponse> NullPointerException(NullPointerException exception){
    ErrorResponse response = new ErrorResponse(exception.getMessage(), List.of(exception.getMessage()));

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response);
  }
    @ExceptionHandler(value = DuplicatedError.class)
    public ResponseEntity<ErrorResponse> DuplicatedException(DuplicatedError exception){
        ErrorResponse response = new ErrorResponse(exception.getMessage(), List.of(exception.getMessage()));

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ErrorResponse>NoSuchElement(NoSuchElements ex){
      ErrorResponse response = new ErrorResponse(ex.getMessage(),List.of(ex.getMessage()));
      return ResponseEntity
              .status(HttpStatus.NOT_FOUND)
              .body(response);
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> JwtException(JWTExpiration exception){
        ErrorResponse response = new ErrorResponse(exception.getMessage(), List.of(exception.getMessage()));

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    @ExceptionHandler(value = HttpClientErrorException.NotFound.class)
    public ResponseEntity<ErrorResponse> NotFoundException(NotFounded exception){
        ErrorResponse response = new ErrorResponse(exception.getMessage(), List.of(exception.getMessage()));

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }


}
