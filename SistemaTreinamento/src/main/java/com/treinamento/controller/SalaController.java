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

import com.treinamento.dto.SalaDetalhesDTO;
import com.treinamento.dto.SalaParaSalvarDTO;
import com.treinamento.dto.SalaRespostaDTO;
import com.treinamento.service.SalaService;

@RequestMapping("/SistemaTreinamento")
@RestController
public class SalaController {

	private final SalaService salaService;

	@Autowired
    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }
	
	@PostMapping(value="/sala")
    public ResponseEntity<SalaRespostaDTO> salvar(@RequestBody @Valid SalaParaSalvarDTO dto) {
        return new ResponseEntity<>(salaService.salvar(dto.gerarSala()), HttpStatus.CREATED);
    }
	
	@GetMapping(value="/sala/{id}")
	public ResponseEntity<SalaDetalhesDTO> pesquisar(@PathVariable Long id) {
		SalaDetalhesDTO sala = salaService.pesquisar(id);
        if(sala.getSala() != null) {
        	return new ResponseEntity<>(sala, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/sala")
	public ResponseEntity<List<SalaRespostaDTO>> listar() {
		List<SalaRespostaDTO> salas = salaService.listar();
        if(salas.isEmpty()) {
            return new ResponseEntity<>(salas, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(salas, HttpStatus.OK);
	}
}