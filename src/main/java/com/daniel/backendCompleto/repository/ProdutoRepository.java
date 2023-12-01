package com.daniel.backendCompleto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniel.backendCompleto.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
