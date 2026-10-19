package tamasia.blog.api.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tamasia.blog.api.dto.request.ComentarioRequestDto;
import tamasia.blog.api.dto.request.PostRequestDto;
import tamasia.blog.api.dto.response.ComentarioResponse;
import tamasia.blog.api.dto.response.PostResponseDto;

public interface PostService {

Page<PostResponseDto> findAll(Pageable pageable);

Page<PostResponseDto> findAll(Pageable pageable, String titulo);

PostResponseDto findById(UUID id);

PostResponseDto createPost (PostRequestDto dto);

ComentarioResponse addComentario(UUID postId, ComentarioRequestDto dto);

PostResponseDto updatePost(UUID id, PostRequestDto dto);

void deletePost(UUID id);

ComentarioResponse updateComentario(
        UUID id, ComentarioRequestDto dto);
void deleteComentario(UUID id);


}