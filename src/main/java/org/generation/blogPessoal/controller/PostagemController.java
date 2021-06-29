package org.generation.blogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.service.PostagemService;
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
//Informa qual URI acessara essa classe
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {
	//Permite acesso aos servicos de PostagemRepository
	@Autowired
	private PostagemService servicePostagem;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll(){
		return servicePostagem.encontrarTodasPostagens();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long idPostagem){
		return servicePostagem.encontrarPostagemId(idPostagem);
	}
	
	//Usar /{} duas vezes resulta em duplicidade de end-point
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		return servicePostagem.encontrarTodasTitulo(titulo);
	}
	
	@PostMapping("/postar")
	public ResponseEntity<Postagem> criarPostagem(@Valid @RequestBody Postagem postagem, 
			@RequestParam String loginUsuario, @RequestParam String descricaoPostagem){
		return servicePostagem.novaPostagem(postagem, loginUsuario, descricaoPostagem);
	}
	
	@PutMapping("/postar/editar")
	public ResponseEntity<Postagem> editarPostagem(@Valid @RequestBody Postagem postagemModificada){
		return servicePostagem.modificarPostagem(postagemModificada);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarPostagem(@PathVariable long idPostagem) {
		return servicePostagem.removerPostagem(idPostagem);
	}
}
