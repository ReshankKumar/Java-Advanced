package com.example.service.impl;

import com.example.Exception.ResourceNotFound;
import com.example.entity.Post;
import com.example.payload.PostDto;
import com.example.repository.PostRepository;
import com.example.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private ModelMapper modelMapper;
    @Override
    public void addData(PostDto dto) {
        Post post=new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        postRepository.save(post);
    }

    @Override
    public List<PostDto> getAllData(int pageno, int pagesize, String sortby, String sortdir) {
        //creating a Sort object based on sortby and sortdir
        Sort sort = Sort.by(sortby);
        if(sortdir.equals("asc")){
            Sort ascending = sort.ascending();
        }
        else{
            Sort descending = sort.descending();
        }
        Pageable pageable = PageRequest.of(pageno, pagesize,sort);

        Page<Post> all = postRepository.findAll(pageable);
        List<Post> content = all.getContent();
        List<PostDto> dtos = content.stream().map(p -> maptoDto(p)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public PostDto getDataById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()->new ResourceNotFound("user id is not present :"+id)
        );
//        Post post = byId.get();
        PostDto postDto = maptoDto(post);
        return postDto;
    }

    @Override
    public void deleteDataById(long id) {
        if(postRepository.existsById(id)){
            postRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFound("id is not present:"+id);
        }
    }

    PostDto maptoDto(Post post){
        PostDto dto=modelMapper.map(post,PostDto.class);
        return dto;
    }
}
