package com.treinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.treinamento.model.Etapa;
import com.treinamento.model.Pessoa;
import com.treinamento.model.PessoaSala;
import com.treinamento.model.Sala;

@Repository
public interface PessoaSalaRepository extends JpaRepository<PessoaSala, Long>{

	@Query("select ps from PessoaSala ps where ps.etapa = :etapa and ps.sala = :sala")
	public List<PessoaSala> findByEtapaAndSala(@Param("etapa") Etapa etapa, @Param("sala") Sala sala);
	
	@Query("select ps from PessoaSala ps where ps.etapa = :etapa and ps.pessoa = :pessoa")
	public List<PessoaSala> findByEtapaAndPessoa(@Param("etapa") Etapa etapa, @Param("pessoa") Pessoa pessoa);
}
