package com.example.controller;

import com.example.entity.SignIn;
import com.example.payload.SignInDto;
import com.example.service.SignInService;
import com.example.service.impl.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sign")
@AllArgsConstructor
public class SignController {
   private SignInService signInService;
   private EmailService emailService;
   //http:localhost:8080/api/sign
   @PostMapping
    public ResponseEntity<SignInDto> addData(@RequestBody SignInDto dto){
        emailService.sendEmail(dto.getEmail(),"test","hello ayusi");
       SignInDto signInDto = signInService.addData(dto);
        return new ResponseEntity<>(signInDto, HttpStatus.CREATED);
    }


}
