package com.daniel.backendCompleto.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.backendCompleto.dto.ProdutoDTO;
import com.daniel.backendCompleto.exception.ResourceNotFoundException;
import com.daniel.backendCompleto.model.Produto;
import com.daniel.backendCompleto.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	// Obtém a lista inteira de produtos
	public List<ProdutoDTO> obterTodosProdutos() {

		List<Produto> produtos = produtoRepository.findAll();
		return produtos.stream().map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
				.collect(Collectors.toList());
	}

	// Obtém o produto pelo ID
	public Optional<ProdutoDTO> obterPorId(Integer id) {
		// Obtendo optional de produto pelo id
		Optional<Produto> produtos = produtoRepository.findById(id);

		// Verificando se o id foi encontado no BD
		if (produtos.isEmpty()) {
			throw new ResourceNotFoundException("Produto com id " + id + " não foi encontrado");
		}

		// Transformando o Optional Produto em ProdutoDTO
		ProdutoDTO dto = new ModelMapper().map(produtos.get(), ProdutoDTO.class);

		return Optional.of(dto);

	}

	// Adiciona um novo produto na lista
	public ProdutoDTO adicionar(ProdutoDTO produtoDTO) {
		// Remove o id para conseguir fazer o cadastro
		produtoDTO.setId(null);

		// Cria um objeto de mapeamento
		ModelMapper mp = new ModelMapper();

		// Converte o ProdutoDTO em PRoduto
		Produto produto = mp.map(produtoDTO, Produto.class);

		// Salva o produto do banco
		produto = produtoRepository.save(produto);

		produtoDTO.setId(produto.getId());

		return produtoDTO;

	}

	// Remove usando o id do produto
	public void deletar(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isEmpty()) {
			throw new ResourceNotFoundException("Produto com id " + id + " não foi encontrado.");
		}

		produtoRepository.deleteById(id);
	}

	public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDTO) {
		// Passar o id para o produtoDTO
		produtoDTO.setId(id);

		// Criar um objeto de mapeamento e converter ProdutoDTO em Produto
		Produto produto = new ModelMapper().map(produtoDTO, Produto.class);

		// Atualizar o produto no BD
		produtoRepository.save(produto);

		// Retornar o produtoDTO atualizado
		return produtoDTO;
	}
}
