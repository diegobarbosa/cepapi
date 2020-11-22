package br.com.cep.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;


public class FindCepInput {
	
	@NotBlank
	@Pattern(regexp = "\\d{8}",message = "Formato inválido. Use 99999999. 8 Dígitos")
	private String cep;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
}
