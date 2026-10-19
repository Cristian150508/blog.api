package tamasia.blog.api.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tamasia.blog.api.dto.request.ComentarioRequestDto;
import tamasia.blog.api.dto.request.PostRequestDto;
import tamasia.blog.api.dto.response.ComentarioResponse;
import tamasia.blog.api.dto.response.PostResponseDto;
import tamasia.blog.api.exeption.RegistroNaoEncontradoException;
import tamasia.blog.api.mapper.ComentarioMapper;
import tamasia.blog.api.mapper.PostMapper;
import tamasia.blog.api.model.ComentarioModel;
import tamasia.blog.api.model.PostModel;
import tamasia.blog.api.repository.ComentarioRepository;
import tamasia.blog.api.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final PostMapper postMapper;
	private final ComentarioRepository comentarioRepository;
	private final ComentarioMapper comentarioMapper;
	private final MessageService messageService;

	public PostServiceImpl(PostRepository postRepository, PostMapper postMapper,
			ComentarioRepository comentarioRepository, ComentarioMapper comentarioMapper,
			MessageService messageService) {

		this.postRepository = postRepository;
		this.postMapper = postMapper;
		this.comentarioRepository = comentarioRepository;
		this.comentarioMapper = comentarioMapper;
		this.messageService = messageService;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PostResponseDto> findAll(Pageable pageable) {
		Page<PostModel> posts = postRepository.findAll(pageable);

		return posts.map(postMapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PostResponseDto> findAll(Pageable pageable, String titulo) {

		Page<PostModel> posts;

		if (titulo == null || titulo.isBlank()) {
			posts = postRepository.findAll(pageable);
		} else {
			posts = postRepository.findByTituloContainingIgnoreCase(titulo, pageable);
		}

		return posts.map(postMapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public PostResponseDto findById(UUID id) {
		PostModel post = postRepository.findById(id).orElseThrow(
				() -> new RegistroNaoEncontradoException(messageService.getMessage("post.notfound", id), id));

		return postMapper.toDto(post);
	}

	@Override
	@Transactional
	public PostResponseDto createPost(PostRequestDto dto) {
		PostModel post = postMapper.toEntity(dto);
		PostModel saved = postRepository.save(post);

		return postMapper.toDto(saved);
	}

	@Override
	@Transactional
	public ComentarioResponse addComentario(UUID postId, ComentarioRequestDto dto) {

		PostModel post = postRepository.findById(postId).orElseThrow(
				() -> new RegistroNaoEncontradoException(messageService.getMessage("post.notfound", postId), postId));

		ComentarioModel comentario = new ComentarioModel(dto.comentario(), post);

		post.adicionarComentario(comentario);

		ComentarioModel saved = comentarioRepository.save(comentario);

		return comentarioMapper.toDto(saved);
	}

	@Override
	@Transactional
	public PostResponseDto updatePost(UUID id, PostRequestDto dto) {

		PostModel post = postRepository.findById(id).orElseThrow(
				() -> new RegistroNaoEncontradoException(messageService.getMessage("post.notfound", id), id));

		post.setAutor(dto.autor());
		post.setTitulo(dto.titulo());
		post.setTexto(dto.texto());

		PostModel postAtualizado = postRepository.save(post);

		return postMapper.toDto(postAtualizado);
	}

	@Override
	@Transactional
	public void deletePost(UUID id) {
		PostModel post = postRepository.findById(id).orElseThrow(
				() -> new RegistroNaoEncontradoException(messageService.getMessage("post.notfound", id), id));

		postRepository.delete(post);
	}

	@Override
	@Transactional
	public ComentarioResponse updateComentario(UUID id, ComentarioRequestDto dto) {

		ComentarioModel comentario = comentarioRepository.findById(id).orElseThrow(
				() -> new RegistroNaoEncontradoException(messageService.getMessage("comentario.notfound", id), id));

		comentario.setComentario(dto.comentario());

		ComentarioModel comentarioAtualizado = comentarioRepository.save(comentario);

		return comentarioMapper.toDto(comentarioAtualizado);
	}

	@Override
	@Transactional
	public void deleteComentario(UUID id) {

		ComentarioModel comentario = comentarioRepository.findById(id).orElseThrow(
				() -> new RegistroNaoEncontradoException(messageService.getMessage("comentario.notfound", id), id));

		comentarioRepository.delete(comentario);
	}

}