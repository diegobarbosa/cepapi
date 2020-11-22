package br.com.cep.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@SuppressWarnings("unused")
public class Cidade {
  
  @Id
  @Column(length = 20)
  private String ibge;
  
  @Column(length = 2)
  @NotNull
  private String uf;
  
  @Column(length = 100)
  @NotNull
  private String localidade;
  
  @OneToMany(mappedBy="cidade")
  private List<Cep> ceps;

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

public String getLocalidade() {
	return localidade;
}

public void setLocalidade(String localidade) {
	this.localidade = localidade;
}

public List<Cep> getCeps() {
	return ceps;
}

public void setCeps(List<Cep> ceps) {
	this.ceps = ceps;
} 
  
}
