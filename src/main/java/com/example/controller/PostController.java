package com.example.controller;

import com.example.payload.PostDto;
import com.example.service.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/practice")
@AllArgsConstructor
public class PostController {
    private PostService postService;
    //http://localhost:8080/api/practice/dataAdded
    @PostMapping("/dataAdded")
    public ResponseEntity<String> addData(@RequestBody @Valid PostDto dto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.NOT_FOUND);
        }
        postService.addData(dto);
        return new ResponseEntity<>("Data is added", HttpStatus.CREATED);

    }
    //http:localhost:8080/api/practice?pageNo=1&pageSize=3&sortBy=name&sortDir=asc
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllData(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false)int pageno,
            @RequestParam(name="pageSize",defaultValue ="2",required=false)int pagesize,
            @RequestParam(name="sortBy",defaultValue = "id",required = false)String sortby,
            @RequestParam(name="sortDir",defaultValue = "desc",required = false)String sortdir
    ){
        List<PostDto> allData = postService.getAllData(pageno,pagesize,sortby,sortdir);
        return new ResponseEntity<>(allData,HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
        PostDto dataById = postService.getDataById(id);
        return new ResponseEntity<>(dataById,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
public ResponseEntity<String> deletePost(@PathVariable long id){
   postService.deleteDataById(id);
   return new ResponseEntity<>("delete is deleted",HttpStatus.OK);
}

}
