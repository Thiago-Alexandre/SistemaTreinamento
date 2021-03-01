package com.treinamento.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.dto.PessoaDetalhesDTO;
import com.treinamento.dto.PessoaParaSalvarDTO;
import com.treinamento.dto.PessoaRespostaDTO;
import com.treinamento.service.PessoaService;

@RequestMapping("/SistemaTreinamento")
@RestController
public class PessoaController {

	private final PessoaService pessoaService;

	@Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }
	
	@PostMapping(value="/pessoa")
    public ResponseEntity<PessoaRespostaDTO> salvar(@RequestBody @Valid PessoaParaSalvarDTO dto) {
        return new ResponseEntity<>(pessoaService.salvar(dto.gerarPessoa()), HttpStatus.CREATED);
    }
	
	@GetMapping(value="/pessoa/{id}")
	public ResponseEntity<PessoaDetalhesDTO> pesquisar(@PathVariable Long id) {
		PessoaDetalhesDTO pessoa = pessoaService.pesquisar(id);
        if(pessoa.getPessoa() != null) {
        	return new ResponseEntity<>(pessoa, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/pessoa")
	public ResponseEntity<List<PessoaRespostaDTO>> listar() {
		List<PessoaRespostaDTO> pessoas = pessoaService.listar();
        if(pessoas.isEmpty()) {
            return new ResponseEntity<>(pessoas, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
	}
}