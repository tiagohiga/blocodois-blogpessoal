package org.generation.blogPessoal.service;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TemaService {
	@Autowired
	private TemaRepository repositoryT;
	
	public ResponseEntity<List<Tema>> encontrarTodosTema(){
		List<Tema> listaTemas = repositoryT.findAll();
		if(listaTemas.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(listaTemas);
		}
	}
	
	public ResponseEntity<Tema> encontrarTemaId(Long idTema){
		return repositoryT.findById(idTema)
				.map(temaExistente -> ResponseEntity.status(200).body(temaExistente))
				.orElse(ResponseEntity.status(204).build());
	}
	
	public ResponseEntity<List<Tema>> encontrarTodosDescricao(String descricaoTema){
		List<Tema> listaTemas = repositoryT.findAllByDescricaoContainingIgnoreCase(descricaoTema);
		if(listaTemas.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(listaTemas);
		}
	}
	
	public ResponseEntity<Tema> cadastrarTema(Tema novoTema) {
		if (verificarTemaExistente(novoTema)) {
			return ResponseEntity.status(204).build();
		}else {
			repositoryT.save(novoTema);
			return ResponseEntity.status(201).body(novoTema);
		}
	}
	
	public ResponseEntity<Object> removerTema(Long idTema){
		Optional<Tema> temaExistente = repositoryT.findById(idTema);
		if(temaExistente.isPresent()) {
			repositoryT.deleteById(idTema);
			return ResponseEntity.status(200).body("Tema removido!");
		}else {
			return ResponseEntity.status(204).body("Tema não encontrado!");
		}
	}
	
	private boolean verificarTemaExistente(Tema verificaTema) {
		Optional<Tema> temaExistente = repositoryT.findByDescricao(verificaTema.getDescricao());
		if(temaExistente.isPresent()) return true;
		else return false;
	}
}
