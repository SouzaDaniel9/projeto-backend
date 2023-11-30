package com.daniel.backendCompleto.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.daniel.backendCompleto.model.Produto;

@Repository
public class ProdutoRepository {

	private List<Produto> produtos = new ArrayList<>();
	private Integer ultimoId = 0;

	// Obtém a lista inteira de produtos
	public List<Produto> obterTodosProdutos() {
		return produtos;
	}

	// Obtém o produto pelo ID
	public Optional<Produto> obterPorId(Integer id) {
		return produtos.stream().filter(p -> p.getId() == id).findFirst();
	}

	// Adiciona um novo produto na lista
	public Produto adicionar(Produto p) {
		ultimoId++;
		p.setId(ultimoId);
		produtos.add(p);
		return p;
	}

	// Remove usando o id do produto
	public void deletar(Integer id) {
		produtos.removeIf(p -> p.getId() == id);
	}

	public Produto atualizar(Produto p) {
		Optional<Produto> produtoEncontrado = obterPorId(p.getId());

		if (produtoEncontrado.isEmpty()) {
			throw new InputMismatchException("Produto não encontrado");
		}
		deletar(p.getId());
		produtos.add(p);
		return p;
	}
}
