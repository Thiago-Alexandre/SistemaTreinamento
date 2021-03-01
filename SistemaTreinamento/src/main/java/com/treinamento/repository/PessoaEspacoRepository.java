package com.treinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.treinamento.model.Etapa;
import com.treinamento.model.Pessoa;
import com.treinamento.model.PessoaEspaco;
import com.treinamento.model.EspacoCafe;

@Repository
public interface PessoaEspacoRepository extends JpaRepository<PessoaEspaco, Long>{

	@Query("select pe from PessoaEspaco pe where pe.etapa = :etapa and pe.espaco = :espaco")
	public List<PessoaEspaco> findByEtapaAndEspaco(@Param("etapa") Etapa etapa, @Param("espaco") EspacoCafe espaco);
	
	@Query("select pe from PessoaEspaco pe where pe.etapa = :etapa and pe.pessoa = :pessoa")
	public List<PessoaEspaco> findByEtapaAndPessoa(@Param("etapa") Etapa etapa, @Param("pessoa") Pessoa pessoa);
}