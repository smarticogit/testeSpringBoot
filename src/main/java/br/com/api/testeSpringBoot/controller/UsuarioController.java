package br.com.api.testeSpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.testeSpringBoot.DAO.UsuarioDAO;
import br.com.api.testeSpringBoot.DTO.UsuarioDTO;
import br.com.api.testeSpringBoot.model.Usuario;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioDAO dao;
	
	@GetMapping("/usuarios/username")
	public ResponseEntity <List<UsuarioDTO>> exibirUsername() {
		List<UsuarioDTO> res = dao.recuperarUsername();
		return ResponseEntity.status(200).body(res);
	}
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> mostrarTodosUsuario() {
		List<Usuario> res = (List<Usuario>) dao.findAll();

		if (res != null) {
			return ResponseEntity.status(200).body(res);
		}
		return ResponseEntity.status(404).build();
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> mostrarUmUsuario(@PathVariable Integer id) {
		Usuario res =  dao.findById(id).orElse(null);

		if (res != null) {
			return ResponseEntity.status(200).body(res);
		}
		return ResponseEntity.status(404).build();
	}

	@PostMapping("/usuarios")
	public ResponseEntity<?> inserirNovoUsuario(@RequestBody Usuario novoUsuario) {
		String nome = novoUsuario.getNomeCompleto();
		if (nome == null) {
			return ResponseEntity.badRequest().body("The field nomeCompleto is obrigatory");
		}
		try {
			Usuario res = dao.save(novoUsuario);
			return ResponseEntity.status(201).body(res);
		} catch (Exception ex) {
			return ResponseEntity.status(400).body(ex.getMessage());
		}
	}

	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> alterarUsuario(@RequestBody Usuario usuario, @PathVariable Integer id) {
		Usuario usr = (Usuario) dao.findById(id).orElse(null);
		if (usr != null) {
			try {
				usuario.setId(id);
				Usuario res = (Usuario) dao.save(usuario);
				return ResponseEntity.status(200).body(res);
			} catch (Exception ex) {
				return ResponseEntity.status(400).body(ex.getMessage());
			}
		}
		return ResponseEntity.status(404).build();
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable Integer id) {
		Usuario usr = (Usuario) dao.findById(id).orElse(null);
		if (usr != null) {
			try {
				dao.deleteById(id);
				return ResponseEntity.status(204).build();
			} catch (Exception ex) {
				return ResponseEntity.status(400).body(ex.getMessage());
			}
		}
		return ResponseEntity.notFound().build();
	}
}
