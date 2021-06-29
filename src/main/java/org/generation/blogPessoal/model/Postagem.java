package org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Indica que a classe sera uma Entidade do JPA
@Entity
//Indica que essa Entidade, dentro do banco de dados, sera uma tabela
@Table(name = "tb_postagem")
public class Postagem {
	//Definindo id como uma PK
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//Determina que titulo nao pode ser nulo e o tamanho da String que pode receber
	@NotNull
	@Size(min = 5, max = 100)
	private String titulo;
	
	@NotNull
	@Size(min = 10, max = 500)
	private String texto;
	
	//Indica que estamos lidando com o tempo (e o tipo de tempo)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
	@JsonIgnoreProperties("temaPostagens")
	private Tema temaPostagem;
	
	@ManyToOne
	@JsonIgnoreProperties("listaPostagens")
	private Usuario usuarioPostagem;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Tema getTemaPostagem() {
		return temaPostagem;
	}
	public void setTemaPostagem(Tema temaPostagem) {
		this.temaPostagem = temaPostagem;
	}
	public Usuario getUsuarioPostagem() {
		return usuarioPostagem;
	}
	public void setUsuarioPostagem(Usuario usuarioPostagem) {
		this.usuarioPostagem = usuarioPostagem;
	}
}
