package com.treinamento.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.treinamento.model.EspacoCafe;
import com.treinamento.model.Etapa;
import com.treinamento.model.Pessoa;
import com.treinamento.model.PessoaEspaco;
import com.treinamento.model.PessoaSala;
import com.treinamento.model.Sala;
import com.treinamento.repository.EspacoCafeRepository;
import com.treinamento.repository.EtapaRepository;
import com.treinamento.repository.PessoaEspacoRepository;
import com.treinamento.repository.PessoaRepository;
import com.treinamento.repository.PessoaSalaRepository;
import com.treinamento.repository.SalaRepository;

@Component
public class ControleLotacao {

	private final PessoaRepository pessoaRepository;
	private final SalaRepository salaRepository;
	private final EspacoCafeRepository espacoRepository;
	private final EtapaRepository etapaRepository;
	private final PessoaSalaRepository pessoaSalaRepository;
	private final PessoaEspacoRepository pessoaEspacoRepository;

	@Autowired
    public ControleLotacao(
    						PessoaRepository pessoaRepository, 
    						SalaRepository salaRepository, 
    						EspacoCafeRepository espacoRepository, 
    						EtapaRepository etapaRepository,
    						PessoaSalaRepository pessoaSalaRepository,
    						PessoaEspacoRepository pessoaEspacoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.salaRepository = salaRepository;
        this.espacoRepository = espacoRepository;
        this.etapaRepository = etapaRepository;
		this.pessoaSalaRepository = pessoaSalaRepository;
		this.pessoaEspacoRepository = pessoaEspacoRepository;
    }
	
