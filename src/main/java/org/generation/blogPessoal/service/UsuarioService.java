package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario cadastrarUsuario(Usuario novoUsuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(novoUsuario.getSenhaUsuario());
		novoUsuario.setSenhaUsuario(senhaEncoder);
		
		return repository.save(novoUsuario);
	}
	
	public Optional<UserLogin> logar(Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByLoginUsuario(user.get().getLoginUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenhaUsuario(), usuario.get().getSenhaUsuario())) {
				String auth = user.get().getLoginUsuario() + ":" + user.get().getSenhaUsuario();
				byte[] encoderAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encoderAuth);
				
				user.get().setTokenUsuario(authHeader);
				user.get().setNomeUsuario(usuario.get().getNomeUsuario());
				
				return user;
			}
		}
		return null;
	}
}
