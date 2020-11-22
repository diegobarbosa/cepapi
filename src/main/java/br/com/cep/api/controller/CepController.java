package br.com.cep.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cep.api.model.CepModel;
import br.com.cep.api.model.FindCepInput;
import br.com.cep.api.model.FindCepsByCidadeInput;
import br.com.cep.domain.model.Cep;
import br.com.cep.domain.service.CepService;

@RestController
public class CepController {

	@Autowired
	private CepService cepService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/cep/{cep}")
	public CepModel findCep(@Valid FindCepInput cep) {
		return toModel(cepService.findCep(cep.getCep()));
	}
	
	@GetMapping("/ceps")
	public List<CepModel> findCepsByCidade(@Valid FindCepsByCidadeInput input) {
		return cepService.findCepsByCidade(input.getIbge(), input.getUf())
				.stream().map(comentario-> toModel(comentario))
				.collect(Collectors.toList());
	}
	
	private CepModel toModel(Cep cep) {
		return modelMapper.map(cep, CepModel.class);
	}
	
	@PostMapping("/cep")
	public String PostTest(@Valid FindCepInput cep) {
		return cep.getCep();
	}

}
