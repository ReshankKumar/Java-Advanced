package com.example.Exception;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String str){
        super(str);
    }
}
