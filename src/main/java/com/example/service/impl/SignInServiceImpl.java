package com.example.service.impl;

import com.example.entity.SignIn;
import com.example.payload.SignInDto;
import com.example.repository.SignRepository;
import com.example.service.SignInService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignInServiceImpl implements SignInService {
    private SignRepository signRepository;
    private ModelMapper modelMapper;
    @Override
    public SignInDto addData(SignInDto dto) {
        SignIn sign=new SignIn();
        sign.setEmail(dto.getEmail());
        sign.setPassword(dto.getPassword());
        SignIn saved = signRepository.save(sign);
        dto.setId(saved.getId());
        dto.setEmail(saved.getEmail());
        dto.setPassword(saved.getPassword());
        //SignInDto signInDto = maptoDto(saved);
        return dto;
    }
    SignInDto maptoDto(SignIn sign){
        SignInDto dtos = modelMapper.map(sign, SignInDto.class);
        return dtos;
    }

}
