package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepository<Tipo de Entidade, Tipo da PK da Entidade>
@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	//Titulo = nome do atributo da Entity
	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);
	
}
