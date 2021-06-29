package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	public ResponseEntity<Usuario> cadastrarUsuario(Usuario novoUsuario) {
		if(verificarUsuarioExistente(novoUsuario)) {
			return ResponseEntity.status(204).build();
		}else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaEncoder = encoder.encode(novoUsuario.getSenhaUsuario());
			novoUsuario.setSenhaUsuario(senhaEncoder);
			repository.save(novoUsuario);
			return ResponseEntity.status(201).body(novoUsuario);
		}
	}
	
	public ResponseEntity<Usuario> alterarUsuario(Usuario usuarioAtual){
		Optional<Usuario> usuarioExistente = repository.findById(usuarioAtual.getIdUsuario());
		if(usuarioExistente.isPresent()) {
			if(!verificarUsuarioExistente(usuarioAtual)) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String senhaEncoder = encoder.encode(usuarioAtual.getSenhaUsuario());
				usuarioAtual.setSenhaUsuario(senhaEncoder);
				repository.save(usuarioAtual);
				return ResponseEntity.status(200).body(usuarioAtual);
			}
		}
		return ResponseEntity.status(204).build();
	}
	
	private boolean verificarUsuarioExistente(Usuario usuario) {
		Optional<Usuario> usuarioExistente = repository.findByLoginUsuario(usuario.getLoginUsuario());
		if(usuarioExistente.isPresent()) return true;
		else return false;
	}
	
	public ResponseEntity<Optional<UserLogin>> logarUsuario(Optional<UserLogin> userLogado){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByLoginUsuario(userLogado.get().getLoginUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(userLogado.get().getSenhaUsuario(), usuario.get().getSenhaUsuario())) {
				String auth = userLogado.get().getLoginUsuario() + ":" + userLogado.get().getSenhaUsuario();
				byte[] encoderAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encoderAuth);
				
				userLogado.get().setTokenUsuario(authHeader);
				userLogado.get().setIdUsuario(usuario.get().getIdUsuario());
				userLogado.get().setLoginUsuario(usuario.get().getLoginUsuario());
				userLogado.get().setNomeUsuario(usuario.get().getNomeUsuario());
				userLogado.get().setFotoUsuario(usuario.get().getFotoUsuario());
				userLogado.get().setTipoUsuario(usuario.get().getTipoUsuario());
				
				
				return ResponseEntity.status(200).body(userLogado);
			}
		}
		return ResponseEntity.status(401).build();
	}
	
	public ResponseEntity<List<Usuario>> encontrarTodosUsuarios(){
		List<Usuario> listaUsuarios = repository.findAll();
		if(listaUsuarios.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(listaUsuarios);
		}
	}
	
	public ResponseEntity<List<Usuario>> encontrarUsuariosNome(String nomeUsuario) {
		return repository.findByNomeUsuarioContaining(nomeUsuario)
				.map(usuarioExistente -> ResponseEntity.status(200).body(usuarioExistente))
				.orElse(ResponseEntity.status(204).build());
	}
	
	public ResponseEntity<String> deletarUsuario(String loginUsuario){
		Optional<Usuario> usuarioExistente = repository.findByLoginUsuario(loginUsuario);
		if(usuarioExistente.isPresent()) {
			repository.deleteById(usuarioExistente.get().getIdUsuario());
			return ResponseEntity.status(200).body("Usuario " + usuarioExistente.get().getLoginUsuario() + " removido");
		}else {
			return ResponseEntity.status(404).build();
		}
		
	}
}
