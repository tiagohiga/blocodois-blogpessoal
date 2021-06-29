package org.generation.blogPessoal.service;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.generation.blogPessoal.repository.TemaRepository;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostagemService {
	@Autowired
	private PostagemRepository repositoryP;
	
	@Autowired
	private UsuarioRepository repositoryU;
	
	@Autowired
	private TemaRepository repositoryT;
	
	public ResponseEntity<List<Postagem>> encontrarTodasPostagens(){
		List<Postagem> listaPostagens = repositoryP.findAll();
		if(listaPostagens.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(listaPostagens);
		}
	}
	
	public ResponseEntity<Postagem> encontrarPostagemId(Long idPostagem){
		return repositoryP.findById(idPostagem)
				.map(postagemExistente -> ResponseEntity.status(200).body(postagemExistente))
				.orElse(ResponseEntity.status(204).build());
	}
	
	public ResponseEntity<List<Postagem>> encontrarTodasTitulo(String tituloPostagem){
		List<Postagem> listaPostagens = repositoryP.findAllByTituloContainingIgnoreCase(tituloPostagem);
		if(listaPostagens.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(listaPostagens);
		}
	}
	
	public ResponseEntity<Postagem> novaPostagem(Postagem novaPostagem, String loginUsuario, String temaPostagem){
		Optional<Usuario> usuarioExistente = repositoryU.findByLoginUsuario(loginUsuario);
		Optional<Tema> temaExistente = repositoryT.findByDescricao(temaPostagem);
		if(usuarioExistente.isPresent() && temaExistente.isPresent()) {
			novaPostagem.setUsuarioPostagem(usuarioExistente.get());
			novaPostagem.setTemaPostagem(temaExistente.get());
			repositoryP.save(novaPostagem);
			return ResponseEntity.status(201).body(novaPostagem);
		}else {
			return ResponseEntity.status(204).build();
		}
	}
	
	public ResponseEntity<Postagem> modificarPostagem(Postagem postagemModificada){
		Optional<Postagem> postagemExistente = repositoryP.findById(postagemModificada.getId());
		if(postagemExistente.isPresent()) {
			repositoryP.save(postagemModificada);
			return ResponseEntity.status(200).body(postagemModificada);
		}else {
			return ResponseEntity.status(204).build();
		}
	}
	
	public ResponseEntity<Object> removerPostagem(Long idPostagem){
		Optional<Postagem> postagemExistente = repositoryP.findById(idPostagem);
		if(postagemExistente.isEmpty()) {
			return ResponseEntity.status(204).body("Usuário não encontrado!");
		}else {
			repositoryP.deleteById(idPostagem);
			return ResponseEntity.status(200).body("Usuário removido com sucesso!");
		}
	}
}
