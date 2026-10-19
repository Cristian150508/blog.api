package tamasia.blog.api.mapper;

import org.springframework.stereotype.Component;

import tamasia.blog.api.dto.request.ComentarioRequestDto;
import tamasia.blog.api.dto.response.ComentarioResponse;
import tamasia.blog.api.model.ComentarioModel;
import tamasia.blog.api.model.PostModel;

@Component
public class ComentarioMapper {
	
	public ComentarioModel toEntity(ComentarioRequestDto dto, PostModel entity) {
		if (dto == null) {
			return null;
		}
		return new ComentarioModel(
				dto.comentario().toString(),
				entity
);
		}
		
		public static ComentarioResponse toDto(ComentarioModel entity) {
			if (entity == null) {
				return null;
			}	
			return new ComentarioResponse(entity.getId(),entity.getDate(),entity.getComentario());
		}
		}