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

import com.daniel.backendCompleto.model.Vendedor;
import com.daniel.backendCompleto.service.VendedorService;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

	@Autowired
	private VendedorService service;
	
	@GetMapping
	public List<Vendedor> obterTodos() {
		return service.obterTodos();
	}

	@GetMapping("/{id}")
	public Optional<Vendedor> obterPorId(@PathVariable Integer id) {
		return service.obterPorId(id);
	}

	@PostMapping
	public Vendedor adicionarVendedor(@RequestBody Vendedor v) {
		return service.adicionarVendedor(v);
	}

	@DeleteMapping("/{id}")
	public void deletarUsandoId(@PathVariable Integer id) {
		service.deletarUsandoId(id);
	}

	@PutMapping("/{id}")
	public void atualizarVendedor(@PathVariable Integer id, @RequestBody Vendedor v) {
		service.atualizarVendedor(id, v);
	}
	
}
