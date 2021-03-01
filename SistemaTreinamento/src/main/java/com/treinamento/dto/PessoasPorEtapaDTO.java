package com.treinamento.dto;

import java.util.List;

import com.treinamento.model.Etapa;
import com.treinamento.model.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PessoasPorEtapaDTO {

	private Etapa etapa;
	private List<Pessoa> pessoas;
}