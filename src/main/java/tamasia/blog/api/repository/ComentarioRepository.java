package tamasia.blog.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tamasia.blog.api.model.ComentarioModel;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioModel, UUID>{
}