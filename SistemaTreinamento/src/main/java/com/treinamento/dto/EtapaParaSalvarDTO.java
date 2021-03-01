package com.treinamento.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.treinamento.model.Etapa;

import lombok.Data;

@Data
public class EtapaParaSalvarDTO {

	@NotBlank(message = "Nome não pode ser vazio!")
	@NotEmpty(message = "Nome não pode ser vazio!")
	@Size(max = 50, message = "Tamanho máximo do nome é de 50 caracteres!")
	private String nome;
	
	public Etapa gerarEtapa() {
		return new Etapa(null, nome);
	}
}