package com.daniel.backendCompleto.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {

	private String nome;
	private Double valor;
	private Integer quantidade;
	private String descricao;

}
