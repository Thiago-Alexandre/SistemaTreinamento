package com.treinamento.dto;

import java.util.List;

import com.treinamento.model.EspacoCafe;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EspacoDetalhesDTO {

	private EspacoCafe espaco;
	private List<PessoasPorEtapaDTO> pessoas;
}
