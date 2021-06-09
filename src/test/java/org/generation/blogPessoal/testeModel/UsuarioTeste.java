package org.generation.blogPessoal.testeModel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UsuarioTeste {

	public Usuario novoUsuario;
	
	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	@BeforeEach
	public void start() {
		novoUsuario = new Usuario("Teste Nome", "Teste Usuario", "Teste Senha");
		//novoUsuario = new Usuario();
	}
	
	@Disabled
	@Test
	void testeValidaAtributos() {
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(novoUsuario);
		System.out.println("Violacao é: " + violacao.toString());
		assertTrue(violacao.isEmpty());
	}
	
	@Test
	void testeValidaAtributosNulos() {
		Usuario erroUsuario = new Usuario();
		erroUsuario.setNomeUsuario("Nome do Usuário");
		erroUsuario.setLoginUsuario("Login do Usuário");
		erroUsuario.setSenhaUsuario("Senha do Usuário");
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(erroUsuario);
		System.out.println("Violacao é: " + violacao.toString());
		assertFalse(violacao.isEmpty());
	}
}
