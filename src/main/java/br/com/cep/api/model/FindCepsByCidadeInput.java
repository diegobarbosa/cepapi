package br.com.cep.api.model;

import javax.validation.constraints.NotBlank;

public class FindCepsByCidadeInput {

	@NotBlank
	private String ibge;
	
	@NotBlank
	private String uf;
	
	public String getIbge() {
		return ibge;
	}
	public void setIbge(String ibge) {
		this.ibge = ibge;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
}
