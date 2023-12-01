package com.daniel.backendCompleto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
	
		private Integer id;
		private String nome;
		private Double valor;
		private Integer quantidade;
		private String descricao;
	
}
