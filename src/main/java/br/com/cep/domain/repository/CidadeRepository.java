package br.com.cep.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cep.domain.model.Cidade;

@Repository
public interface CidadeRepository  extends JpaRepository<Cidade, String> {

}
