package com.treinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.treinamento.dto.EspacoCafeRespostaDTO;
import com.treinamento.model.EspacoCafe;

@Repository
public interface EspacoCafeRepository extends JpaRepository<EspacoCafe, Long>{

	@Query("select e from EspacoCafe e")
	public List<EspacoCafeRespostaDTO> pesquisarTodos();
}