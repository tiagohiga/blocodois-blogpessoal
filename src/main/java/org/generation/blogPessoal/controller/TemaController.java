package org.generation.blogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.generation.blogPessoal.service.TemaService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/temas")
public class TemaController {
	@Autowired
	private TemaRepository repository;
	
	@Autowired
	private TemaService servicesTema;
	
	@GetMapping
	public ResponseEntity<List<Tema>> getAll(){
		return servicesTema.encontrarTodosTema();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable long idTema){
		return servicesTema.encontrarTemaId(idTema);
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> getByName(@PathVariable String nome){
		return servicesTema.encontrarTodosDescricao(nome);
	}
	
	@PostMapping
	public ResponseEntity<Tema> criarTema(@Valid @RequestBody Tema novoTema){
		return servicesTema.cadastrarTema(novoTema);
	}
	
	@PutMapping("/editar")
	public ResponseEntity<Tema> editarTema(@Valid @RequestBody Tema temaModificado){
		return servicesTema.cadastrarTema(temaModificado);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Object> deletarTema(@PathVariable long idTema) {
		return servicesTema.removerTema(idTema);
	}
}
