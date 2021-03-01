package com.treinamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PessoaEspaco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Etapa etapa;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private EspacoCafe espaco;
}