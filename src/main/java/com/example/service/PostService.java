package com.example.service;

import com.example.payload.PostDto;

import java.util.List;

public interface PostService {

    public void addData(PostDto dto);

    List<PostDto> getAllData(int pageno, int pagesize, String sortby, String sortdir);

    PostDto getDataById(long id);

    void deleteDataById(long id);

}
