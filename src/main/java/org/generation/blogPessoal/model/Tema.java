package org.generation.blogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_tema")
public class Tema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTema;
	
	@NotNull
	private String descricao;
	
	//CascadeType.ALL = persistencia propagara todas as operacoes 
	//(PERSIST, REMOVE, REFRESH, MERGE, DETACH) para as entidades relacionadas
	@OneToMany(mappedBy = "temaPostagem", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("temaPostagem")
	private List<Postagem> temaPostagens;

	public Tema(@NotNull String descricao) {
		super();
		this.descricao = descricao;
	}
	
	public Tema() {

	}

	public long getIdTema() {
		return idTema;
	}

	public void setIdTema(long idTema) {
		this.idTema = idTema;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getTemaPostagens() {
		return temaPostagens;
	}

	public void setTemaPostagens(List<Postagem> temaPostagens) {
		this.temaPostagens = temaPostagens;
	}
}
