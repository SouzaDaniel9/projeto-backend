package com.daniel.backendCompleto.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendedorDTO {

	private Integer id;
	private String nome;
	private String departamento;
	private Double salario;
	private Date aniversario;
}