	public void atualizarLotacao() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		List<Sala> salas = salaRepository.findAll();
		List<EspacoCafe> espacos = espacoRepository.findAll();
		List<Etapa> etapas = etapaRepository.findAll();
		pessoaEspacoRepository.deleteAll();
		pessoaSalaRepository.deleteAll();
		if(pessoas.size() > 0 && salas.size() > 0 && espacos.size() > 0 && etapas.size() > 0) {
			int indiceEtapa = 0;
			int lotacaoMinPorEspaco = (int) (pessoas.size() / espacos.size());
			int qtdPessoas = 0;
			for(int indiceEspaco = 0; indiceEspaco < espacos.size(); indiceEspaco++) {
				for(int indicePessoa = 0; indicePessoa < lotacaoMinPorEspaco; indicePessoa++) {
					PessoaEspaco pe = new PessoaEspaco();
					pe.setEtapa(etapas.get(indiceEtapa));
					pe.setPessoa(pessoas.get(indicePessoa + qtdPessoas));
					pe.setEspaco(espacos.get(indiceEspaco));
					pessoaEspacoRepository.save(pe);
				}
				qtdPessoas += lotacaoMinPorEspaco;
			}
			for(int i = 0; i < pessoas.size() % espacos.size(); i++) {
				PessoaEspaco pe = new PessoaEspaco();
				pe.setEtapa(etapas.get(indiceEtapa));
				pe.setPessoa(pessoas.get(i + qtdPessoas));
				pe.setEspaco(espacos.get(i));
				pessoaEspacoRepository.save(pe);
			}
			int lotacaoMinPorSala = (int) (pessoas.size() / salas.size());
			qtdPessoas = 0;
			for(int indiceSala = 0; indiceSala < salas.size(); indiceSala++) {
				for(int indicePessoa = 0; indicePessoa < lotacaoMinPorSala; indicePessoa++) {
					PessoaSala ps = new PessoaSala();
					ps.setEtapa(etapas.get(indiceEtapa));
					ps.setPessoa(pessoas.get(indicePessoa + qtdPessoas));
					ps.setSala(salas.get(indiceSala));
					pessoaSalaRepository.save(ps);
				}
				qtdPessoas += lotacaoMinPorSala;
			}
			for(int i = 0; i < pessoas.size() % salas.size(); i++) {
				PessoaSala ps = new PessoaSala();
				ps.setEtapa(etapas.get(indiceEtapa));
				ps.setPessoa(pessoas.get(i + qtdPessoas));
				ps.setSala(salas.get(i));
				pessoaSalaRepository.save(ps);
			}
			int qtdTrocaEspaco = (int) lotacaoMinPorEspaco / 2;
			System.out.println(qtdTrocaEspaco);
			int qtdTrocaSala = (int) lotacaoMinPorSala / 2;
			System.out.println(qtdTrocaSala);
			for(indiceEtapa = 1; indiceEtapa < etapas.size(); indiceEtapa++) {
				if(espacos.size() > 1) {
					for(int indiceEspaco = 0; indiceEspaco < espacos.size() - 1; indiceEspaco++) {
						List<PessoaEspaco> pessoasEspaco = pessoaEspacoRepository.findByEtapaAndEspaco(etapas.get(0), espacos.get(indiceEspaco + 1));
						for(int indicePessoa = 0; indicePessoa < qtdTrocaEspaco; indicePessoa++) {
							PessoaEspaco pe = new PessoaEspaco();
							pe.setEtapa(etapas.get(indiceEtapa));
							pe.setPessoa(pessoasEspaco.get(indicePessoa).getPessoa());
							pe.setEspaco(espacos.get(indiceEspaco));
							pessoaEspacoRepository.save(pe);
						}
						pessoasEspaco = pessoaEspacoRepository.findByEtapaAndEspaco(etapas.get(0), espacos.get(indiceEspaco));
						for(int indicePessoa = qtdTrocaEspaco; indicePessoa < pessoasEspaco.size(); indicePessoa++) {
							PessoaEspaco pe = new PessoaEspaco();
							pe.setEtapa(etapas.get(indiceEtapa));
							pe.setPessoa(pessoasEspaco.get(indicePessoa).getPessoa());
							pe.setEspaco(espacos.get(indiceEspaco));
							pessoaEspacoRepository.save(pe);
						}
					}
					
					List<PessoaEspaco> pessoasEspaco = pessoaEspacoRepository.findByEtapaAndEspaco(etapas.get(0), espacos.get(0));
					for(int indicePessoa = 0; indicePessoa < qtdTrocaEspaco; indicePessoa++) {
						PessoaEspaco pe = new PessoaEspaco();
						pe.setEtapa(etapas.get(indiceEtapa));
						pe.setPessoa(pessoasEspaco.get(indicePessoa).getPessoa());
						pe.setEspaco(espacos.get(espacos.size() - 1));
						pessoaEspacoRepository.save(pe);
					}
					pessoasEspaco = pessoaEspacoRepository.findByEtapaAndEspaco(etapas.get(0), espacos.get(espacos.size() - 1));
					for(int indicePessoa = qtdTrocaEspaco; indicePessoa < pessoasEspaco.size(); indicePessoa++) {
						PessoaEspaco pe = new PessoaEspaco();
						pe.setEtapa(etapas.get(indiceEtapa));
						pe.setPessoa(pessoasEspaco.get(indicePessoa).getPessoa());
						pe.setEspaco(espacos.get(espacos.size() - 1));
						pessoaEspacoRepository.save(pe);
					}
				} else {
					List<PessoaEspaco> pessoasEspaco = pessoaEspacoRepository.findByEtapaAndEspaco(etapas.get(0), espacos.get(0));
					for(int indicePessoa = 0; indicePessoa < pessoasEspaco.size(); indicePessoa++) {
						PessoaEspaco pe = new PessoaEspaco();
						pe.setEtapa(etapas.get(indiceEtapa));
						pe.setPessoa(pessoasEspaco.get(indicePessoa).getPessoa());
						pe.setEspaco(espacos.get(0));
						pessoaEspacoRepository.save(pe);
					}
				}
				if(salas.size() > 1) {
					for(int indiceSala = 0; indiceSala < salas.size() - 1; indiceSala++) {
						List<PessoaSala> pessoasSala = pessoaSalaRepository.findByEtapaAndSala(etapas.get(0), salas.get(indiceSala + 1));
						for(int indicePessoa = 0; indicePessoa < qtdTrocaSala; indicePessoa++) {
							PessoaSala ps = new PessoaSala();
							ps.setEtapa(etapas.get(indiceEtapa));
							ps.setPessoa(pessoasSala.get(indicePessoa).getPessoa());
							ps.setSala(salas.get(indiceSala));
							pessoaSalaRepository.save(ps);
						}
						pessoasSala = pessoaSalaRepository.findByEtapaAndSala(etapas.get(0), salas.get(indiceSala));
						for(int indicePessoa = qtdTrocaSala; indicePessoa < pessoasSala.size(); indicePessoa++) {
							PessoaSala ps = new PessoaSala();
							ps.setEtapa(etapas.get(indiceEtapa));
							ps.setPessoa(pessoasSala.get(indicePessoa).getPessoa());
							ps.setSala(salas.get(indiceSala));
							pessoaSalaRepository.save(ps);
						}
					}
					
					List<PessoaSala> pessoasSala = pessoaSalaRepository.findByEtapaAndSala(etapas.get(0), salas.get(0));
					for(int indicePessoa = 0; indicePessoa < qtdTrocaSala; indicePessoa++) {
						PessoaSala ps = new PessoaSala();
						ps.setEtapa(etapas.get(indiceEtapa));
						ps.setPessoa(pessoasSala.get(indicePessoa).getPessoa());
						ps.setSala(salas.get(salas.size() - 1));
						pessoaSalaRepository.save(ps);
					}
					pessoasSala = pessoaSalaRepository.findByEtapaAndSala(etapas.get(0), salas.get(salas.size() - 1));
					for(int indicePessoa = qtdTrocaSala; indicePessoa < pessoasSala.size(); indicePessoa++) {
						PessoaSala ps = new PessoaSala();
						ps.setEtapa(etapas.get(indiceEtapa));
						ps.setPessoa(pessoasSala.get(indicePessoa).getPessoa());
						ps.setSala(salas.get(salas.size() - 1));
						pessoaSalaRepository.save(ps);
					}
				} else {
					List<PessoaSala> pessoasSala = pessoaSalaRepository.findByEtapaAndSala(etapas.get(0), salas.get(0));
					for(int indicePessoa = 0; indicePessoa < pessoasSala.size(); indicePessoa++) {
						PessoaSala ps = new PessoaSala();
						ps.setEtapa(etapas.get(indiceEtapa));
						ps.setPessoa(pessoasSala.get(indicePessoa).getPessoa());
						ps.setSala(salas.get(0));
						pessoaSalaRepository.save(ps);
					}
				}
			}
		}
	}
}