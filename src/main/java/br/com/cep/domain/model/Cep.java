package br.com.cep.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@SuppressWarnings("unused")
public class Cep {

	@Id
	@Column(length = 20)
	@NotNull
	private String cep;

	@NotNull
	@Column(length = 255)
	private String logradouro;

	@Column(length = 255)
	private String complemento;

	@Column(length = 50)
	private String bairro;

	@ManyToOne()
	@JoinColumn(name = "ibge")
	@NotNull
	private Cidade cidade;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
