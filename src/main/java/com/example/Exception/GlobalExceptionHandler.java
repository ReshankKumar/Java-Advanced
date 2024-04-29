package com.example.Exception;

import com.example.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.crypto.Data;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//@ExceptionHandler(ResourceNotFound.class)
//    public ResponseEntity<ErrorDetails> resourceNotFound(
//            ResourceNotFound rs, WebRequest webRequest
//    ){
//      ErrorDetails details=new ErrorDetails(new Date(),rs.getMessage(),webRequest.getDescription(false));
//
//        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
//
//    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalException(
            Exception e,WebRequest webRequest
    ){
      ErrorDetails details=new ErrorDetails(new Date(),e.getMessage(),webRequest.getDescription(true));
      return new ResponseEntity<>(details,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
