package org.generation.blogPessoal.testeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TemaRepositoryTeste {

	@Autowired
	private TemaRepository repository;
	
	@BeforeAll
	void start() {
		Tema tema1 = new Tema("descricao longa mesmo");
		repository.save(tema1);
		
		Tema tema2 = new Tema("talvez nao vai");
		repository.save(tema2);
		
		Tema tema3 = new Tema("nao vai");
		repository.save(tema3);
	}
	
	@Test
	public void findAllByDescricaoContainingRetornaDescricao() {
		List<Tema> listaContendo = repository.findAllByDescricaoContainingIgnoreCase("nao vai");
		assertEquals(2, listaContendo.size());
	}
	

	@AfterAll
	public void end() {
		repository.deleteAll();
	}

}
