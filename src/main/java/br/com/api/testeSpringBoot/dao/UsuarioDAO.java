package br.com.api.testeSpringBoot.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.api.testeSpringBoot.model.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer> { // Usuario e a chave primaria da tabela;
	
}
