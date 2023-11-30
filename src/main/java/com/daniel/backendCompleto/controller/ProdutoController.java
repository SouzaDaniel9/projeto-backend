package com.daniel.backendCompleto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.backendCompleto.model.Produto;
import com.daniel.backendCompleto.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public List<Produto> obterTodos() {
		return produtoService.obterTodosProdutos();
	}

	@GetMapping("/{id}")
	public Optional<Produto> obterPorId(@PathVariable Integer id) {
		return produtoService.obterPorId(id);
	}

	@PostMapping
	public Produto novoProduto(@RequestBody Produto p) {
		return produtoService.adicionar(p);
	}

	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Integer id) {
		produtoService.deletar(id);
		return "Produto com id " + id + " deletado com sucesso.";
	}
	
	@PutMapping("/{id}")
	public Produto atualizar(@PathVariable Integer id, @RequestBody Produto p) {
		return produtoService.atualizar(id, p);
	}
}
