package com.example.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
    private long id;
    @NotEmpty(message = "title must not be null")
    @Size(min = 4,message = "title should be atleast 4 character")
    private String title;

    private String content;
}
