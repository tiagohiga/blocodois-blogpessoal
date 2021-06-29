package org.generation.blogPessoal.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	
	@Size(min = 3, max = 100)
	private String nomeUsuario;
	
	@NotNull
	@Size(min = 5, max = 100)
	private String loginUsuario;
	
	@NotNull
	@Size(min = 5)
	private String senhaUsuario;
	
	private String fotoUsuario;
	
	private String tipoUsuario;
	
	@OneToMany(mappedBy = "usuarioPostagem", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuarioPostagem")
	private List<Postagem> listaPostagens = new ArrayList<>();
	
	public Usuario(
			@NotNull @Size(min = 3, max = 100) String nomeUsuario,
			@NotNull @Size(min = 5, max = 100) String loginUsuario, 
			@NotNull @Size(min = 5) String senhaUsuario) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.loginUsuario = loginUsuario;
		this.senhaUsuario = senhaUsuario;
	}

	public Usuario() {
		
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public List<Postagem> getListaPostagens() {
		return listaPostagens;
	}

	public void setListaPostagens(List<Postagem> listaPostagens) {
		this.listaPostagens = listaPostagens;
	}

	public String getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
}
