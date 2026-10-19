package tamasia.blog.api.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import tamasia.blog.api.model.PostModel;
import tamasia.blog.api.repository.PostRepository;

@DataJpaTest
public class PostRepositoryTest {


	@Autowired
	PostRepository repository;
	
	@Test
	void deveSalvarERestrnarPost(){
		
		//Arrange
		PostModel post = new PostModel("Yuri", "+DevsToBlu", "Curso mais top do mundo");
		
		//Act
		PostModel postSalvo = repository.save(post);
		
		//Assert
		Assertions.assertThat(postSalvo).isNotNull();
		
	}
	
}