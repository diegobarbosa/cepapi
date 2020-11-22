package br.com.cep.domain.httpclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import br.com.cep.domain.DomainException;

@Service
public class ViaCepClient {

		
	public ViaCep findCep(String cep) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "http://viacep.com.br/ws/";
		ResponseEntity<ViaCep> response = null;
		
		try {
			response  = restTemplate.getForEntity(url + cep + "/json", ViaCep.class);
		}
		catch(HttpStatusCodeException exception) {

			if(exception.getStatusCode() == HttpStatus.BAD_REQUEST) {
				throw new DomainException("Cep Client: Formato inválido");
			}
						
			throw new DomainException("Cep Client: Erro");
			
		}

		
		ViaCep viaCep = response.getBody();
		
		if(viaCep.getErro() != null && viaCep.getErro()) {
			throw new DomainException("Endereço CEP não encontrado");
		}
		
		return viaCep;
	}
}
