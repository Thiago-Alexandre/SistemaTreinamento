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

import com.treinamento.dto.EspacoDetalhesDTO;
import com.treinamento.dto.EspacoCafeParaSalvarDTO;
import com.treinamento.dto.EspacoCafeRespostaDTO;
import com.treinamento.service.EspacoService;

@RequestMapping("/SistemaTreinamento")
@RestController
public class EspacoController {

	private final EspacoService espacoService;

	@Autowired
    public EspacoController(EspacoService espacoService) {
        this.espacoService = espacoService;
    }
	
	@PostMapping(value="/espaco")
    public ResponseEntity<EspacoCafeRespostaDTO> salvar(@RequestBody @Valid EspacoCafeParaSalvarDTO dto) {
        return new ResponseEntity<>(espacoService.salvar(dto.gerarEspaco()), HttpStatus.CREATED);
    }
	
	@GetMapping(value="/espaco/{id}")
	public ResponseEntity<EspacoDetalhesDTO> pesquisar(@PathVariable Long id) {
		EspacoDetalhesDTO espacoCafe = espacoService.pesquisar(id);
        if(espacoCafe.getEspaco() != null) {
        	return new ResponseEntity<>(espacoCafe, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/espaco")
	public ResponseEntity<List<EspacoCafeRespostaDTO>> listar() {
		List<EspacoCafeRespostaDTO> espacos = espacoService.listar();
        if(espacos.isEmpty()) {
            return new ResponseEntity<>(espacos, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(espacos, HttpStatus.OK);
	}
}