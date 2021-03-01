package com.treinamento.dto;

import com.treinamento.model.Sala;

import lombok.Getter;

@Getter
public class SalaRespostaDTO {

	private Long id;
	private String nome;
	private Integer lotacao;
	
	public SalaRespostaDTO(Sala sala, Integer lotacao) {
		this.id = sala.getId();
		this.nome = sala.getNome();
		this.lotacao = lotacao;
	}
}