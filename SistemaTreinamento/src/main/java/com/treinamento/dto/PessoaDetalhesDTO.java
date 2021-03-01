package com.treinamento.dto;

import java.util.List;

import com.treinamento.model.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PessoaDetalhesDTO {
	private Pessoa pessoa;
	private List<PosicoesPorEtapaDTO> etapas;
}