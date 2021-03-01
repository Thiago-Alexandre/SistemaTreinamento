package com.treinamento.dto;

import com.treinamento.model.Etapa;

import lombok.Getter;

@Getter
public class EtapaRespostaDTO {

	private Long id;
	private String nome;
	
	public EtapaRespostaDTO(Etapa etapa) {
		this.id = etapa.getId();
		this.nome = etapa.getNome();
	}
}