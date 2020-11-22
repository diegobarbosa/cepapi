package br.com.cep.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cep.domain.httpclient.ViaCep;
import br.com.cep.domain.httpclient.ViaCepClient;
import br.com.cep.domain.model.Cep;
import br.com.cep.domain.model.Cidade;
import br.com.cep.domain.repository.CepRepository;
import br.com.cep.domain.repository.CidadeRepository;

@Service
public class CepService {
	
	@Autowired
	private CepRepository cepRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ViaCepClient viaCepClient;
	
	public Cep findCep(String cepStr) {
		
	Optional<Cep> cep = cepRepository.findById(cepStr);	
	
	if(cep.isPresent()) {
		return cep.get();
	}
		
	ViaCep viaCep =	viaCepClient.findCep(cepStr);
	
	var cidadeOptional = cidadeRepository.findById(viaCep.getIbge());
	
	Cidade cidade = null;
	
	if(cidadeOptional.isEmpty()) {
		cidade = new Cidade();
		cidade.setIbge(viaCep.getIbge());
		cidade.setLocalidade(viaCep.getLocalidade());
		cidade.setUf(viaCep.getUf());
		
		cidadeRepository.save(cidade);
	} 
	else {
		cidade = cidadeOptional.get();
	}
	
	var newCep = new Cep();
	newCep.setCep(viaCep.getCep().replace("-", ""));
	newCep.setLogradouro(viaCep.getLogradouro());
	newCep.setComplemento(viaCep.getComplemento());
	newCep.setBairro(viaCep.getBairro());
	newCep.setCidade(cidade);
	
	cepRepository.save(newCep);
	
	return newCep;
		
	}
	
	
	public List<Cep> findCepsByCidade(String ibge, String uf){
		return cepRepository.findByCidade(ibge, uf);
	}
}
