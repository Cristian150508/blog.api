package tamasia.blog.api.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import tamasia.blog.api.model.PostModel;
import tamasia.blog.api.repository.PostRepository;

@DataJpaTest
public class PostRepositoryTest {


	@Autowired
	private PostRepository repository;
	
	@Test
	void deveSalvarERestrnarPost(){
		
		//Arrange
		PostModel post = new PostModel("Cristian", "+DevsToBlu", "Curso mais top do mundo");
		
		//Act
		PostModel postSalvo = repository.save(post);
		
		//Assert
		Assertions.assertThat(postSalvo).isNotNull();
		
	}
	
	@Test 
	void deveListarPorTitulo () {
		
		//arrange
		PostModel post1 = new PostModel("Rennam", "Força bruta", "Espanca os amigos");
		PostModel post2 = new PostModel("Lima", "Força intelectual", "Belos argumentos");
		PostModel post3 = new PostModel("Coreia", "Força da fome", "Gastou dinheiro do pão com minecraft");
		PostModel post4 = new PostModel("Reges", "Olhos que tudo veem", "Espanta qualquer mulher");
		PostModel post5 = new PostModel("Pietro", "Escudo humano", "Apanha do Rennam e sobrevive");
		PostModel post6 = new PostModel("Fabio", "Animal domestico", "Pau mandado da Angeli");
		
		repository.save(post1);
		repository.save(post2);
		repository.save(post3);
		repository.save(post4);
		repository.save(post5);
		repository.save(post6);
		//act
		Page<PostModel> page = repository.findByTituloContainingIgnoreCase("Animal", PageRequest.of(0, 10));
		
		//assert
		Assertions.assertThat(page).hasSize(1);
		
	}
	
}