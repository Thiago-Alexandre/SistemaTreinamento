package com.treinamento.dto;

import com.treinamento.model.EspacoCafe;
import com.treinamento.model.Etapa;
import com.treinamento.model.Sala;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PosicoesPorEtapaDTO {

	private Etapa etapa;
	private Sala sala;
	private EspacoCafe espaco;
}