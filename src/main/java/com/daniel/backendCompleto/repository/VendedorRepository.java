package com.daniel.backendCompleto.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.daniel.backendCompleto.model.Vendedor;

@Repository
public class VendedorRepository {

	private List<Vendedor> vendedores = new ArrayList<>();
	private Integer ultimoId = 0;

	public List<Vendedor> obterTodos() {
		return vendedores;
	}

	public Optional<Vendedor> obterPorId(Integer id) {
		return vendedores.stream().filter(vendedor -> vendedor.getId() == id).findFirst();
	}

	public Vendedor adicionarVendedor(Vendedor v) {
		ultimoId++;
		v.setId(ultimoId);
		vendedores.add(v);
		return v;
	}

	public void deletarUsandoId(Integer id) {
		vendedores.removeIf(vendedor -> vendedor.getId() == id);
	}

	public void atualizarVendedor(Integer id, Vendedor v) {
		Optional<Vendedor> vendedorEncontrado = obterPorId(v.getId());

		if (vendedorEncontrado.isEmpty()) {
			throw new InputMismatchException("Vendedor não encontrado");
		}

		deletarUsandoId(v.getId());
		vendedores.add(v);
	}

}
