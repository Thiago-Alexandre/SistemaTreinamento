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

import com.treinamento.dto.EtapaParaSalvarDTO;
import com.treinamento.dto.EtapaRespostaDTO;
import com.treinamento.service.EtapaService;

@RequestMapping("/SistemaTreinamento")
@RestController
public class EtapaController {

	private final EtapaService etapaService;

	@Autowired
    public EtapaController(EtapaService etapaService) {
        this.etapaService = etapaService;
    }
	
	@PostMapping(value="/etapa")
    public ResponseEntity<EtapaRespostaDTO> salvar(@RequestBody @Valid EtapaParaSalvarDTO dto) {
        return new ResponseEntity<>(etapaService.salvar(dto.gerarEtapa()), HttpStatus.CREATED);
    }
	
	@GetMapping(value="/etapa/{id}")
	public ResponseEntity<EtapaRespostaDTO> pesquisar(@PathVariable Long id) {
		EtapaRespostaDTO etapa = etapaService.pesquisar(id);
        if(etapa.getId() != null) {
        	return new ResponseEntity<>(etapa, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/etapa")
	public ResponseEntity<List<EtapaRespostaDTO>> listar() {
		List<EtapaRespostaDTO> etapas = etapaService.listar();
        if(etapas.isEmpty()) {
            return new ResponseEntity<>(etapas, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(etapas, HttpStatus.OK);
	}
}