package tamasia.blog.api.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "tb_comentario")
public class ComentarioModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JdbcTypeCode(SqlTypes.BINARY)
	private UUID id;
	
	@Column(nullable = false)
	private LocalDate date;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String comentario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private PostModel post;
	
	public ComentarioModel() {
		
	}

	public ComentarioModel(String comentario, PostModel post) {
		this.comentario = comentario;
		this.post = post;
		this.date = LocalDate.now();
	}
	
	public UUID getId() {return id;}
	public void setId(UUID id) {this.id = id;}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public PostModel getPost() {
		return post;
	}

	public void setPost(PostModel post) {
		this.post = post;
	}
	
	
	

}