package com.treinamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treinamento.dto.PessoaDetalhesDTO;
import com.treinamento.dto.PessoaRespostaDTO;
import com.treinamento.dto.PosicoesPorEtapaDTO;
import com.treinamento.model.Etapa;
import com.treinamento.model.Pessoa;
import com.treinamento.model.PessoaEspaco;
import com.treinamento.model.PessoaSala;
import com.treinamento.repository.EtapaRepository;
import com.treinamento.repository.PessoaEspacoRepository;
import com.treinamento.repository.PessoaRepository;
import com.treinamento.repository.PessoaSalaRepository;
import com.treinamento.util.ControleLotacao;

@Service
public class PessoaService {

	private final PessoaRepository pessoaRepository;
	private final EtapaRepository etapaRepository;
	private final PessoaSalaRepository pessoaSalaRepository;
	private final PessoaEspacoRepository pessoaEspacoRepository;
	private final ControleLotacao controle;

	@Autowired
    public PessoaService(PessoaRepository pessoaRepository, 
    						EtapaRepository etapaRepository,
    						PessoaSalaRepository pessoaSalaRepository, 
    						PessoaEspacoRepository pessoaEspacoRepository, 
    						ControleLotacao controle) {
        this.pessoaRepository = pessoaRepository;
        this.etapaRepository = etapaRepository;
        this.pessoaSalaRepository = pessoaSalaRepository;
		this.pessoaEspacoRepository = pessoaEspacoRepository;
        this.controle = controle;
    }
	
	public PessoaRespostaDTO salvar(Pessoa pessoa) {
		Pessoa novaPessoa = pessoaRepository.save(pessoa);
		controle.atualizarLotacao();
        return new PessoaRespostaDTO(novaPessoa);
    }
	
	public PessoaDetalhesDTO pesquisar(Long id) {
    	Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if(pessoa.isPresent()) {
        	List<PosicoesPorEtapaDTO> posicoesPorEtapa = new ArrayList<PosicoesPorEtapaDTO>();
        	List<Etapa> etapas = etapaRepository.findAll();
        	for(int i = 0; i < etapas.size(); i++) {
        		List<PessoaSala> listaSala = pessoaSalaRepository.findByEtapaAndPessoa(etapas.get(i), pessoa.get());
        		PessoaSala ps = listaSala.get(0);
        		List<PessoaEspaco> listaEspaco = pessoaEspacoRepository.findByEtapaAndPessoa(etapas.get(i), pessoa.get());
        		PessoaEspaco pe = listaEspaco.get(0);
        		posicoesPorEtapa.add(new PosicoesPorEtapaDTO(etapas.get(i), ps.getSala(), pe.getEspaco()));
        	}
        	return new PessoaDetalhesDTO(pessoa.get(), posicoesPorEtapa);
        }
        return new PessoaDetalhesDTO(null, null);
    }

    public List<PessoaRespostaDTO> listar() {
    	List<PessoaRespostaDTO> lista = new ArrayList<PessoaRespostaDTO>();
    	List<Pessoa> pessoas = pessoaRepository.findAll();
    	for(int i = 0; i < pessoas.size(); i++) {
	    	lista.add(new PessoaRespostaDTO(pessoas.get(i)));
	    }
    	return lista;
    }
}