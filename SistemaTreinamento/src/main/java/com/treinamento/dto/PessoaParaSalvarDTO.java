package com.treinamento.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.treinamento.model.Pessoa;

import lombok.Data;

@Data
public class PessoaParaSalvarDTO {

	@NotBlank(message = "Nome não pode ser vazio!")
	@NotEmpty(message = "Nome não pode ser vazio!")
	@Size(max = 30, message = "Tamanho máximo do nome é de 30 caracteres!")
	private String nome;
	
	@NotBlank(message = "Sobrenome não pode ser vazio!")
	@NotEmpty(message = "Sobrenome não pode ser vazio!")
	@Size(max = 50, message = "Tamanho máximo do sobrenome é de 50 caracteres!")
	private String sobrenome;
	
	public Pessoa gerarPessoa() {
		return new Pessoa(null, nome, sobrenome);
	}
}