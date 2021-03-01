package com.treinamento.dto;

import com.treinamento.model.EspacoCafe;

import lombok.Getter;

@Getter
public class EspacoCafeRespostaDTO {

	private Long id;
	private String nome;
	private Integer lotacao;
	
	public EspacoCafeRespostaDTO(EspacoCafe espaco, Integer lotacao) {
		this.id = espaco.getId();
		this.nome = espaco.getNome();
		this.lotacao = lotacao;
	}
}