package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/example")
public class ExampleControler {
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getSomething(){
        return new ResponseEntity<>("this is ok", HttpStatus.OK);
    }
}
