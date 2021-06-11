package org.generation.blogPessoal.testeRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioRepositoryTeste {
	
	@Autowired
	private UsuarioRepository repository;
	
	Usuario usuario1;
	
	@BeforeAll
	void start() {
		usuario1 = new Usuario("Teste 1", "Teste1", "12345678");
		repository.save(usuario1);
		
	}
	
	@Test
	public void findByLoginUsuarioRetornaLoginUsuario() throws Exception{
		Usuario user = repository.findByLoginUsuario("Teste1").get();
		assertTrue(user.getLoginUsuario().equals(usuario1.getLoginUsuario()));
	}
	
	@AfterAll
	public void end() {
		repository.deleteAll();
	}
	
}
