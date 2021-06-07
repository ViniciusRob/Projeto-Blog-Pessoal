package br.org.generation.blogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogPessoal.model.Tema;
import br.org.generation.blogPessoal.repository.TemaRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/temas")
public class TemaController {
	
	@Autowired
	private TemaRepository repository;
	
	//Consultar Todos os Temas
	@GetMapping
	public ResponseEntity<List<Tema>> findAllTema(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	//Consultar Tema por ID
	@GetMapping("/{id}")
	public ResponseEntity<Tema> findByIdTema(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
									  .orElse(ResponseEntity.notFound().build());
	}
	
	//Consultar Tema por Descricao
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> findByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	//Cadastrar Tema
	@PostMapping
	public ResponseEntity<Tema> postTema(@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
		
	//Editar Tema
	@PutMapping
	public ResponseEntity<Tema> putTema(@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(tema));
	}
		
	//Excluir Tema
	@DeleteMapping("/{id}")
	public void deleteTema(@PathVariable long id) {
		repository.deleteById(id);
	}
}
