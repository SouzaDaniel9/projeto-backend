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
		//Obtendo optional de produto pelo id
		Optional<Produto> produtos = produtoRepository.findById(id);

		// Verificando se o id foi encontado no BD
		if (produtos.isEmpty()) {
			throw new ResourceNotFoundException("Produto com id " + id + " não foi encontrado");
		}
		
		//Transformando o Optional Produto em ProdutoDTO
		ProdutoDTO dto = new ModelMapper().map(produtos.get(), ProdutoDTO.class);
		
		return Optional.of(dto);
		
	}

	// Adiciona um novo produto na lista
	public ProdutoDTO adicionar(ProdutoDTO produtoDto) {
		return produtoRepository.save(produtoDto);
	}

	// Remove usando o id do produto
	public void deletar(Integer id) {
		produtoRepository.deleteById(id);
	}

	public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto) {
		produtoDto.setId(id);
		return produtoRepository.save(produtoDto);
	}
}
