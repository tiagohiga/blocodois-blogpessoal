package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	@Autowired
	private UsuarioService services;
	
	@Autowired
	private UsuarioRepository repository;
	
	@PostMapping("/logar")
	public ResponseEntity<Optional<UserLogin>> authentication(@Valid @RequestBody Optional<UserLogin> userLogado){
		return services.logarUsuario(userLogado);
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> cadastrarNovoUsuario(@Valid @RequestBody Usuario novoUsuario){
		return services.cadastrarUsuario(novoUsuario);
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<Usuario> alterarCadastro(@Valid @RequestBody Usuario usuarioAtual){
		return services.alterarUsuario(usuarioAtual);
	}
	
	@GetMapping("/todos")
	public ResponseEntity<List<Usuario>> findAll(){
		return services.encontrarTodosUsuarios();
	}
	
	@GetMapping("/nome")
	public ResponseEntity<List<Usuario>> findByNome(@RequestParam(name = "usuario", defaultValue = "") String nomeUsuario){
		return services.encontrarUsuariosNome(nomeUsuario);
	}
	
	@DeleteMapping("/deletar/{login_usuario}")
	public ResponseEntity<String> deleteByLogin(@PathVariable(name = "login_usuario") String loginUsuario){
		return services.deletarUsuario(loginUsuario);
	}
}
