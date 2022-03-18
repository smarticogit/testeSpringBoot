package br.com.api.testeSpringBoot.DAO;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.api.testeSpringBoot.DTO.UsuarioDTO;
import br.com.api.testeSpringBoot.model.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer> { // Usuario e a chave primaria da tabela;
	
	@Query("SELECT new "
			+ "br.com.api.testeSpringBoot.DTO.UsuarioDTO("
			+ "username, email)"
			+ "FROM Usuario"
			)
	public  List<UsuarioDTO> recuperarUsername();
}