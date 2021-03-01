package com.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treinamento.model.Etapa;

@Repository
public interface EtapaRepository extends JpaRepository<Etapa, Long>{

}