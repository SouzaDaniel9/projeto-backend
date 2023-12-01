package com.daniel.backendCompleto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.backendCompleto.model.Produto;
import com.daniel.backendCompleto.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	// Obtém a lista inteira de produtos
	public List<Produto> obterTodosProdutos() {
		return produtoRepository.findAll();
	}

	// Obtém o produto pelo ID
	public Optional<Produto> obterPorId(Integer id) {
		return produtoRepository.findById(id);
	}

	// Adiciona um novo produto na lista
	public Produto adicionar(Produto p) {
		return produtoRepository.save(p);
	}

	// Remove usando o id do produto
	public void deletar(Integer id) {
		produtoRepository.deleteById(id);
	}

	public Produto atualizar(Integer id, Produto p) {
		p.setId(id);
		return produtoRepository.save(p);
	}
}
