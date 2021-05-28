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

import br.org.generation.blogPessoal.model.Postagem;
import br.org.generation.blogPessoal.repository.PostagemRepository;
import net.bytebuddy.implementation.bytecode.assign.primitive.VoidAwareAssigner;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;
	
	//Consultar Todas as Postagens
	@GetMapping("")
	public ResponseEntity<List<Postagem>> findAllPostagem(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	//Consultar Postagem por ID
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> findByIdPostagem(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				                      .orElse(ResponseEntity.notFound().build()) ;
	}
	
	//Consultar Postagem por Título
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> findByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	//Cadastrar Postagem
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	//Editar Postagem
	@PutMapping
	public ResponseEntity<Postagem> putPostagem(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	//Excluir Postagem
	@DeleteMapping("/{id}")
	public void deletePostagem(@PathVariable long id) {
		repository.deleteById(id);
	}
}
