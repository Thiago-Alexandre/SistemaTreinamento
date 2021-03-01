package com.treinamento.dto;

import java.util.List;

import com.treinamento.model.Sala;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SalaDetalhesDTO {

	private Sala sala;
	private List<PessoasPorEtapaDTO> pessoas;
}