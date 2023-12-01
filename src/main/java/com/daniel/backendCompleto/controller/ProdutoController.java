package com.daniel.backendCompleto.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.backendCompleto.dto.ProdutoDTO;
import com.daniel.backendCompleto.model.Produto;
import com.daniel.backendCompleto.service.ProdutoService;
import com.daniel.backendCompleto.view.ProdutoResponse;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<ProdutoResponse>> obterTodos() {
		List<ProdutoDTO> produtoDTO = produtoService.obterTodosProdutos();

		ModelMapper mp = new ModelMapper();

		List<ProdutoResponse> resposta = produtoDTO.stream()
				.map(produtoDto -> mp.map(produtoDto, ProdutoResponse.class)).collect(Collectors.toList());

//		return new ResponseEntity<>(resposta, HttpStatus.OK);
		return ResponseEntity.ok().body(resposta);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<ProdutoResponse>> obterPorId(@PathVariable Integer id) {
		try {

			Optional<ProdutoDTO> dto = produtoService.obterPorId(id);
			ProdutoResponse produto = new ModelMapper().map(dto.get(), ProdutoResponse.class);
			return ResponseEntity.ok().body(Optional.of(produto));

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
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
